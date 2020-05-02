package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.ht1.CardActivity.cardNumSelection;
import static com.example.ht1.CardActivity.cardTypeSelection;
import static com.example.ht1.Main2Activity.userIdSelection;

public class WithdrawActivity extends AppCompatActivity {

    Spinner spinner_area;
    Spinner spinner_money;
    Button withdraw;
    TextView textview;

    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<String> area_list = new ArrayList<>();
    ArrayList<String> money_list = new ArrayList<>();
    ArrayList<AccountEvent> event_list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        final String card_type = cardTypeSelection;
        final  int userId = userIdSelection;
        final String card_num = cardNumSelection;

        textview = findViewById(R.id.textView13);
        spinner_money = findViewById(R.id.spinner6);
        spinner_area = findViewById(R.id.spinner7);
        withdraw = findViewById(R.id.button11);

        //adding data to lists
        credit_cards = MainActivity.getInstance().getCreditCards();
        debit_cards = MainActivity.getInstance().getDebitCards();
        event_list =  MainActivity.getInstance().getAccountEventlist();

        //adding data to money list
        money_list.add("Choose the right sum");
        money_list.add("20 €");
        money_list.add("40 €");
        money_list.add("60 €");
        money_list.add("90 €");
        money_list.add("120 €");
        money_list.add("240 €");

        //adding data to area list
        area_list.add("Choose on which area you are");
        area_list.add("FIN");
        area_list.add("EU");
        area_list.add("WORLD");

        textview.setText("Card: " + card_num);

        /////////////Spinner for choosing the area //////////////////////////////////////////////////////////////////////
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, area_list);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(area_adapter);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////Spinner for choosing the amount of money to be withdrawn////////////////////////////////////////////////////
        ArrayAdapter<String> money_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, money_list);
        money_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_money.setAdapter(money_adapter);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////Withdrawing money when clicking  button///////////////////////////////////////////////////////////////
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_money = spinner_money.getSelectedItem().toString();

                if (get_money.equals("Choose the right sum")) {
                    Toast.makeText(getApplicationContext(), "Select money sum first", Toast.LENGTH_SHORT).show();
                } else {

                    // parsing and then saving the amount of money as integer
                    String[] money_sep = get_money.split(" ");
                    String S_money = money_sep[0];
                    int money = Integer.parseInt(S_money);

                    // checking if the area is right
                    String selected_area = spinner_area.getSelectedItem().toString();

                    // if card is credit card
                    if (card_type.equals("Credit")) {
                        for (int i = 0; i < credit_cards.size(); i++) {
                            if (card_num == credit_cards.get(i).getCardNum()) {
                                String area = credit_cards.get(i).getArea();
                                System.out.println(area);

                                // checking if the area is right
                                if (area.equals("WORLD") || (area.equals("EU") && selected_area.equals("FIN")) || selected_area.equals(area)) {

                                    //reducing balance from the account
                                    for (int r = 0; r < credit_cards.size(); r++) {
                                        if (userId == credit_cards.get(r).getUserID()) {

                                            String acc_num = credit_cards.get(r).getAccountNumber();

                                            //checking that there is enough money in the account
                                             float limit = credit_cards.get(r).getBalance() + credit_cards.get(r).getCredit();

                                             if (limit > money){
                                                 //checking that the draw limit is ok
                                                 if (credit_cards.get(r).getDrawLimit() > money) {
                                                     credit_cards.get(r).pay(money);

                                                     //getting the previous account event id from event list
                                                     int id_org = 0;
                                                     for (int a = 0; a < event_list.size(); a++) {
                                                         id_org = event_list.get(a).getId();
                                                     }

                                                     //getting the current date for the event log
                                                     SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                                                     Date date = new Date();
                                                     String time_date = formatter.format(date);
                                                     event_list.add(new AccountEvent(id_org + 1, acc_num, time_date, -(money), "Withdraw", "Automaatti"));

                                                     Toast.makeText(getApplicationContext(), "You got " + money + " €", Toast.LENGTH_SHORT).show();
                                                 }else {
                                                     Toast.makeText(getApplicationContext(), "Your draw limit has gone over", Toast.LENGTH_SHORT).show();
                                                 }
                                             }else{
                                                 Toast.makeText(getApplicationContext(), "You do not have enough money in your account", Toast.LENGTH_SHORT).show();
                                             }
                                        }
                                    }

                                } else if (selected_area.equals("Choose on which area you are")) {
                                    Toast.makeText(getApplicationContext(), "Select area first!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You cannot withdraw money in this area", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        // doing the same if card is debit card
                    }else if (card_type.equals("Debit")) {
                        for (int i = 0; i < debit_cards.size(); i++) {
                            if (card_num == debit_cards.get(i).getCardNum()) {
                                String area = debit_cards.get(i).getArea();
                                System.out.println(area);

                                // checking if the area is right
                                if (area.equals("WORLD") || (area.equals("EU") && selected_area.equals("FIN")) || selected_area.equals(area)) {

                                    //reducing balance from the account
                                    for (int r = 0; r < debit_cards.size(); r++) {
                                        if (userId == debit_cards.get(r).getUserID()) {
                                            String acc_num = debit_cards.get(r).getAccountNumber();


                                            //checking that there is enough money in the account
                                            float limit = debit_cards.get(r).getBalance();

                                            if (limit > money){
                                                //checking that the draw limit is ok
                                                if (debit_cards.get(r).getDrawLimit() > money){
                                                    debit_cards.get(r).pay(money);

                                                    //getting the previous account event id from event list
                                                    int id_org = 0;
                                                    for (int a = 0; a < event_list.size(); a++) {
                                                        id_org = event_list.get(a).getId();
                                                    }

                                                    //getting the current date for the event log
                                                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                                                    Date date = new Date();
                                                    String time_date = formatter.format(date);
                                                    event_list.add(new AccountEvent(id_org + 1, acc_num, time_date, -(money), "Withdraw", " ATM"));

                                                    Toast.makeText(getApplicationContext(), "You got " + money + " €", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    Toast.makeText(getApplicationContext(), "Your draw limit has gone over", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(getApplicationContext(), "You do not have enough money in your account", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                } else if (selected_area.equals("Choose on which area you are")) {
                                    Toast.makeText(getApplicationContext(), "Select area first!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You cannot withdraw money in this area", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                }

            }
        });
    }

}
