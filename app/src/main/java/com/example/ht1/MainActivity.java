package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<PayLog> paylog = new ArrayList<>();

    Context context = null;

    public static MainActivity instance;
    public static int bankSelection;
    public static String bankBicSelection;
    public static String bankNameSelection;


    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        //Spinner 1: banks
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.banks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        instance = this;
        
    }
    public static MainActivity getInstance() {
        return instance;
    }

    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bankSelection = parent.getSelectedItemPosition();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void loadActivity1(View v){
        DB_Bank dbAccess = DB_Bank.getInstance(getApplicationContext());
        dbAccess.open();
        int i;
        bankBicSelection = dbAccess.getBIC(bankSelection);
        bankNameSelection = dbAccess.getName(bankBicSelection);
        /////////////////////// Reading the file and save it to Arraylist
        //Account_events

        try {
            InputStream ins = context.openFileInput("AccountEvents.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String merkkijono = "";

            while ((merkkijono=buf.readLine()) != null) {

                System.out.println(merkkijono);
                //Parse
                String h = "[,]";
                String[] separated = merkkijono.split(h);
                account_event.add(new AccountEvent(Integer.parseInt(separated[0]), separated[1], separated[2], Float.parseFloat(separated[3]), separated[4], separated[5]));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist
        } finally {
            System.out.println("Readed.");
        }
        //Paylog
        try {
            InputStream ins = context.openFileInput("PayLog.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String line = "";

            while ((line=buf.readLine()) != null) {
                System.out.println(line);
                //Parse
                String h = "[,]";
                String[] separated = line.split(h);
                paylog.add(new PayLog(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]), Float.parseFloat(separated[2]),separated[3], separated[4], separated[5], separated[6]));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist
        } finally {
            System.out.println("Readed: Paylog");
        }
        //Debit_account
        try {
            InputStream ins = context.openFileInput("Debit_accounts.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String line = "";

            while ((line=buf.readLine()) != null) {
                System.out.println(line);
                //Parse
                String h = "[,]";
                String[] separated = line.split(h);
                debit_accounts.add(new Debit_account(separated[0], Float.parseFloat(separated[1]), Integer.parseInt(separated[2]),separated[3], Float.parseFloat(separated[4])));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist if file do not exists
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 41", 3894.55f, 566789, "31.12.2017", 6500f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 42", 22.56f, 612009, "2.12.2020", 1200f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 43", 1028.44f, 345123, "8.10.2019", 3000f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 44", 9876.55f, 998477, "2.9.2018", 600f));
            debit_accounts.add(new Debit_account("FI55 3131 1000 6789 45", 12098.55f, 535477, "9.11.2011", 9000f));

        } finally {
            System.out.println("Readed: Debit account");
        }
        //Credit_account
        try {
            InputStream ins = context.openFileInput("Credit_accounts.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String line = "";

            while ((line=buf.readLine()) != null) {
                System.out.println(line);
                //Parse
                String h = "[,]";
                String[] separated = line.split(h);
                credit_accounts.add(new Credit_account(separated[0], Float.parseFloat(separated[1]), Integer.parseInt(separated[2]),separated[3], Float.parseFloat(separated[4]), Float.parseFloat(separated[5])));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 31", 5789.66f, 566789, "12.12.2017", 5000f, 3000f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 32", 2987.44f, 612009, "2.4.2020", 1000f, 1000f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 33", 3006.22f, 345123, "5.10.2019", 2500f, 500f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 34", 395.50f, 998477, "2.8.2018", 300f, 1250f));
            credit_accounts.add(new Credit_account("FI55 3131 1000 6789 35", 11980.33f, 535477, "1.4.2015", 10000f, 4000f));

        } finally {
            System.out.println("Readed: Credit account");
        }
        //Debit_card
        try {
            InputStream ins = context.openFileInput("Debit_cards.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String line = "";

            while ((line=buf.readLine()) != null) {
                System.out.println(line);
                //Parse
                String h = "[,]";
                String[] separated = line.split(h);
                debit_cards.add(new Debit_card(separated[0], Float.parseFloat(separated[1]), Integer.parseInt(separated[2]),separated[3], Float.parseFloat(separated[4]), Float.parseFloat(separated[5]), Float.parseFloat(separated[6]), separated[7], separated[8]));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 41", 3894.55f, 566789, "31.12.2017", 6500f, 1200f, 500f, "FIN", "5153198799466732"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 42", 22.56f, 612009, "2.12.2020", 1200f, 1000f, 200f, "EU", "5153198799466733"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 43", 1028.44f, 345123, "8.10.2019", 3000f, 2000f, 1000f, "WORLD", "5153198799466734"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 44", 9876.55f, 998477, "2.9.2018", 600f, 5000f, 600f, "EU", "5153198799466735"));
            debit_cards.add(new Debit_card("FI55 3131 1000 6789 45", 12098.55f, 535477, "9.11.2011", 9000f, 3400f, 500f, "EU", "5153198799466736"));

        } finally {
            System.out.println("Readed: Debit card");
        }
        //Credit_card
        try {
            InputStream ins = context.openFileInput("Credit_cards.csv");
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String line = "";

            while ((line=buf.readLine()) != null) {
                System.out.println(line);
                //Parse
                String h = "[,]";
                String[] separated = line.split(h);
                credit_cards.add(new Credit_card(separated[0], Float.parseFloat(separated[1]), Integer.parseInt(separated[2]),separated[3], Float.parseFloat(separated[4]), Float.parseFloat(separated[5]), Float.parseFloat(separated[6]), Float.parseFloat(separated[7]), separated[8], separated[9]));
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Reading not allowed.");
            //To Arraylist
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 31", 5789.66f, 566789, "12.12.2017", 5000f, 3000f, 1200f, 500f, "FIN", "5153198799466792"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 32", 2987.44f, 612009, "2.4.2020", 1000f, 1000f, 1000f, 200f, "EU", "5153198799466793"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 33", 3006.22f, 345123, "5.10.2019", 2500f, 500f, 5000f, 600f, "EU", "5153198799466794"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 34", 395.50f, 998477, "2.8.2018", 300f, 1250f, 5000f, 600f, "EU", "5153198799466795"));
            credit_cards.add(new Credit_card("FI55 3131 1000 6789 35", 11980.33f, 535477, "1.4.2015", 10000f, 4000f, 3400f, 500f, "EU", "5153198799466796"));

        } finally {
            System.out.println("Readed: Credit cards");
        }

        /////////////////////// Reading the file
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        dbAccess.close();
    }
    public ArrayList<AccountEvent> getAccountEventlist() { return account_event; }
    public ArrayList<Debit_account> getDebitaccountlist() { return debit_accounts; }
    public ArrayList<Credit_account> getCreditaccountlist() { return credit_accounts; }
    public ArrayList<Credit_card> getCreditCards() { return credit_cards; }
    public ArrayList<Debit_card> getDebitCards() { return debit_cards; }
    public ArrayList<PayLog> getPaylog() { return paylog; }
}

