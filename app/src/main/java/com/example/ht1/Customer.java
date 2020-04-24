package com.example.ht1;

public class Customer {
    String password;
    int user_id;
    String BIC;
    String SSN;
    String name;
    int telephone;
    String address;
    String postal_code;

    public Customer(String password, int user_id, String BIC, String SSN, String name, int telephone, String address, String postal_code) {
        this.password = password;
        this.user_id = user_id;
        this.BIC = BIC;
        this.SSN = SSN;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.postal_code = postal_code;
    }

    public String getPassword() {
        return password;
    }
    public String getBIC() {
        return BIC;
    }
    public String getSSN() {
        return SSN;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return telephone;
    }
    public String getAddress() {
        return address;
    }
    public String getPostalCode() {
        return postal_code;
    }
    public void setPassword(String s) {
        password = s;
    }

}


