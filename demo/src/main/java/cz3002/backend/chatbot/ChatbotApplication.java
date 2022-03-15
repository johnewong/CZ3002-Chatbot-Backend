package cz3002.backend.chatbot;

import chatbot.Bot;
import chatbot.DataParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {

    public static void main(String[] args) {

        // construct a data parser
        //DataParser dp = new DataParser("D:\\ntu\\advance software\\backend\\CZ3002-Chatbot-Backend\\bot\\data.xml");
        String currentDir = System.getProperty("user.dir");
        // construct new bot with level 0 as default and given data parser
        Bot bot = new Bot("0", currentDir + "\\chatbotData\\data.xml",currentDir + "\\chatbotData\\faq.xml",currentDir + "\\chatbotData\\rating.xml");

        SpringApplication.run(ChatbotApplication.class, args);
    }

}
