package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import static com.example.ht1.CardActivity.cardTypeSelection;
import static com.example.ht1.Main2Activity.userIdSelection;

public class WithdrawActivity extends AppCompatActivity {

    //TODO tsekkaa maarajotus!

    Spinner spinner;
    Spinner spinner_money;
    Button withdraw;

    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<String> credit_cards_list = new ArrayList<>();
    ArrayList<String> debit_cards_list = new ArrayList<>();
    ArrayList<String> money = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        final String card_type = cardTypeSelection;
        final  int userId = userIdSelection;

        spinner = findViewById(R.id.spinner6);
        spinner_money = findViewById(R.id.spinner7);
        withdraw = findViewById(R.id.button11);


        credit_cards = MainActivity.getInstance().getCreditCards();
        debit_cards = MainActivity.getInstance().getDebitCards();

        // creating the lists of cards that the user has
        credit_cards_list.add("Show credit cards");
        for (int i = 0; i < credit_cards.size(); i++) {
            if (userId == credit_cards.get(i).getUserID()) {
                String card_num = credit_cards.get(i).getCardNum();
                credit_cards_list.add(card_num);
            }
        }
        debit_cards_list.add("Show debit cards");
        for (int i = 0; i < debit_cards.size(); i++) {
            if (userId == credit_cards.get(i).getUserID()) {
                String card_num = debit_cards.get(i).getCardNum();
                debit_cards_list.add(card_num);
            }
        }

        //adding data to money list
        money.add("20 €");
        money.add("40 €");
        money.add("60 €");
        money.add("90 €");
        money.add("120 €");
        money.add("240 €");

        /////////////Spinner for choosing card//////////////////////////////////////////////////////////////////////

        if (card_type.equals("Credit")){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, credit_cards_list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }else if (card_type.equals("Debit")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, debit_cards_list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////Spinner for choosing the amount of money to be withdrawn////////////////////////////////////////////////////
        ArrayAdapter<String> money_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, money);
        money_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_money.setAdapter(money_adapter);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////Withdrawing money when clicking  button///////////////////////////////////////////////////////////////
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card_num = spinner.getSelectedItem().toString();

                // parsing and then saving the amount of money as integer
                String get_money = spinner_money.getSelectedItem().toString();
                String[] money_sep = get_money.split(" ");
                String S_money = money_sep[0];
                int money = Integer.parseInt(S_money);

                //reducing balance from the account
                if (card_type.equals("Debit")) {
                    for (int i = 0; i < debit_cards.size(); i++) {
                        if (userId == debit_cards.get(i).getUserID()) {
                            debit_cards.get(i).pay(money);
                        }
                    }
                } else if (card_type.equals("Credit")) {
                    for (int i = 0; i < credit_cards.size(); i++) {
                        if (userId == credit_cards.get(i).getUserID()) {
                            credit_cards.get(i).pay(money);
                        }
                    }
                }
            }
        });
    }

}
