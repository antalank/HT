package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;

public class Main3Activity extends AppCompatActivity {
    TextView textView1;
    TextView textView5;
    TextView textViewError;
    EditText editText1;
    String text = "";

    public static Main3Activity instance;

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

    ArrayList<PayLog> paylog = new ArrayList<>();
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView1 = findViewById(R.id.textViewOtsikko);
        textView5 = findViewById(R.id.textView5);
        textView5.setText("Import keycode list number");
        textViewError = findViewById(R.id.textViewError);
        textViewError.setText("");
        editText1 = findViewById(R.id.editText1);

        randomNumber();
        instance = this;

    }
    public static Main3Activity getInstance() { return instance; }

    public void randomNumber() {
        double rand = Math.random();
        rand = rand * 999999 + 100000;
        int IntRand = (int) rand;
        System.out.println(IntRand);

        text = Integer.toString(IntRand);
        textView1.setText(text);
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
    public void loadActivity3(View v){
        String number = editText1.getText().toString();
        if (number.equals(text)) {
            execute();
            Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
            startActivity(intent);
        } else if (number != text) {
            textViewError.setText("Wrong number!, try again");
        }
    }
    public ArrayList<PayLog> getPaylog() { return paylog; }
}
