package cz3002.backend.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz3002.backend.chatbot.pojo.RateResponse;
import cz3002.backend.chatbot.dao.ReadExcel;
import cz3002.backend.chatbot.dao.WriteCSV;

@Service
public class RateResponseService {
    public String save(RateResponse rate){
        WriteCSV writeCsv = new WriteCSV();
        writeCsv.writefile("xxx.csv",rate);
        return "Thank you for your support";
    }
}
