package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import static com.example.ht1.Main2Activity.userIdSelection;

import java.util.ArrayList;

public class AddCardActivity extends AppCompatActivity {
    TextView number;
    Spinner spinner;
    Spinner area_spinner;
    EditText use;
    EditText draw;
    Button check_switch;
    Button create;
    int userId = userIdSelection;
    Context context;

    //all lists
    ArrayList<String> area_list = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<String> credit_account_list = new ArrayList<>();
    ArrayList<String> debit_account_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        number = findViewById(R.id.textView9);
        spinner = findViewById(R.id.spinner2);
        area_spinner = findViewById(R.id.spinner4);
        use = findViewById(R.id.editText);
        draw = findViewById(R.id.editText2);
        check_switch = findViewById(R.id.button2);
        create = findViewById(R.id.button3);
        context = AddCardActivity.this;

        this.setTitle("New card");

        //adding data to the lists
        area_list.add("Select area");
        area_list.add("FIN");
        area_list.add("EUR");
        area_list.add("WORLD");

        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        debit_account_list.add("Choose to which account you want to add the card");
        for (int i = 0; i < debit_accounts.size(); i++) {
            if (userId == debit_accounts.get(i).getUserID()) {
                String acc_num = debit_accounts.get(i).getAccountNumber();
                debit_account_list.add(acc_num);
            }
        }
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();
        credit_account_list.add("Choose to which account you want to add the card");
        for (int i = 0; i < credit_accounts.size(); i++) {
            if (userId == credit_accounts.get(i).getUserID()) {
                String acc_num = credit_accounts.get(i).getAccountNumber();
                credit_account_list.add(acc_num);
            }
        }

        //////Using switch to choose debit or credit card///////////////////////////////////////////////////////////////

        check_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) findViewById(R.id.switch2);
                if (sw.isChecked()) {
                    // listing credit accounts in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, credit_account_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                } else {
                    // listing debit accounts in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, debit_account_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        //creating a new card number by using .random()
        double rand1 = Math.random();
        rand1 = 10000000 + rand1 * 99999998;
        double rand2 = Math.random();
        rand2 = 10000000 + rand2 * 99999998;
        int card_num1 = (int) rand1;
        int card_num2 = (int) rand2;
        String S_card_num1 = Integer.toString(card_num1);
        String S_card_num2 = Integer.toString(card_num2);
        final String card_num = S_card_num1 + S_card_num2;
        number.setText("Card number: " + card_num);


        /////////////Spinner for choosing the working area of the card//////////////////////////////////////////////////////////////////////
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, area_list);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(area_adapter);

        final String area = area_spinner.getSelectedItem().toString();

        ////////////////////////////////////////////////////////////////////////////////////////////////

        ///////// Creating the new card ///////////////////////////////////////////////////////////////////////////////////////

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_use_limit = use.getText().toString();
                float use_limit = Float.parseFloat(S_use_limit);

                String S_draw_limit = draw.getText().toString();
                float draw_limit = Float.parseFloat(S_draw_limit);

                if (area.equals("Select area")) {
                    System.out.println("The header of spinner");
                    // TODO joku ilmoitus että valitse area ensin? vaiko turha
                } else {
                    // TODO koodi tähän:
                    Switch sw;
                    sw = (Switch) findViewById(R.id.switch2);
                    if (sw.isChecked()) {    //is credit card
                        //TODO for loop for adding the info of the chosen account to card-list
                    } else {    //is debit card
                    }
                }

    }

    });
    }
}

