package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    EditText giveUser;
    EditText givePass;
    Context context = null;

    ArrayList<Customer> customers = new ArrayList<>();
    public static int userIdSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        giveUser = findViewById(R.id.giveUserName);
        givePass = findViewById(R.id.givePassword);
        context = Main2Activity.this;
        new LongRunningTask().execute();
    }
    private class LongRunningTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println("Background prosess starts");
            System.out.println("Adding customers");
            customers.add(new Customer("kissa", 566789, "OKOYFIHH", "170287-43667", "Anna Pukki", 043422332, "Katajakatu 9", "53810"));
            customers.add(new Customer("kala", 612009, "NDEAFIHH", "300196-1098", "Pete Peteläinen", 04342232, "Mannerheimintie 2", "23127"));
            customers.add(new Customer("hevonen", 345123, "HANDFIHH", "051201A3498", "Matti Meikäläinen", 043423213, "Liisankatu 7", "41412"));
            customers.add(new Customer("koira", 998477, "ITELFIHH", "101066-1199", "Maija Mutteri", 04342325324, "Skinnarilankatu 5", "21421"));
            customers.add(new Customer("lintu", 535477, "SBANFIHH", "230391-6560", "Liisa Korhonen", 901923124, "Helsingintie 7", "02432"));
            System.out.println("Background prosess ends");

            return null;
        }
    }
    public void loadActivity2(View v){
        int apply = 0;
        int user;
        String password;
        String gU = giveUser.getText().toString();
        int gUser = Integer.parseInt(gU);
        String gPassword = givePass.getText().toString();

        for (Customer cust : customers) {
            System.out.println();
            user = cust.user_id;
            password = cust.password;
            System.out.println(user + " " + password);
            if (gUser == user && gPassword.equals(password)) {
                    apply++;
                    userIdSelection = user;
            }
        }
        if (apply > 0) {
            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
            startActivity(intent);
        } else {

        }
    }
}
