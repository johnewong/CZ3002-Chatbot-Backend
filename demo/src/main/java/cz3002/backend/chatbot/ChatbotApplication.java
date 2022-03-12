package cz3002.backend.chatbot;

import chatbot.Bot;
import chatbot.DataParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {

    public static void main(String[] args) {

        // construct a data parser
        DataParser dp = new DataParser();

        // construct new bot with level 0 as default and given data parser
        Bot bot = new Bot("0", dp);


        SpringApplication.run(ChatbotApplication.class, args);
    }

}
