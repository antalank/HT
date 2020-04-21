package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //All arraylists are listed here
    ArrayList<Bank> bank = new ArrayList();
    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LongRunningTask().execute();
    }
    private class LongRunningTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println("Background prosess starts");
            System.out.println("Adding banks");
            bank.add(new Bank(0, "OKOYFIHH", "Osuuspankki", "Arkadiankatu 23, 00100 Helsinki", "1000500"));
            bank.add(new Bank(1, "NDEAFIHH", "Nordea", "Satamaradankatu 5, 00020 Helsinki", "02003000"));
            bank.add(new Bank(2, "HANDFIHH", "Handelsbanken", "Itämerenkatu 11-13, 00180 Helsinki", "01044411"));
            bank.add(new Bank(3, "ITELFIHH", "Säästöpankki", "Teollisuuskatu 33, 00510 Helsinki", "0207032450"));
            bank.add(new Bank(4, "SBANFIHH", "S-Pankki", "Fleminginkatu 34, 00510 Helsinki", "010765800"));

            System.out.println("Adding customers");
            System.out.println("Adding customers");
            System.out.println("Adding customers");

            customers.add(new Customer("kissa", 566789, "OKOYFIHH", "170287-43667", "Anna Pukki", 33, "Katajakatu 9", "53810"));
            customers.add(new Customer("kala", 612009, "NDEAFIHH", "300196-1098", "Pete Peteläinen", 24, "Mannerheimintie 2", "23127"));
            customers.add(new Customer("hevonen", 345123, "HANDFIHH", "051201A3498", "Matti Meikäläinen", 18, "Liisankatu 7", "41412"));
            customers.add(new Customer("koira", 998477, "ITELFIHH", "101066-1199", "Maija Mutteri", 58, "Skinnarilankatu 5", "21421"));
            customers.add(new Customer("lintu", 535477, "SBANFIHH", "230391-6560", "Liisa Korhonen", 29, "Helsingintie 7", "02432"));

            System.out.println("Adding accounts");
            //TODO "Lisää asiakkaat ja kortit"



            System.out.println("Background prosess ends");

            return null;
        }
    }
}
