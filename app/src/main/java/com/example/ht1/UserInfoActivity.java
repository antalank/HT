package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.ht1.Main2Activity.userIdSelection;

public class UserInfoActivity extends AppCompatActivity {

    TextView nameText;
    TextView addressText;
    TextView numberText;
    EditText newName;
    EditText newAddress;
    EditText newNumber;
    String name;
    String address;
    String number;
    String postal_code;
    String currentString;

    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        nameText = findViewById(R.id.Name);
        addressText = findViewById(R.id.Address);
        numberText = findViewById(R.id.Number);
        newName = findViewById(R.id.editName);
        newAddress = findViewById(R.id.editAddress);
        newNumber = findViewById(R.id.editNumber);

        int id = userIdSelection;
        customers =  Main2Activity.getInstance().getCustomerlist();

        for (Customer customer : customers) {
            if (id == customer.getUserId()) {
                name = customer.getName();
                address = customer.getAddress();
                number = customer.getNumber();
                postal_code = customer.getPostalCode();
            }
        }

        nameText.setText("Name: " + name);
        addressText.setText("Address: " + postal_code + " " + address);
        numberText.setText("Phone number: " + number);
    }

    public void setEnabledName(View v){
        EditText editText1 = findViewById(R.id.editName);
        editText1.setFocusableInTouchMode(true);
        editText1.setVisibility(View.VISIBLE);
        Button button1 = findViewById(R.id.save1);
        button1.setFocusable(true);
        button1.setVisibility(View.VISIBLE);
    }
    public void setEnabledAddress(View v){
        EditText editText2 = findViewById(R.id.editAddress);
        editText2.setFocusableInTouchMode(true);
        editText2.setVisibility(View.VISIBLE);
        Button button2 = findViewById(R.id.save2);
        button2.setFocusable(true);
        button2.setVisibility(View.VISIBLE);
    }
    public void setEnabledNumber(View v){
        EditText editText3 = findViewById(R.id.editNumber);
        editText3.setFocusableInTouchMode(true);
        editText3.setVisibility(View.VISIBLE);
        Button button3 = findViewById(R.id.save3);
        button3.setFocusable(true);
        button3.setVisibility(View.VISIBLE);
    }

    public void saveName(View v){
        String s;
        int id = userIdSelection;
        customers =  Main2Activity.getInstance().getCustomerlist();
        s = newName.getText().toString();
        nameText.setText("Name: " + s);
        for (Customer c : customers) {
            if (id == c.getUserId()) {
                c.setName(s);
            }
        }

        EditText editText1 = findViewById(R.id.editName);
        editText1.setFocusableInTouchMode(false);
        editText1.setVisibility(View.INVISIBLE);
        Button button1 = findViewById(R.id.save1);
        button1.setFocusable(false);
        button1.setVisibility(View.INVISIBLE);

        newName.setText(null);
    }
    public void saveAddress(View v){
        int id = userIdSelection;
        customers =  Main2Activity.getInstance().getCustomerlist();
        currentString = newAddress.getText().toString();
        String[] separated = currentString.split(",");
        String r = separated[0];
        String t = separated[1];
        addressText.setText("Address: " + r + " " + t);
        for (Customer c : customers) {
            if (id == c.getUserId()) {
                c.setPostalCode(r);
                c.setAddress(t);
            }
        }

        EditText editText2 = findViewById(R.id.editAddress);
        editText2.setFocusableInTouchMode(false);
        editText2.setVisibility(View.INVISIBLE);
        Button button2 = findViewById(R.id.save2);
        button2.setFocusable(false);
        button2.setVisibility(View.INVISIBLE);

        newAddress.setText(null);
    }
    public void saveNumber(View v){
        String s;
        int id = userIdSelection;
        customers =  Main2Activity.getInstance().getCustomerlist();
        s = newNumber.getText().toString();
        numberText.setText("Phone number: " + s);
        for (Customer c : customers) {
            if (id == c.getUserId()) {
                c.setNumber(s);
            }
        }

        EditText editText3 = findViewById(R.id.editNumber);
        editText3.setFocusableInTouchMode(false);
        editText3.setVisibility(View.INVISIBLE);
        Button button3 = findViewById(R.id.save3);
        button3.setFocusable(false);
        button3.setVisibility(View.INVISIBLE);

        newNumber.setText(null);
    }


}
