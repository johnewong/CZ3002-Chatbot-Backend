package cz3002.backend.chatbot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//@Entity
//@Table(name = "RateResponse")
//@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class RateResponse {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "RateResponseId")
    private Integer RateResponseId;

   // @Column(name = "Rate")
    private Integer Rate;

   // @Column(name="QnID")
    private Integer QnID;


    public Integer getRateResponseId() {
        return this.RateResponseId;
    }
    public void setRateResponseId(Integer RateResponseId) {
        this.RateResponseId = RateResponseId;
    }

    public Integer getRate() {
        return this.Rate;
    }
    public void setRate(Integer Rate) {
        this.Rate = Rate;
    }

    public Integer getQnID() {
        return this.QnID;
    }
    public void setQnID(Integer QnId) {
        this.QnID = QnID;
    }



}


