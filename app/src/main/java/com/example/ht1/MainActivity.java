package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //All arraylists are listed here
    ArrayList<Bank> bank = new ArrayList();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<PayLog> paylog = new ArrayList<>();

    public static MainActivity instance;
    public static int bankSelection;
    public static String bankBicSelection;
    public static String bankNameSelection;

    String yearString;
    String monthString;
    String dayString;
    String dateString;
    String date_commit;
    int dateInt;

    int id;
    int date_int;
    float sum;
    String from_account;
    String from_name;
    String to_account;
    String to_name;

    int a = 0;
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

        instance = this;

        new LongRunningTask().execute();
    }
    public static MainActivity getInstance() {
        return instance;
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
    public void execute() {
        for (PayLog p : paylog) {
            System.out.println(p.getIde() + " " + p.getDate_() + " " +  p.getSum() + " " +  p.getFrom_account() + " " +  p.getFrom_name() + " " +  p.getTo_account() + " " +  p.getTo_name());
        }
        account_event =  MainActivity.getInstance().getAccountEventlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(("dd-MM-yyyy"));
        String formattedDate = df.format(c);
        String[] separated = formattedDate.split("-");
        dayString = separated[0];
        monthString = separated[1];
        if (monthString.equals("01")) {
            monthString = "1";
        } else if (monthString.equals("02")) {
            monthString = "2";
        } else if (monthString.equals("03")) {
            monthString = "3";
        } else if (monthString.equals("04")) {
            monthString = "4";
        } else if (monthString.equals("05")) {
            monthString = "5";
        } else if (monthString.equals("06")) {
            monthString = "6";
        } else if (monthString.equals("07")) {
            monthString = "7";
        } else if (monthString.equals("08")) {
            monthString = "8";
        } else if (monthString.equals("09")) {
            monthString = "9";
        }
        yearString = separated[2];
        dateString = (yearString + monthString + dayString);
        dateInt = Integer.parseInt(dateString);
        System.out.println(dateInt);
        System.out.println("222222222222222222222222222222222222");
        // pay-method here
        for (PayLog p : paylog) {
            System.out.println(p.getDate_());
            if (dateInt >= p.getDate_()) {
                System.out.println("#####################################");
                if (userIdSelection == p.getIde()) {
                    System.out.println("Accountevent will be paid");
                    id = p.getIde();
                    date_int = p.getDate_();
                    sum = p.getSum();
                    from_account = p.getFrom_account();
                    from_name = p.getFrom_name();
                    to_account = p.getTo_account();
                    to_name = p.getTo_name();
                    a++;
                }
            }
        }
        if (a > 0) {
            date_commit = (dayString + "." + monthString + "." + yearString);
            account_event.add(new AccountEvent(1, from_account, date_commit, -(sum), to_account, to_name));
            account_event.add(new AccountEvent(2, to_account, date_commit, sum, from_account, userNameSelection));

            for (Debit_account d_a : debit_accounts) {
                if (from_account.equals(d_a.getAccountNumber())) {
                    d_a.pay(sum);
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (from_account.equals(c_a.getAccountNumber())) {
                    c_a.pay(sum);
                }
            }
            for (Debit_account d_a : debit_accounts) {
                if (d_a.getAccountNumber().equals(to_account)) {
                    d_a.addMoney(sum);
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (c_a.getAccountNumber().equals(to_account)) {
                    c_a.addMoney(sum);
                }
            }
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
    public void loadActivity1(View v){
        DB_Bank dbAccess = DB_Bank.getInstance(getApplicationContext());
        dbAccess.open();
        int i;
        bankBicSelection = dbAccess.getBIC(bankSelection);
        bankNameSelection = dbAccess.getName(bankBicSelection);
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    public ArrayList<Bank> getBanklist() { return bank; }
    public ArrayList<AccountEvent> getAccountEventlist() { return account_event; }
    public ArrayList<Debit_account> getDebitaccountlist() { return debit_accounts; }
    public ArrayList<Credit_account> getCreditaccountlist() { return credit_accounts; }
    public ArrayList<Credit_card> getCreditCards() { return credit_cards; }
    public ArrayList<Debit_card> getDebitCards() { return debit_cards; }
    public ArrayList<PayLog> getPaylog() { return paylog; }
    public void printCreditCards(){
        for (int i = 0; i < credit_cards.size(); i++) {
            System.out.println(credit_cards.get(i).getDrawLimit());
            System.out.println(credit_cards.get(i).getUseLimit());
            System.out.println(credit_cards.get(i).getArea());
        }
    }
}

