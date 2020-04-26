package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddCardActivity extends AppCompatActivity {
    TextView number;
    Spinner spinner;
    EditText use;
    EditText draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        number = findViewById(R.id.textView9);
        spinner = findViewById(R.id.spinner2);
        use = findViewById(R.id.editText);
        draw = findViewById(R.id.editText2);

        this.setTitle("New card");

        //creating a new card number by using .random()
        double rand1 = Math.random();
        rand1 = 10000000 + rand1 * 99999998;
        double rand2 = Math.random();
        rand2 = 10000000 + rand2 * 99999998;
        int card_num1 = (int) rand1;
        int card_num2 = (int) rand2;
        String S_card_num1 = Integer.toString(card_num1);
        String S_card_num2 = Integer.toString(card_num2);
        String card_num = S_card_num1 + S_card_num2;
        number.setText("Card number: " + card_num);



        //TODO spinneri valitsemaan tili? Vaiko vain että kirjoita tilinro?
        //Spinner for choosing the type of the card
     /*   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, type_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String type = spinner.getSelectedItem().toString();
        if (type.equals("Choose between credit or debit")) {
            System.out.println("The header of spinner");
        } else {

        } */




    }
}

