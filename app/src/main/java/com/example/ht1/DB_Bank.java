package com.example.ht1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Bank {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DB_Bank instance;
    Cursor c = null;

    private DB_Bank(Context context){
        this.openHelper = new DBHelper(context);
    }

    public static DB_Bank getInstance(Context context){
        if (instance == null){
            instance = new DB_Bank(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db != null){
            this.db.close();
        }
    }


    public String getBIC(int i) {
        c = db.rawQuery("select BIC from bank where id = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String BIC = c.getString(0);
            buffer.append(""+BIC);
        }
        return buffer.toString();
    }

    public String getName(String i) {
        c = db.rawQuery("select name from bank where BIC = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String name = c.getString(0);
            buffer.append(""+name);
        }
        return buffer.toString();
    }
    public String getAddress(String i) {
        c = db.rawQuery("select address from bank where BIC = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String address = c.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();
    }
    public  String getPhone(String i) {
        c = db.rawQuery("select phone from bank where BIC = '"+i+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String phone = c.getString(0);
            buffer.append(""+phone);
        }
        return buffer.toString();
    }
}
