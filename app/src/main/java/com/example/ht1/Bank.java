package com.example.ht1;

public class Bank {
    private int id;
    private String BIC;
    private String name;
    private String address;
    private String phone;

    protected Bank(int id, String BIC, String name, String address, String phone) {
        this.id = id;
        this.BIC = BIC;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getBIC() { return BIC; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public  String getPhone() {return phone; }

}
