package chatbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main  {

    public static void main(String[] args) throws IOException {

        // construct a data parser
        // DataParser dp = new DataParser();

        // construct new bot with level 0 as default and given data parser
        String currentDir = System.getProperty("user.dir");
        Bot bot = new Bot("0", currentDir + "\\chatbotData\\data.xml", currentDir + "\\chatbotData\\faq.xml", currentDir + "\\chatbotData\\rating.xml");
        //System.out.println(bot.getFAQ(5));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
//
        String input = "";
        while(!input.equals("-1")){

            String botMessage = bot.getMessage();
            System.out.println(botMessage);

            input = reader.readLine();
            String response = bot.send(input);


            if (response.length() > 0) {
                System.out.println(response);
            }

        }
    }
}
