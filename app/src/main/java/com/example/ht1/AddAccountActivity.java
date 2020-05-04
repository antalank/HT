package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static com.example.ht1.Main2Activity.userIdSelection;


public class AddAccountActivity extends AppCompatActivity {

    TextView number_view;
    TextView balance_view;
    TextView date_view;
    EditText paylimit;
    EditText creditlimit;
    Button create;
    Button select;

    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        setTitle("Create new account");

        number_view = findViewById(R.id.textView9);
        balance_view = findViewById(R.id.textView14);
        date_view = findViewById(R.id.textView15);
        paylimit = findViewById(R.id.editText2);
        creditlimit = findViewById(R.id.editText5);
        create = findViewById(R.id.button3);
        select = findViewById(R.id.button15);

        final int userId = userIdSelection;

        credit_accounts = MainActivity.getInstance().getCreditaccountlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();

        //creating a new account number by using .random()
        double rand1 = Math.random();
        rand1 = 10 + rand1 * 90;
        double rand2 = Math.random();
        rand2 = 1001 + rand2 * 9000;
        double rand3 = Math.random();
        rand3 = 1000 + rand3 * 9000;  //
        double rand4 = Math.random();
        rand4 = 1001 + rand4 * 9000;
        double rand5 = Math.random();
        rand5 = 10 + rand5 * 90;

        int acc_num1 = (int) rand1;
        int acc_num2 = (int) rand2;
        int acc_num3 = (int) rand3;
        int acc_num4 = (int) rand4;
        int acc_num5 = (int) rand5;
        String S_acc_num1 = Integer.toString(acc_num1);
        String S_acc_num2 = Integer.toString(acc_num2);
        String S_acc_num3 = Integer.toString(acc_num3);
        String S_acc_num4 = Integer.toString(acc_num4);
        String S_acc_num5 = Integer.toString(acc_num5);
        final String acc_num = "FI" + S_acc_num1 + " " + S_acc_num2 + " " + S_acc_num3 + " " + S_acc_num4 + " " + S_acc_num5;
        number_view.setText("Account number: " + acc_num);

        //Open date of the account
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        final String time_date = formatter.format(date);
        date_view.setText("Open date: " + time_date);

        //balance of the account
        final float balance = 0.00f;
        balance_view.setText("Balance: " + balance + " €");


        //////Using switch to choose debit or credit account///////////////////////////////////////////////////////////////

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) findViewById(R.id.switch2);
                if (sw.isChecked()) {
                    // credit account, add possibility to write credit limit
                    creditlimit.setFocusableInTouchMode(true);
                    creditlimit.setVisibility(View.VISIBLE);
                } else {
                    creditlimit.setFocusableInTouchMode(false);
                    creditlimit.setVisibility(View.INVISIBLE);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ///////// Creating the new account ///////////////////////////////////////////////////////////////////////////////////////

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String S_pay_limit = paylimit.getText().toString();
                    float pay_limit = Float.parseFloat(S_pay_limit);

                    Switch sw;
                    sw = (Switch) findViewById(R.id.switch2);
                    if (sw.isChecked()) {    //is credit account
                        String S_credit_limit = creditlimit.getText().toString();
                        float credit_limit = Float.parseFloat(S_credit_limit);

                        credit_accounts.add(new Credit_account(acc_num, balance, userId, time_date, pay_limit, credit_limit));
                        Toast.makeText(getApplicationContext(), "Credit account created", Toast.LENGTH_SHORT).show();


                    } else {    //is debit account
                        debit_accounts.add(new Debit_account(acc_num, balance, userId, time_date, pay_limit));
                        Toast.makeText(getApplicationContext(), "Debit account created", Toast.LENGTH_SHORT).show();

                    }
                }catch (NullPointerException null_e){
                    Toast.makeText(getApplicationContext(), "Set limit first", Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException empty_e){
                    Toast.makeText(getApplicationContext(), "Set limit first", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //this makes the AccountActivity refresh when pressing back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intentCardActivity = new Intent(AddAccountActivity.this, AccountActivity.class);
        startActivity(intentCardActivity);
    }

}
