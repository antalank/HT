package com.example.ht1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String dbName = "olio.db";

    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table customer (user_id integer primary key, SSN text, name text, telephone text, address text, password text, postal_code text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS customer");
        onCreate(db);
    }

    public String addData(int u, String s, String n, String t, String a, String p, String pc) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user_id", u);
        cv.put("SSN", s);
        cv.put("name", n);
        cv.put("telephone", t);
        cv.put("address", a);
        cv.put("password", p);
        cv.put("postal_code", pc);

        long res = db.insert("customer", null, cv);

        if (res == -1){
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }
}
