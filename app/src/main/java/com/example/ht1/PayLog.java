package com.example.ht1;

public class PayLog {
    private int ide;
    int date_;
    float sum;
    private String from_account;
    private String from_name;
    private String to_account;
    private String to_name;

    public PayLog(int ide, int date_, float sum, String from_account, String from_name, String to_account, String to_name) {
        this.ide = ide;
        this.date_ = date_;
        this.sum = sum;
        this.from_account = from_account;
        this.from_name = from_name;
        this.to_account = to_account;
        this.to_name = to_name;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }
    public int getIde() {
        return ide;
    }
    public void setDate(int date_) {
        this.date_ = date_;
    }
    public int getDate_() {
        return date_;
    }
    public void setSum(float sum) {
        this.sum = sum;
    }
    public float getSum() {
        return sum ;
    }
    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }
    public String getFrom_account() {
        return from_account;
    }
    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }
    public String getFrom_name() {
        return from_name;
    }
    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }
    public String getTo_account() {
        return to_account;
    }
    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }
    public String getTo_name() {
        return to_name;
    }
}
