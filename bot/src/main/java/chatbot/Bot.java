package chatbot;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Bot {
    // Store all regular expression matches
    private HashMap<String,String> dictionary;

    // Default state to start the bot
    String level = "0";
    DataParser parser;
    String faqPath;
    String ratingPath;
    // default constructor
    public Bot(String level, String dataPath, String faqPath, String ratingPath) {
        dictionary = new HashMap<String,String>();
        this.level = level;
        this.faqPath = faqPath;
        this.ratingPath = ratingPath;
        this.parser = new DataParser(dataPath);
        System.out.println("Chat bot initialization...");
    }

    // get current state message
    public String getMessage() {
        State state = parser.getState(level);
        return replaceMatches(state.getMessage()).trim();
    }

    // send user message to the bot and get the response
    public String send(String message) {

        String response = "";
        State state = parser.getState(level);


        // end of the tree
        if (state.getKeywords().isEmpty()) {
            this.level = "0";
        }

        // match the keyword with given message
        Keyword match = parse(message, state.getKeywords());

        // if no keyword is matched, display one of the invalid answers
        if (match == null) {
            response = parser.getInvalidAnswer();
        } else {

            // if match classname is provided, check to get the dynamic response
            if (match.className.length() > 0) {

                // check for Weather dynamic response
                if (match.className.equals("Weather")) {
                    Weather weather = new Weather();
                    response = weather.getResponse(match.arg);
                    this.level = "0";
                }
            } else {

                // get the new state and return the new message
                if (response.length() == 0) {

                    this.level = match.target;
                    state = parser.getState(level);

                    // if it is end of the tree
                    if (state.getKeywords().isEmpty()) {
                        response = this.getMessage();
                        this.level = "0";

                    }
                }
            }
        }
        return response;
    }

    // parse the given text to find best match in the keywords
    private Keyword parse(String text, ArrayList<Keyword> keylist) {

        // set the default match to none
        int bestMatch = -1;
        Keyword match = null;

        // loop through keywords
        for (int i = 0; i < keylist.size(); i++) {

            // get number of matches of the keyword with given text
            int matches = getMatches(text, keylist.get(i));

            // if match is better than best match, replace it
            if (matches > -1 && matches > bestMatch) {
                match = keylist.get(i);
                bestMatch = matches;
            }
        }

        // add best answers regex variable value into the dictionary for future reference
        if (match != null){
            if(match.learn.length() > 0 ){

                // get training data keyword and description
                String subject = dictionary.get(match.learn);
                String result =  match.variableValue;


                // create a new state for new trained data
                ArrayList<String> messages = new ArrayList<String>();
                messages.add(result);
                State myState = new State(String.valueOf(parser.stateCounter),messages,new ArrayList());
                parser.addState(myState);

                // add the new trained keyword
                Keyword keyword = new Keyword(subject, myState.getId(), "", "", "", 1, "" );
                State state = parser.getState("1");
                ArrayList<Keyword> keywords = state.getKeywords();
                keywords.add(keyword);

            }else{
                if (match.variableValue.length() > 0){
                    dictionary.put(match.variable, match.variableValue);
                }
            }
        }
        return match;
    }

    // get number of matches of the given keywords in the given list
    private int getMatches(String text, Keyword keyword) {

        // no match by default
        int result = -1;

        // return 0 match when keyword is *
        if(keyword.keyword.equals("*")){
            return keyword.points;
        }

        // if regex is expected
        if(keyword.variable.length() > 0){
            String match = Regex.match(keyword.keyword, text);
            if(match.length() > 0){
                keyword.variableValue = match;
                return keyword.points;
            }
        }

        String[] words = keyword.keyword.split(" ");


        // loop through list of the keywords
        for (String word : words) {

            // if current keyword is in the text, add points
            if (text.toLowerCase().indexOf(word.toLowerCase()) >= 0) {
                result = result + keyword.points + 1;
            } else {
                // return null if one of the keywords does not exists
                return -1;
            }
        }
        return result;
    }


    // replace given text with variables in the dictionary
    public String replaceMatches(String text){

        // replace variables within dictionary in the text
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            text = text.replaceAll("\\["+entry.getKey() + "\\]", entry.getValue());
        }

        // remove empty variables tags
        return Regex.clear(text);
    }

    public void rateAnswer(String ans, String rate)  {
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(ratingPath);
            // Get the parent node
            Node data = doc.getFirstChild();
            // Get the employee element

            // Add a new node
            Element Result = doc.createElement("Result");
            Element Answer = doc.createElement("Answer");
            Element Rating = doc.createElement("Rating");
            Answer.appendChild(doc.createTextNode(ans));
            Rating.appendChild(doc.createTextNode(rate));
            Result.appendChild(Answer);
            Result.appendChild(Rating);
            data.appendChild(Result);
            // write the content to the xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new File(this.ratingPath));
            transformer.transform(src, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList getFAQ(int topRows) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        ArrayList faqlist = new ArrayList();
        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            Document dom = db.parse(this.faqPath);

            Element docEle = dom.getDocumentElement();

            NodeList nl = docEle.getElementsByTagName("Question");
            System.out.println(nl);



            if (nl != null && nl.getLength() > 0) {

                // loop through all children
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    String question = el.getTextContent().replaceAll("\\s","");
                    // get state id
                    int id = Integer.parseInt(el.getAttribute("id"));
                    if(id <= topRows){
                        faqlist.add(question);
                    }
                    // get all state messages

                }

            }
            // Load configuration and states from the XML file

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return faqlist;
    }



}
