package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static com.example.ht1.PaymentActivity.selectedAccNum;

import java.util.ArrayList;

public class UpcomingPaymentActivity extends AppCompatActivity {
    TextView textView;

    public String event_string;

    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<PayLog> paylog_list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_payment);

        textView = (TextView) findViewById((R.id.textViewData));
        textView.setText("");

        credit_cards = MainActivity.getInstance().getCreditCards();
        debit_cards = MainActivity.getInstance().getDebitCards();
        paylog_list = MainActivity.getInstance().getPaylog();

    }

    public void showPayLog(View v){

        /// Creating the string to show in ScrollView
        if (paylog_list.size() == 0){
            textView.setText("No upcoming payments");

        }else {
            for (int i = 0; i < paylog_list.size(); i++) {
                if (paylog_list.get(i).getFrom_account().equals(selectedAccNum)) {
                    int id = paylog_list.get(i).getIde();
                    int date = paylog_list.get(i).getDate_();
                    float sum = paylog_list.get(i).getSum();
                    String to_acc = paylog_list.get(i).getTo_account();
                    String to_name = paylog_list.get(i).getTo_name();
                    event_string = ("Id: " + id + "Date: " +date + " Sum: -" + sum + " €\nTo: " + to_acc + ", " + to_name + "\n" + "\n");
                    textView.append(event_string);
                }
            }
        }
    }
}
