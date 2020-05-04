package com.example.ht1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.ht1.SHA512.getSHA512;
import static com.example.ht1.SHA512.salt;
import static com.example.ht1.RandomStringGenerator.getNewSalt;

public class DB_Customer {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    public static ArrayList<Salt> salt_list = new ArrayList<>();
    private static DB_Customer instance;
    Cursor c = null;

    private DB_Customer(Context context){
        this.openHelper = new DBHelper(context);
    }

    public static DB_Customer getInstance(Context context){
        if (instance == null){
            instance = new DB_Customer(context);
        }
        return instance;
    }

    public static DB_Customer getInstance2(Context context){
        if (instance == null){
            instance = new DB_Customer(context);
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

    public String getName(int u){
        c = db.rawQuery("select name from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String name = c.getString(0);
            buffer.append(""+name);
        }
        return buffer.toString();
    }

    public void setName(int u, String n){
        c = db.rawQuery("update customer set name = '"+n+"' where user_id = '"+u+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getAddress(int u){
        c = db.rawQuery("select address from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String address = c.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();
    }

    public void setAddress(int u, String a){
        c = db.rawQuery("update customer set address = '"+a+"' where user_id = '"+u+"'", null);
        c.moveToFirst();
        c.close();
    }


    public String getPostalCode(int u){
        c = db.rawQuery("select postal_code from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String postal_code = c.getString(0);
            buffer.append(""+postal_code);
        }
        return buffer.toString();
    }

    public void setPostalCode(int u, String pc){
        c = db.rawQuery("update customer set postal_code = '"+pc+"' where user_id = '"+u+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getNumber(int u){
        c = db.rawQuery("select telephone from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String telephone = c.getString(0);
            buffer.append(""+telephone);
        }
        return buffer.toString();
    }

    public void setNumber(int u, String t){
        c = db.rawQuery("update customer set telephone = '"+t+"' where user_id = '"+u+"'", null);
        c.moveToFirst();
        c.close();
    }

    public String getBIC(int u){
        c = db.rawQuery("select BIC from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String bic = c.getString(0);
            buffer.append(""+bic);
        }
        return buffer.toString();
    }

    public String getPassword(int u){
        c = db.rawQuery("select password from customer where user_id = '"+u+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String password = c.getString(0);
            buffer.append(""+password);
        }
        return buffer.toString();
    }

    public void addCustomer(int u, String s, String n, String t, String a, String p, String pc, String b){
        String randomSalt = getNewSalt();
        String salt = salt(p, randomSalt);
        salt_list.add(new Salt(salt, u));
        String pass = getSHA512(p, salt);
        c = db.rawQuery("insert into customer values("+u+", '"+s+"', '"+n+"', '"+t+"', '"+a+"', '"+pass+"', '"+pc+"', '"+b+"')", null);
        c.moveToFirst();
        c.close();
    }

    public void changePassword(int u, String p){
        String randomSalt = getNewSalt();
        String salt = salt(p, randomSalt);
        salt_list.add(new Salt(salt, u));
        String pass = getSHA512(p, salt);
        c = db.rawQuery("update customer set password = '"+pass+"' where user_id = '"+u+"'", null);
        c.moveToFirst();
        c.close();
    }

    public ArrayList getSaltList(){
        return salt_list; //TODO Yritä
    }
}
