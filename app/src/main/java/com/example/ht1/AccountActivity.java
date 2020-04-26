package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.ht1.Main2Activity.userIdSelection;

public class AccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textviewotsikko;
    TextView textviewData;
    int valinta;
    String account;
    String event_string;
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    List<String> list = new ArrayList<String>();
    List<String> event_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        textviewotsikko = (TextView) findViewById(R.id.textViewOtsikko);
        textviewotsikko.setText("Select account from drop down menu");
        textviewData = (TextView) findViewById((R.id.textViewData));
        textviewData.setText("Accountevents are listed here");
        spinner();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
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
    public void showData(View view) {
        int i = 0;
        String r_account;
        String day;
        String from_num;
        float sum;
        String from_name;

        for (String l : list) {
            if (i == valinta) {
                account = list.get(i);
            } i++;
        }
        textviewData.setText("");
        for (AccountEvent a_e : account_event) {
            r_account = a_e.getAccount_num();
            if (r_account.equals(account)) {
                day = a_e.getDay();
                from_num = a_e.getFrom_account_num();
                from_name = a_e.getFrom_account_name();
                sum = a_e.getSum();
                event_string = (day + " Sum: " + sum + "€\nFrom: " + from_num + ", " + from_name + "\n" + "\n");
                //event_list.add(event_string);
                textviewData.append(event_string);

            }
        }
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        valinta = parent.getSelectedItemPosition();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
