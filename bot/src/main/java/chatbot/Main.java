package chatbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main  {

    public static void main(String[] args) throws IOException {

        // construct a data parser
        DataParser dp = new DataParser();

        // construct new bot with level 0 as default and given data parser
        Bot bot = new Bot("0", dp);

        bot.getMessage();

//        // send the message to the bot and get the bot response
//        String response = bot.send(txtMessage.getText());
//
//        // if the response is not empty display it
//        if (response.length() > 0) {
//            addBotText(response);
//        }
//
//        // display new state message
//        addBotText(bot.getMessage());

       // System.out.println("hello");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = "";
        while(!input.equals("-1")){

            System.out.println("hello please type something (-1 to exit)");
            input = reader.readLine();

            bot.getMessage();

            String response = bot.send(input);
            System.out.println(response);

            System.out.println("end of session");
        }
    }
}
