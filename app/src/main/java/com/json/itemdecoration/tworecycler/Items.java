package com.json.itemdecoration.tworecycler;

public class Items {
    private String newPrices; //最新价
    private String upDowms; //涨跌
    private String buy; //报买

    private String buyNums; //手数
    private String buyDates; //时间
    private String sell; //报卖

    private String sellNums; //手数
    private String sellDates; //时间
    private String YTDPut; //昨收

    public Items() {
    }

    public String getNewPrices() {
        return newPrices;
    }

    public void setNewPrices(String newPrices) {
        this.newPrices = newPrices;
    }

    public String getUpDowms() {
        return upDowms;
    }

    public void setUpDowms(String upDowms) {
        this.upDowms = upDowms;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getBuyNums() {
        return buyNums;
    }

    public void setBuyNums(String buyNums) {
        this.buyNums = buyNums;
    }

    public String getBuyDates() {
        return buyDates;
    }

    public void setBuyDates(String buyDates) {
        this.buyDates = buyDates;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSellNums() {
        return sellNums;
    }

    public void setSellNums(String sellNums) {
        this.sellNums = sellNums;
    }

    public String getSellDates() {
        return sellDates;
    }

    public void setSellDates(String sellDates) {
        this.sellDates = sellDates;
    }

    public String getYTDPut() {
        return YTDPut;
    }

    public void setYTDPut(String YTDPut) {
        this.YTDPut = YTDPut;
    }
}
