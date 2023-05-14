package com.example.example;

/**
 * POJO object
 */
public class XmlCustomObject {

    String buyRate;
    String participantId;
    String sellRate;
    String currencyCode;

    public XmlCustomObject(String buyRate, String participantId, String sellRate, String currencyCode) {
        this.buyRate = buyRate;
        this.participantId = participantId;
        this.sellRate = sellRate;
        this.currencyCode = currencyCode;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getSellRate() {
        return sellRate;
    }

    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "XmlCustomObject{" +
                "buyRate='" + buyRate + '\'' +
                ", participantId='" + participantId + '\'' +
                ", sellRate='" + sellRate + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

}
