package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.ht1.Main2Activity.userIdSelection;

public class AccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Context context;
    TextView textviewotsikko;
    TextView textviewData;
    TextView textViewWrite;
    int selection;
    String r_account;
    String write = "";
    String writeFileName = "";
    String account;
    String event_string;
    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        textviewotsikko = (TextView) findViewById(R.id.textViewOtsikko);
        textviewotsikko.setText("Select account from drop down menu");
        textviewData = (TextView) findViewById((R.id.textViewData));
        textviewData.setText("Accountevents are listed here");
        textViewWrite = (TextView) findViewById(R.id.textViewWrite);
        textViewWrite.setText("");
        context = AccountActivity.this;
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

        String day;
        String from_num;
        float sum;
        String from_name;

        for (String l : list) {
            if (i == selection) {
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
                event_string = (day + " Sum: " + sum + " €\nFrom/to: " + from_num + ", " + from_name + "\n" + "\n");
                textviewData.append(event_string);
            }
        } write = textviewData.getText().toString();
    }
    public void writeDatatoFile(View view) {
    try {
        LocalDate time = LocalDate.now();
        writeFileName = ("Account: " + r_account + ", " + time.toString());
        OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(writeFileName, Context.MODE_PRIVATE));
        ows.write(write);
        ows.close();
        textViewWrite.setText("Events wrote to phone's memory");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        System.out.println("###Wrote###");
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
