package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //All arraylists are listed here
    ArrayList<Bank> bank = new ArrayList();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<AccountEvent> account_event = new ArrayList<>();

    public static int bankSelection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Spinner 1: banks
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.banks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
            customers.add(new Customer("kissa", 566789, "OKOYFIHH", "170287-43667", "Anna Pukki", 33, "Katajakatu 9", "53810"));
            customers.add(new Customer("kala", 612009, "NDEAFIHH", "300196-1098", "Pete Peteläinen", 24, "Mannerheimintie 2", "23127"));
            customers.add(new Customer("hevonen", 345123, "HANDFIHH", "051201A3498", "Matti Meikäläinen", 18, "Liisankatu 7", "41412"));
            customers.add(new Customer("koira", 998477, "ITELFIHH", "101066-1199", "Maija Mutteri", 58, "Skinnarilankatu 5", "21421"));
            customers.add(new Customer("lintu", 535477, "SBANFIHH", "230391-6560", "Liisa Korhonen", 29, "Helsingintie 7", "02432"));

            System.out.println("Adding accounts");
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

            // adding cards
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

            //adding accountevents
            account_event.add(new AccountEvent(0, "FI55 3131 1000 6789 31", "1.1.2020", 223, "FI55 3131 1000 6789 32", "Pete Peteläinen"));
            account_event.add(new AccountEvent(1, "FI55 3131 1000 6789 31", "2.1.2020", -12, "FI55 3131 1000 6789 33", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(2, "FI55 3131 1000 6789 31", "3.1.2020", -45, "FI55 3131 1000 6789 34", "Maija Mutteri"));
            account_event.add(new AccountEvent(3, "FI55 3131 1000 6789 32", "4.1.2020", -4, "FI55 3131 1000 6789 31", "Anna Pukki"));
            account_event.add(new AccountEvent(4, "FI55 3131 1000 6789 32", "5.1.2020", 45, "FI55 3131 1000 6789 33", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(5, "FI55 3131 1000 6789 32", "6.1.2020", 23, "FI55 3131 1000 6789 34", "Maija Mutteri"));
            account_event.add(new AccountEvent(6, "FI55 3131 1000 6789 33", "7.1.2020", 8, "FI55 3131 1000 6789 35", "Liisa Korhonen"));
            account_event.add(new AccountEvent(7, "FI55 3131 1000 6789 33", "8.1.2020", -5, "FI55 3131 1000 6789 41", "Anna Pukki"));
            account_event.add(new AccountEvent(8, "FI55 3131 1000 6789 33", "9.1.2020", 9, "FI55 3131 1000 6789 44", "Maija Mutteri"));
            account_event.add(new AccountEvent(9, "FI55 3131 1000 6789 34", "10.1.2020", 2, "FI55 3131 1000 6789 31", "Anna Pukki"));
            account_event.add(new AccountEvent(10, "FI55 3131 1000 6789 34", "11.1.2020", 7, "FI55 3131 1000 6789 44", "Maija Mutteri"));
            account_event.add(new AccountEvent(11, "FI55 3131 1000 6789 34", "12.1.2020", -4, "FI55 3131 1000 6789 32", "Pete Peteläinen"));
            account_event.add(new AccountEvent(12, "FI55 3131 1000 6789 35", "13.1.2020", 8, "FI55 3131 1000 6789 43", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(13, "FI55 3131 1000 6789 35", "14.1.2020", -1, "FI55 3131 1000 6789 44", "Maija Mutteri"));
            account_event.add(new AccountEvent(14, "FI55 3131 1000 6789 35", "15.1.2020", 6, "FI55 3131 1000 6789 34", "Maija Mutteri"));
            account_event.add(new AccountEvent(15, "FI55 3131 1000 6789 41", "16.1.2020", 3, "FI55 3131 1000 6789 33", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(16, "FI55 3131 1000 6789 41", "17.1.2020", -8, "FI55 3131 1000 6789 35", "Liisa Korhonen"));
            account_event.add(new AccountEvent(17, "FI55 3131 1000 6789 41", "18.1.2020", 4, "FI55 3131 1000 6789 42", "Pete Peteläinen"));
            account_event.add(new AccountEvent(18, "FI55 3131 1000 6789 42", "19.1.2020", 99, "FI55 3131 1000 6789 35", "Liisa Korhonen"));
            account_event.add(new AccountEvent(19, "FI55 3131 1000 6789 42", "20.1.2020", -33, "FI55 3131 1000 6789 43", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(20, "FI55 3131 1000 6789 42", "21.1.2020", 77, "FI55 3131 1000 6789 44", "Maija Mutteri"));
            account_event.add(new AccountEvent(21, "FI55 3131 1000 6789 43", "22.1.2020", -44, "FI55 3131 1000 6789 41", "Anna Pukki"));
            account_event.add(new AccountEvent(22, "FI55 3131 1000 6789 43", "23.1.2020", 55, "FI55 3131 1000 6789 31", "Anna Pukki"));
            account_event.add(new AccountEvent(23, "FI55 3131 1000 6789 43", "24.1.2020", -99, "FI55 3131 1000 6789 32", "Pete Peteläinen"));
            account_event.add(new AccountEvent(24, "FI55 3131 1000 6789 44", "25.1.2020", 1000, "FI55 3131 1000 6789 31", "Anna Pukki"));
            account_event.add(new AccountEvent(25, "FI55 3131 1000 6789 44", "26.1.2020", -3, "FI55 3131 1000 6789 35", "Liisa Korhonen"));
            account_event.add(new AccountEvent(26, "FI55 3131 1000 6789 44", "27.1.2020", 4324, "FI55 3131 1000 6789 41", "Anna Pukki"));
            account_event.add(new AccountEvent(27, "FI55 3131 1000 6789 45", "28.1.2020", -43,	"FI55 3131 1000 6789 44", "Maija Mutteri"));
            account_event.add(new AccountEvent(28, "FI55 3131 1000 6789 45", "29.1.2020", 23, "FI55 3131 1000 6789 43", "Matti Meikäläinen"));
            account_event.add(new AccountEvent(29, "FI55 3131 1000 6789 45", "30.1.2020", 1, "FI55 3131 1000 6789 33", "Matti Meikäläinen"));
            System.out.println("Background prosess ends");

            return null;
        }
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bankSelection = parent.getSelectedItemPosition();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
