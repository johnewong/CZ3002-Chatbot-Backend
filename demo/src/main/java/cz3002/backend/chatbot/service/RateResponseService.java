package cz3002.backend.chatbot.service;

import org.springframework.stereotype.Service;
import cz3002.backend.chatbot.pojo.RateResponse;
import cz3002.backend.chatbot.dao.ReadExcel;

@Service
public class RateResponseService {
    public String save(){
        return "Thank you for your support";
    }
}
