package com.example.ht1;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {

    private static final String dbName = "olio.db";
    private static final int version = 1;

    public DBHelper(Context context) {
        super(context, dbName, null, version);
    }
}

