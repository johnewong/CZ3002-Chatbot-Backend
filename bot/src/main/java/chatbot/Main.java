package chatbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main  {

    public static void main(String[] args) throws IOException {

        // construct a data parser
        // DataParser dp = new DataParser();

        // construct new bot with level 0 as default and given data parser
        Bot bot = new Bot("0","D:\\ntu\\advance software\\backend\\CZ3002-Chatbot-Backend\\bot\\data.xml");

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
