package com.example.ht1;

public class Salt {
    String salt;
    int user;

    public Salt(String s, int u){
        String salt = s;
        int user = u;
    }

    public int getUser(){
        return user;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String s){
        salt = s;
    }
}
