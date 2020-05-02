package com.example.ht1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;
import static com.example.ht1.MainActivity.bankNameSelection;
import static com.example.ht1.MainActivity.instance;

public class Main4Activity extends BaseActivity {
    Context context = null;
    TextView textView4BankName;
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();

    //public static Main4Activity instance;

    String yearString;
    String monthString;
    String dayString;
    String dateString;
    int dateInt;

    int id;
    int date_int;
    float sum;
    String from_account;
    String from_name;
    String to_account;
    String to_name;

    int a = 0;

    ArrayList<PayLog> paylog = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView4BankName = findViewById(R.id.textView4BankName);
        //     textView4BankName.setText("Welcome to " + bankNameSelection + "!");

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_main4, null, false);
        mDrawer.addView(contentView, 0);
        account_event =  MainActivity.getInstance().getAccountEventlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();
        new LongRunningTask().execute();
        //instance = this;
    }
    //public static Main4Activity getInstance() { return instance; }

    private class LongRunningTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println("Background prosess starts");


            System.out.println("Background prosess ends");

            return null;
        }
    }
    public void buttonBack(View view) {
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
            account_event.add(new AccountEvent(1, from_account, dateString, -(sum), to_account, to_name));
            account_event.add(new AccountEvent(2, to_account, dateString, sum, from_account, userNameSelection));

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

    //public ArrayList<PayLog> getPaylog() { return paylog; }

}
