package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;

public class PaymentActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    TextView textViewHeaderPayment;
    TextView textViewWriteAccount;
    TextView textViewSum;
    TextView textViewDueDate;
    TextView textViewFreq;
    TextView textViewPay;
    TextView textViewName;
    EditText editTextAccountNumber;
    EditText editTextSum;
    EditText editTextFreq;
    EditText editTextName;

    int selection;
    int i = 0;
    int a = 0;
    int b = 0;
    float payLim;
    float bal;
    float credit_lim;

    int id_orginal;

    String account;
    String name;

    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_payment, null, false);
        mDrawer.addView(contentView, 0);

        textViewHeaderPayment = (TextView) findViewById(R.id.textViewHeaderPayment);
        textViewHeaderPayment.setText("Select your account and pay bills");
        textViewWriteAccount = (TextView) findViewById((R.id.textViewWriteAccount));
        textViewWriteAccount.setText("Write account number");
        textViewSum = (TextView) findViewById(R.id.textViewSum);
        textViewSum.setText("Write amount in €");
        textViewDueDate = (TextView) findViewById(R.id.textViewDueDate);
        textViewDueDate.setText("Write due date if you pay bill then");
        textViewFreq = (TextView) findViewById(R.id.textViewFreq);
        textViewFreq.setText("Do you want to make payment frequent?");
        textViewPay = (TextView) findViewById(R.id.textViewPay);
        textViewPay.setText("");
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText("Write receiver's name");


        editTextAccountNumber = (EditText) findViewById(R.id.editTextAccountNumber);
        editTextAccountNumber.setText("");
        editTextSum = (EditText) findViewById(R.id.editTextSum);
        editTextFreq = (EditText) findViewById(R.id.editTextFreq);
        editTextName = (EditText) findViewById(R.id.editTextName);

        spinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerPayment);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        for (AccountEvent a_e : account_event) {
            id_orginal = a_e.getId();
        }

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
    public void pay(View view) {
        String account_num = "";
        String sum_String;
        float sum;
        Date time = Calendar.getInstance().getTime();
        String time_date = time.toString();

        account_num = editTextAccountNumber.getText().toString();
        sum_String = editTextSum.getText().toString();
        sum = Float.parseFloat(sum_String);
        name = editTextName.getText().toString();
        // sum, account_num, time_date

        for (Debit_account d_a : debit_accounts) {
            if (account.equals(d_a.getAccountNumber())) {
                payLim = d_a.getPayLim();
                bal = d_a.getBalance();
                credit_lim = d_a.getPayLim();
                if (sum <= payLim && ((bal - sum) > 0)) {
                    d_a.pay(sum);
                    a++;
                }
            }
        }
        for (Credit_account c_a : credit_accounts) {
            if (account.equals(c_a.getAccountNumber())) {
                payLim = c_a.getPayLim();
                bal = c_a.getBalance();
                if (sum <= payLim && ((bal - sum) > credit_lim)) {
                    c_a.pay(sum);
                    a++;
                }
            }
        }

        if (a == 0) {
            textViewPay.setText("Payment failed");
        } else if (a > 0) {
            for (Debit_account d_a : debit_accounts) {
                if (d_a.getAccountNumber().equals(account_num)) {
                    d_a.addMoney(sum);
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (c_a.getAccountNumber().equals(account_num)) {
                    c_a.addMoney(sum);
                }
            }
            //Adding event list

            account_event.add(new AccountEvent(id_orginal + 1, account, time_date, -(sum), account_num, name));
            account_event.add(new AccountEvent(id_orginal + 2, account_num, time_date, sum, account, userNameSelection));
            textViewPay.setText("Payment succesfull");

        }
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
