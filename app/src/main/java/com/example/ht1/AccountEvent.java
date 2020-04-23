package com.example.ht1;

public class AccountEvent {
    private int id;
    private String account_num;
    private String day;
    private float sum;
    private String from_account_num;
    private String from_account_name;

    protected AccountEvent(int id, String account_num, String day, float sum, String from_account_num, String from_account_name) {
        this.id = id;
        this.account_num = account_num;
        this.day = day;
        this.sum = sum;
        this.from_account_num = from_account_num;
        this.from_account_name = from_account_name;
    }

    public int getId() { return id; }
    public String getAccount_num() { return from_account_num; }
    public String getDay() { return day; }
    public float getSum() { return sum; }
    public String getFrom_account_num() { return from_account_num; }
    public String getFrom_account_name() { return from_account_name; }

}
