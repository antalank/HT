package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //All arraylists are listed here
    ArrayList<Bank> bank = new ArrayList();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();

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

            customers.add(new Customer("kissa", 566789, "OKOYFIHH", "170287-43667", "Anna Pukki", 33, "Katajakatu 9", "53810"));
            customers.add(new Customer("kala", 612009, "NDEAFIHH", "300196-1098", "Pete Peteläinen", 24, "Mannerheimintie 2", "23127"));
            customers.add(new Customer("hevonen", 345123, "HANDFIHH", "051201A3498", "Matti Meikäläinen", 18, "Liisankatu 7", "41412"));
            customers.add(new Customer("koira", 998477, "ITELFIHH", "101066-1199", "Maija Mutteri", 58, "Skinnarilankatu 5", "21421"));
            customers.add(new Customer("lintu", 535477, "SBANFIHH", "230391-6560", "Liisa Korhonen", 29, "Helsingintie 7", "02432"));

            System.out.println("Adding accounts");
            //TODO "Lisää asiakkaat ja kortit"
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 41", 3894.55f, 566789, "31.12.2017", 6500f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 42", 22.56f, 612009, "2.12.2020", 1200f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 43", 1028.44f, 345123, "8.10.2019", 3000f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 44", 9876.55f, 998477, "2.9.2018", 600f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 45", 12098.55f, 535477, "9.11.2011", 9000f));

            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 31", 5789.66f, 566789, "12.12.2017", 5000f, 3000f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 32", 2987.44f, 612009, "2.4.2020", 1000f, 1000f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 33", 3006.22f, 345123, "5.10.2019", 2500f, 500f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 34", 395.50f, 998477, "2.8.2018", 300f, 1250f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 35", 11980.33f, 535477, "1.4.2015", 10000f, 4000f));

            /// adding cards
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 41", 3894.55f, 566789, "31.12.2017", 6500f, 1200f, 500f, "FIN", "5153198799466732"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 42", 22.56f, 612009, "2.12.2020", 1200f, 1000f, 200f, "EU", "5153198799466733"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 43", 1028.44f, 345123, "8.10.2019", 3000f, 2000f, 1000f, "WORLD", "5153198799466734"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 44", 9876.55f, 998477, "2.9.2018", 600f, 5000f, 600f, "EU", "5153198799466735"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 45", 12098.55f, 535477, "9.11.2011", 9000f, 3400f, 500f, "EU", "5153198799466736"));

            credit_cards.add(new Credit_card("FI55 3131 1000 6789 31", 5789.66f, 566789, "12.12.2017", 5000f, 3000f, 1200f, 500f, "FIN", "5153198799466792"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 32", 2987.44f, 612009, "2.4.2020", 1000f, 1000f, 1000f, 200f, "EU", "5153198799466793"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 33", 3006.22f, 345123, "5.10.2019", 2500f, 500f, 5000f, 600f, "EU", "5153198799466794"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 34", 395.50f, 998477, "2.8.2018", 300f, 1250f, 5000f, 600f, "EU", "5153198799466795"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 35", 11980.33f, 535477, "1.4.2015", 10000f, 4000f, 3400f, 500f, "EU", "5153198799466796"));

            System.out.println("Background prosess ends");

            return null;
        }
    }
}
