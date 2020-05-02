package com.example.ht1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Account {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DB_Account instance;
    Cursor c = null;

    private DB_Account(Context context) {
        this.openHelper = new DBHelper(context);
    }

    public static DB_Account getInstance(Context context) {
        if (instance == null) {
            instance = new DB_Account(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getBalanceCred(String s) {
        c = db.rawQuery("select balance from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            buffer.append(""+balance);
        }
        return buffer.toString();
    }

    public String getBalanceDeb(String s) {
        c = db.rawQuery("select balance from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            buffer.append(""+balance);
        }
        return buffer.toString();
    }

    public void setBalanceCred(String s, float a){
        c = db.rawQuery("update credit_account set balance = '"+a+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setBalanceDeb(String s, float a){
        c = db.rawQuery("update debit_account set balance = '"+a+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getAccNumCred(int i) {
        c = db.rawQuery("select acc_num from credit_account where user_id = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String acc_num = c.getString(0);
            buffer.append(""+acc_num);
        }
        return buffer.toString();
    }

    public String getAccNumDeb(int i) {
        c = db.rawQuery("select acc_num from debit_account where user_id = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String acc_num = c.getString(0);
            buffer.append(""+acc_num);
        }
        return buffer.toString();
    }

    public void setAccNumCred(String s, int i){
        c = db.rawQuery("update credit_account set acc_num = '"+s+"' where user_id = '"+i+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setAccNumDeb(String s, int i){
        c = db.rawQuery("update debit_account set acc_num = '"+s+"' where user_id = '"+i+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getOpenDateCred(String s) {
        c = db.rawQuery("select open_date from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String open_date = c.getString(0);
            buffer.append(""+open_date);
        }
        return buffer.toString();
    }

    public String getOpenDateDeb(String s) {
        c = db.rawQuery("select open_date from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String open_date = c.getString(0);
            buffer.append(""+open_date);
        }
        return buffer.toString();
    }

    public void setOpenDateCred(String s, String o){
        c = db.rawQuery("update credit_account set open_date = '"+o+"' where acc_num= '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setOpenDateDeb(String s, String o){
        c = db.rawQuery("update debit_account set open_date = '"+o+"' where acc_num= '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getPayLimCred(String s) {
        c = db.rawQuery("select pay_lim from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String pay_lim = c.getString(0);
            buffer.append(""+pay_lim);
        }
        return buffer.toString();
    }

    public String getPayLimDeb(String s) {
        c = db.rawQuery("select pay_lim from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String pay_lim = c.getString(0);
            buffer.append(""+pay_lim);
        }
        return buffer.toString();
    }
    

    public String getUserIdCred(String s) {
        c = db.rawQuery("select user_id from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String user_id = c.getString(0);
            buffer.append(""+user_id);
        }
        return buffer.toString();
    }

    public String getUserIdDeb(String s) {
        c = db.rawQuery("select user_id from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String user_id = c.getString(0);
            buffer.append(""+user_id);
        }
        return buffer.toString();
    }

    public String payCred(String s, float sum) {
        c = db.rawQuery("select balance from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            float pay = Float.parseFloat(balance);
            pay = pay - sum;
            String b = String.valueOf(pay);
            buffer.append(""+b);
        }
        c = db.rawQuery("update credit_account set balance = '"+buffer+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
        return buffer.toString();
    }

    public String payDeb(String s, float sum) {
        c = db.rawQuery("select balance from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            float pay = Float.parseFloat(balance);
            pay = pay - sum;
            String b = String.valueOf(pay);
            buffer.append(""+b);
        }
        c = db.rawQuery("update debit_account set balance = '"+buffer+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
        return buffer.toString();
    }

    public String addMoneyCred(String s, float sum) {
        c = db.rawQuery("select balance from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            float pay = Float.parseFloat(balance);
            pay = pay + sum;
            String b = String.valueOf(pay);
            buffer.append(""+b);
        }
        c = db.rawQuery("update credit_account set balance = '"+buffer+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
        return buffer.toString();
    }

    public String addMoneyDeb(String s, float sum) {
        c = db.rawQuery("select balance from debit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String balance = c.getString(0);
            float pay = Float.parseFloat(balance);
            pay = pay + sum;
            String b = String.valueOf(pay);
            buffer.append(""+b);
        }
        c = db.rawQuery("update debit_account set balance = '"+buffer+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
        return buffer.toString();
    }

    public void setPayLimCred(String s, float n){
        c = db.rawQuery("update credit_account set pay_lim = '"+n+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setPayLimDeb(String s, float p){
        c = db.rawQuery("update debit_account set pay_lim = '"+p+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getCredit(String s) {
        c = db.rawQuery("select credit from credit_account where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String credit = c.getString(0);
            buffer.append(""+credit);
        }
        return buffer.toString();
    }

    public void setCredit(String s, float cr){
        c = db.rawQuery("update credit_account set credit = '"+cr+"' where acc_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getAreaCred(String s) {
        c = db.rawQuery("select area from credit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String area = c.getString(0);
            buffer.append(""+area);
        }
        return buffer.toString();
    }

    public String getAreaDeb(String s) {
        c = db.rawQuery("select area from debit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String area = c.getString(0);
            buffer.append(""+area);
        }
        return buffer.toString();
    }

    public void setAreaCred(String s, String a){
        c = db.rawQuery("update credit_card set area = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setAreaDeb(String s, String a){
        c = db.rawQuery("update debit_card set area = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getUseLimCred(String s) {
        c = db.rawQuery("select use_lim from credit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String use_lim = c.getString(0);
            buffer.append(""+use_lim);
        }
        return buffer.toString();
    }

    public String getUseLimDeb(String s) {
        c = db.rawQuery("select use_lim from debit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String use_lim = c.getString(0);
            buffer.append(""+use_lim);
        }
        return buffer.toString();
    }

    public void setUseLimCred(String s, float a){
        c = db.rawQuery("update credit_card set use_lim = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setUseLimDeb(String s, float a){
        c = db.rawQuery("update debit_card set use_lim = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getDrawLimCred(String s) {
        c = db.rawQuery("select draw_lim from credit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String draw_lim = c.getString(0);
            buffer.append(""+draw_lim);
        }
        return buffer.toString();
    }

    public String getDrawLimDeb(String s) {
        c = db.rawQuery("select draw_lim from debit_card where card_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String draw_lim = c.getString(0);
            buffer.append(""+draw_lim);
        }
        return buffer.toString();
    }

    public void setDrawLimCred(String s, float a){
        c = db.rawQuery("update credit_card set draw_lim = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public void setDrawLimDeb(String s, float a){
        c = db.rawQuery("update debit_card set draw_lim = '"+a+"' where card_num = '"+s+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getCardNumCred(String s) {
        c = db.rawQuery("select card_num from credit_card where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String card_num = c.getString(0);
            buffer.append(""+card_num);
        }
        return buffer.toString();
    }

    public String getCardNumDeb(String s) {
        c = db.rawQuery("select card_num from debit_card where acc_num = '"+s+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String card_num = c.getString(0);
            buffer.append(""+card_num);
        }
        return buffer.toString();
    }


}

