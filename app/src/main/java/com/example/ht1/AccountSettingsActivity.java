package com.example.ht1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.ht1.Main2Activity.userIdSelection;

public class AccountSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int selection;
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    List<String> list = new ArrayList<String>();

    TextView textViewHeader_Settings;
    TextView textViewPaylimNote;
    TextView textViewCreditNote;
    TextView textViewDown;
    TextView textViewSelect;

    EditText editTextPayLimit;
    EditText editTextCreditLimit;

    int i = 0;
    String account;
    String PayLimit;
    String CreditLimit;

    float paylimit;
    float creditlimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        textViewHeader_Settings = (TextView) findViewById(R.id.textViewHeader_Settings);
        textViewPaylimNote = (TextView) findViewById(R.id.textViewPaylimNote);
        textViewCreditNote = (TextView) findViewById(R.id.textViewCreditNote);
        textViewSelect = (TextView) findViewById(R.id.textViewSelect);

        editTextPayLimit = (EditText) findViewById(R.id.editTextPayLimit);
        editTextPayLimit.setText("");
        editTextCreditLimit = (EditText) findViewById(R.id.editTextCreditLimit);
        editTextCreditLimit.setText("");

        spinner();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner8);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
    }
    public void spinner() {
        int id = 0;
        System.out.println(userIdSelection);

        //get arrayslists from MainActivity
        account_event =  MainActivity.getInstance().getAccountEventlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();

        for (Debit_account d_a : debit_accounts) {
            id = userIdSelection;
            if (id == d_a.getUserID()) {
                list.add(d_a.getAccountNumber());
            }
        }
        for (Credit_account c_a : credit_accounts) {
            id = userIdSelection;
            if (id == c_a.getUserID()) {
                list.add(c_a.getAccountNumber());
            }
        }

    }
    public void PayLimit(View view) {
        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        PayLimit = editTextPayLimit.getText().toString();
        System.out.println(PayLimit);
        //Cheking possible errors
        if (PayLimit == null || PayLimit.isEmpty()) {
            paylimit = 0.0f;
        } else {
            paylimit = Float.valueOf(PayLimit);
        }
        //Seting paylimit
        for (Debit_account d_a : debit_accounts) {
            if (account.equals(d_a.getAccountNumber())) {
                d_a.setPayLim(paylimit);
                Toast.makeText(getApplicationContext(), "Paylimit set to " + paylimit, Toast.LENGTH_SHORT).show();

            }
        }
        for (Credit_account c_a : credit_accounts) {
            if (account.equals(c_a.getAccountNumber())) {
                c_a.setPayLim(paylimit);
                Toast.makeText(getApplicationContext(), "Paylimit set to " + paylimit, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void CreditLimit(View view) {
        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        CreditLimit = editTextCreditLimit.getText().toString();
        System.out.println(CreditLimit);
        //Cheking possible errors
        if (CreditLimit == null || CreditLimit.isEmpty()) {
            creditlimit = 0.0f;
        } else {
            creditlimit = Float.valueOf(CreditLimit);
        }
        int i = 0;
        //Set paylimit
        for (Credit_account c_a : credit_accounts) {
            if (account.equals(c_a.getAccountNumber())) {
                c_a.setCredit(creditlimit);
                Toast.makeText(getApplicationContext(), "Credit limit set to " + creditlimit, Toast.LENGTH_SHORT).show();
                i++;
            }
        }
        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Can't change credit limit", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonBack(View view) {
        super.onBackPressed();
        Intent intent = new Intent(AccountSettingsActivity.this, AccountActivity.class);
        startActivity(intent);
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selection = parent.getSelectedItemPosition();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
