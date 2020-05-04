package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.MainActivity.bankBicSelection;

public class BankInfoActivity extends BaseActivity {

    TextView nameText;
    TextView addressText;
    TextView numberText;
    String name;
    String address;
    String number;

    //ArrayList<Bank> banks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_bank_info, null, false);
        mDrawer.addView(contentView, 0);


        nameText = findViewById(R.id.textViewOtsikko);
        addressText = findViewById(R.id.textView2);
        numberText = findViewById(R.id.textView3);


        DB_Bank dbAccess = DB_Bank.getInstance(getApplicationContext());
        dbAccess.open();

        String bic = bankBicSelection;

        name = dbAccess.getName(bic);
        address = dbAccess.getAddress(bic);
        number = dbAccess.getPhone(bic);


        nameText.setText("Name: " + name);
        addressText.setText("Address: " + address);
        numberText.setText("Phone number: " + number);

        dbAccess.close();
    }

}

