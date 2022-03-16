package cz3002.backend.chatbot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chatbot.Bot;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ChabotController {
    String currentDir = System.getProperty("user.dir");
    // construct new bot with level 0 as default and given data parser
    Bot bot;
    //Bot bot = new Bot("0", currentDir + "\\chatbotData\\data.xml",currentDir + "\\chatbotData\\faq.xml",currentDir + "\\chatbotData\\rating.xml");

    @RequestMapping(value = "/initbot", method = RequestMethod.GET)
    public String initbot(String Question) {
        this.bot = new Bot("0", "chatbotData/data_new.xml",  "chatbotData/faq.xml",   "chatbotData/rating.xml");
        String botMessage = bot.getMessage();

        return botMessage;
    }

    @RequestMapping(value = "/getAnswer", method = RequestMethod.GET)
    public String getAnswer(String question, int state) {
        if(state == 0){
            this.bot = new Bot("0", "chatbotData/data_new.xml",  "chatbotData/faq.xml",   "chatbotData/rating.xml");
        }

        String response = bot.send(question);
        String answer = "";

        if (response.length() > 0) {
            answer = response;
        }
        else {
            answer = bot.getMessage();
        }

        return answer;
    }
    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    @ResponseBody
    public void rating(String answer, String rate) {
        System.out.println(answer);
        bot.rateAnswer(answer,rate);
    }

    @RequestMapping(value = "/getfaq", method = RequestMethod.GET)
    public List<String> getfaq(int toprows) {
        List<String> faqs = bot.getFAQ(toprows);
        return faqs;
    }
}
