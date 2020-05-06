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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


//class variables are listed here
import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;
import static com.example.ht1.MainActivity.bankNameSelection;
import static com.example.ht1.MainActivity.bankBicSelection;

public class Main4Activity extends BaseActivity {
    Context context = null;
    TextView textView4BankName;
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<PayLog> paylog = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_main4, null, false);
        mDrawer.addView(contentView, 0);

        ///setting the name of the bank to the activity
        System.out.println(bankNameSelection);
        textView4BankName = (TextView) findViewById(R.id.textView4BankName);
        textView4BankName.setText(bankNameSelection);

        //getting the data to the lists
        account_event =  MainActivity.getInstance().getAccountEventlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();
        new LongRunningTask().execute();
    }

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
    public void LogOut(View view) {
        System.out.println(context);
        //Writing AccountEvents to csv-file
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("AccountEvents.csv", Context.MODE_PRIVATE));
            int id = 0;
            String account_num = "";
            String day = "";
            float sum = 0;
            String from_account_num = "";
            String from_account_name = "";
            int i = 0;
            for (AccountEvent a_e : account_event) {
                id = a_e.getId();
                account_num = a_e.getAccount_num();
                day = a_e.getDay();
                sum = a_e.getSum();
                from_account_num = a_e.getFrom_account_num();
                from_account_name = a_e.getFrom_account_name();
                i++;
                System.out.println(i);
                ows.write(id + "," + account_num + "," + day + "," + sum + "," + from_account_num + "," + from_account_name + "\n");
            }
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: AccountEvents###");
        }
        //Writing Paylog to csv-file
        try {
            OutputStreamWriter ows2 = new OutputStreamWriter(context.openFileOutput("PayLog.csv", Context.MODE_PRIVATE));
            int ide;
            int date_;
            float sum;
            String from_account;
            String from_name;
            String to_account;
            String to_name;
            int i = 0;
            for (PayLog p_l : paylog) {
                ide = p_l.getIde();
                date_ = p_l.getDate_();
                sum = p_l.getSum();
                from_account = p_l.getFrom_account();
                from_name = p_l.getFrom_name();
                to_account = p_l.getTo_account();
                to_name = p_l.getTo_name();
                i++;
                System.out.println(i);
                ows2.write(ide + "," + date_ + "," + sum + "," + from_account + "," + from_name + "," + to_account + "," + to_name + "\n");
            }
            ows2.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: PayLog###");
        }

        //Writing Debit_account to csv-file
        try {
            OutputStreamWriter ows3 = new OutputStreamWriter(context.openFileOutput("Debit_accounts.csv", Context.MODE_PRIVATE));
            String acc_num;
            float balance;
            int user_id;
            String open_date;
            float pay_lim;
            int i = 0;
            for (Debit_account d_a : debit_accounts) {
                acc_num = d_a.getAccountNumber();
                balance = d_a.getBalance();
                user_id = d_a.getUserID();
                open_date = d_a.getOpenDate();
                pay_lim = d_a.getPayLim();
                i++;
                System.out.println(i);
                ows3.write(acc_num + "," + balance + "," + user_id + "," + open_date + "," + pay_lim + "\n");
            }
            ows3.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: Debit_account###");
        }

        //Writing Credit_account to csv-file
        try {
            OutputStreamWriter ows4 = new OutputStreamWriter(context.openFileOutput("Credit_accounts.csv", Context.MODE_PRIVATE));
            String acc_num;
            float balance;
            int user_id;
            String open_date;
            float pay_lim;
            float credit;
            int i = 0;
            for (Credit_account c_a : credit_accounts) {
                acc_num = c_a.getAccountNumber();
                balance = c_a.getBalance();
                user_id = c_a.getUserID();
                open_date = c_a.getOpenDate();
                pay_lim = c_a.getPayLim();
                credit = c_a.getCredit();
                i++;
                System.out.println(i);
                ows4.write(acc_num + "," + balance + "," + user_id + "," + open_date + "," + pay_lim + "," + credit + "\n");
            }
            ows4.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: Credit_account###");
        }
        //Writing Debit_card to csv-file
        try {
            OutputStreamWriter ows5 = new OutputStreamWriter(context.openFileOutput("Debit_cards.csv", Context.MODE_PRIVATE));
            String acc_num;
            float balance;
            int user_id;
            String open_date;
            float pay_lim;
            float use_lim;
            float draw_lim;
            String area;
            String card_num;
            int i = 0;
            for (Debit_card d_c : debit_cards) {
                acc_num = d_c.getAccountNumber();
                balance = d_c.getBalance();
                user_id = d_c.getUserID();
                open_date = d_c.getOpenDate();
                pay_lim = d_c.getPayLim();
                use_lim = d_c.getUseLimit();
                draw_lim = d_c.getDrawLimit();
                area = d_c.getArea();
                card_num = d_c.getCardNum();
                i++;
                System.out.println(i);
                ows5.write(acc_num + "," + balance + "," + user_id + "," + open_date + "," + pay_lim + "," + use_lim + "," + draw_lim + "," + area + "," + card_num + "\n");
            }
            ows5.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: Debit_cards###");
        }
        //Writing Credit_card to csv-file
        try {
            OutputStreamWriter ows6 = new OutputStreamWriter(context.openFileOutput("Credit_cards.csv", Context.MODE_PRIVATE));
            String acc_num;
            float balance;
            int user_id;
            String open_date;
            float pay_lim;
            float credit;
            float use_lim;
            float draw_lim;
            String area;
            String card_num;
            int i = 0;
            for (Credit_card c_c : credit_cards) {
                acc_num = c_c.getAccountNumber();
                balance = c_c.getBalance();
                user_id = c_c.getUserID();
                open_date = c_c.getOpenDate();
                pay_lim = c_c.getPayLim();
                credit = c_c.getCredit();
                use_lim = c_c.getUseLimit();
                draw_lim = c_c.getDrawLimit();
                area = c_c.getArea();
                card_num = c_c.getCardNum();
                i++;
                System.out.println(i);
                ows6.write(acc_num + "," + balance + "," + user_id + "," + open_date + "," + pay_lim + "," + credit + "," + use_lim + "," + draw_lim + "," + area + "," + card_num + "\n");
            }
            ows6.close();
        } catch (IOException e) {
            Log.e("IOException", "Write not allowed");
        } finally {
            System.out.println("###Wrote: Credit_cards###");
        }
    }
}

