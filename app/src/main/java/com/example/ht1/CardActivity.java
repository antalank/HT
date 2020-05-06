package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.ht1.Main2Activity.userIdSelection;

public class CardActivity extends BaseActivity {

    public static String cardTypeSelection;
    public static String cardNumSelection;
    public static String accountNumSelection;

    Spinner spinner;
    Button check_switch;
    Button amortise;
    Context context;
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<String> credit_cards_list = new ArrayList<>();
    ArrayList<String> debit_cards_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_card, null, false);
        mDrawer.addView(contentView, 0);

        check_switch = findViewById(R.id.button8);
        amortise = findViewById(R.id.button6);
        spinner = findViewById(R.id.spinner);
        context = CardActivity.this;
        int userId = userIdSelection;

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

        //////Using switch to choose debit or credit card///////////////////////////////////////////////////////////////

        check_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) findViewById(R.id.switch1);
                if (sw.isChecked()) {
                    //// listing credit cards in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, credit_cards_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    cardTypeSelection = "Credit";

                } else {
                    //// listing debit cards in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, debit_cards_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    cardTypeSelection = "Debit";

                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        ///////Amortise card////////////////////////////////////////////////////////////////////////////////

            //checking if the card is debit or credit card with switch
            amortise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Switch sw;
                    sw = (Switch) findViewById(R.id.switch1);
                    if (sw.isChecked()) {    //is credit card

                        //making sure that the user has chosen credit or debit card (Spinner has null object otherwise)
                        try {
                            // saving the chosen item from spinner
                            String card = spinner.getSelectedItem().toString();

                            if (card.equals("Show credit cards")) {
                                Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
                            } else {
                                //deleting the card from all lists
                                for (int i = 0; i < credit_cards.size(); i++) {
                                    if (card == credit_cards.get(i).getCardNum()) {
                                        credit_cards.remove(i);
                                        credit_cards_list.remove(i+1);
                                        Toast.makeText(getApplicationContext(), "Card has been amortised", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } catch (NullPointerException null_e) {
                            Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
                        }

                    } else {     //is debit card

                        //making sure that the user has chosen credit or debit card (Spinner has null object otherwise)
                        try {

                            // saving the chosen item from spinner
                            String card = spinner.getSelectedItem().toString();
                            if (card.equals("Show debit cards")) {
                                Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
                            } else {
                                //deleting the card from all lists
                                for (int i = 0; i < debit_cards.size(); i++) {
                                    if (card == debit_cards.get(i).getCardNum()) {
                                        debit_cards.remove(i);
                                        debit_cards_list.remove(i+1);
                                        Toast.makeText(getApplicationContext(), "Card has been amortised", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        } catch (NullPointerException null_e) {
                            Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    }

    public static String getCardTypeSelection() {
        return cardTypeSelection;
    }

    public void addCard(View v){
        Intent intent = new Intent(CardActivity.this, AddCardActivity.class);
        startActivity(intent);
    }

    public void changeSettings(View v){
        //making sure that the user has chosen credit or debit card (Spinner has null object otherwise)
        try {
            // making sure that the user has selected a card
            String card = spinner.getSelectedItem().toString();
            if (card.equals("Show credit cards")) {
                Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
            }else if (card.equals("Show debit cards")) {
                Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(CardActivity.this, CardSettingsActivity.class);
                startActivity(intent);
            }
        }catch (NullPointerException null_e){
            Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
        }

    }

    public void withdrawMoney(View v) {
        //making sure that the user has chosen credit or debit card (Spinner has null object otherwise)
        try {
            // making sure that the user has selected a card
            String card = spinner.getSelectedItem().toString();
            if (card.equals("Show credit cards")) {
                Toast.makeText(getApplicationContext(), "Select card first!", Toast.LENGTH_SHORT).show();
            } else if (card.equals("Show debit cards")) {
                Toast.makeText(getApplicationContext(), "Select card first!", Toast.LENGTH_SHORT).show();
            } else {
                cardNumSelection = card;
                Intent intent = new Intent(CardActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        }catch (NullPointerException null_e){
            Toast.makeText(getApplicationContext(), "Select card first", Toast.LENGTH_SHORT).show();
        }
    }
}





