package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import static com.example.ht1.CardActivity.cardTypeSelection;
import static com.example.ht1.Main2Activity.userIdSelection;


public class CardSettingsActivity extends AppCompatActivity {
    //TODO muista credit- ja debit-hommelit

    Spinner spinner;
    EditText draw;
    EditText use;
    Button check_draw;
    Button check_use;
    Button check_area;
    TextView draw_view;
    TextView use_view;
    TextView area_view;

    //lists are here
    ArrayList<String> area_list = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<Credit_card> credit_cards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_settings);

        final String card_type = cardTypeSelection;
        final int userId = userIdSelection;

        check_draw = findViewById(R.id.button5);
        check_use = findViewById(R.id.button9);
        check_area = findViewById(R.id.button12);
        spinner = findViewById(R.id.spinner5);
        draw = findViewById(R.id.editText3);
        use = findViewById(R.id.editText4);
        draw_view = findViewById(R.id.textView10);
        use_view = findViewById(R.id.textView11);
        area_view = findViewById(R.id.textView12);

        //adding data to the lists
        area_list.add("Select area");
        area_list.add("FIN");
        area_list.add("EUR");
        area_list.add("WORLD");

        debit_cards = MainActivity.getInstance().getDebitCards();
        credit_cards = MainActivity.getInstance().getCreditCards();


        //////Content of the text views /////////////////////////////////////////////////////////////////////////////////////////////

        if (card_type.equals("Debit")){
            for (int i = 0; i < debit_cards.size(); i++) {
                if (userId == debit_cards.get(i).getUserID()) {
                    draw_view.setText("Draw limit: " + debit_cards.get(i).getDrawLimit());
                    use_view.setText("Use limit: " + debit_cards.get(i).getUseLimit());
                    area_view.setText("Working area: " + debit_cards.get(i).getArea());
                }
            }
        } else if (card_type.equals("Credit")){

            for (int i = 0; i < credit_cards.size(); i++) {
                if (userId == credit_cards.get(i).getUserID()) {
                    draw_view.setText("Draw limit: " + credit_cards.get(i).getDrawLimit());
                    use_view.setText("Use limit: " + credit_cards.get(i).getUseLimit());
                    area_view.setText("Working area: " + credit_cards.get(i).getArea());

                }
            }
        }


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////getting the information from editTexts with OnClickListener() ///////////////////////////////////////////////////
        check_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_draw_lim = draw.getText().toString();
                float draw_lim = Float.parseFloat(S_draw_lim);
                draw_view.setText("Draw limit: " + draw_lim);

                if (card_type.equals("Debit")){
                    for (int i = 0; i < debit_cards.size(); i++) {
                        if (userId == debit_cards.get(i).getUserID()) {
                            debit_cards.get(i).setDrawLimit(draw_lim);
                        }
                    }
                } else if(card_type.equals("Credit")){
                    for (int i = 0; i < credit_cards.size(); i++) {
                        if (userId == credit_cards.get(i).getUserID()) {
                            credit_cards.get(i).setDrawLimit(draw_lim);
                        }
                    }
                }

                }
        });

        check_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_use_lim = use.getText().toString();
                float use_lim = Float.parseFloat(S_use_lim);
                use_view.setText("Use limit: " + use_lim);

                if (card_type.equals("Debit")){
                    for (int i = 0; i < debit_cards.size(); i++) {
                        if (userId == debit_cards.get(i).getUserID()) {
                            debit_cards.get(i).setUseLimit(use_lim);
                        }
                    }
                } else if(card_type.equals("Credit")){
                    for (int i = 0; i < credit_cards.size(); i++) {
                        if (userId == credit_cards.get(i).getUserID()) {
                            credit_cards.get(i).setUseLimit(use_lim);
                        }
                    }
                }
            }
        });

        /////////////Spinner for choosing the working area of the card//////////////////////////////////////////////////////////////////////
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, area_list);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(area_adapter);


        /// when pressing button
        check_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area = spinner.getSelectedItem().toString();
                if (card_type.equals("Debit")){
                    for (int i = 0; i < debit_cards.size(); i++) {
                        if (userId == debit_cards.get(i).getUserID()) {
                            debit_cards.get(i).setArea(area);
                            area_view.setText("Working area: " + area);

                        }
                    }
                } else if(card_type.equals("Credit")){
                    for (int i = 0; i < credit_cards.size(); i++) {
                        if (userId == credit_cards.get(i).getUserID()) {
                            credit_cards.get(i).setArea(area);
                            area_view.setText("Working area: " + area);
                        }
                    }
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
