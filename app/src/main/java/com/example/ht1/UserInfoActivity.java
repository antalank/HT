package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ht1.Main2Activity.userIdSelection;

public class UserInfoActivity extends BaseActivity {

    TextView nameText;
    TextView addressText;
    TextView numberText;
    EditText newName;
    EditText newAddress;
    EditText newNumber;
    String currentString;

   // ArrayList<Customer> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_user_info, null, false);
        mDrawer.addView(contentView, 0);

        nameText = findViewById(R.id.Name);
        addressText = findViewById(R.id.Address);
        numberText = findViewById(R.id.Number);
        newName = findViewById(R.id.editName);
        newAddress = findViewById(R.id.editAddress);
        newNumber = findViewById(R.id.editNumber);

        //customers =  Main2Activity.getInstance().getCustomerlist();

        /*for (Customer customer : customers) {
            if (id == customer.getUserId()) {
                name = customer.getName();
                address = customer.getAddress();
                number = customer.getNumber();
                postal_code = customer.getPostalCode();
            }
        }

        nameText.setText("Name: " + name);
        addressText.setText("Address: " + postal_code + " " + address);
        numberText.setText("Phone number: " + number);*/

        DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
        dbAccess.open();

        int id = userIdSelection;
        String name = dbAccess.getName(id);
        String address = dbAccess.getAddress(id);
        String postal_code = dbAccess.getPostalCode(id);
        String number = dbAccess.getNumber(id);

        nameText.setText("Name: " + name);
        addressText.setText("Address: " + postal_code + " " + address);
        numberText.setText("Phone number: " + number);

        dbAccess.close();
    }

    public void setEnabledName(View v) {
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

        try {

            //customers =  Main2Activity.getInstance().getCustomerlist();
            s = newName.getText().toString();
            nameText.setText("Name: " + s);

            DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
            dbAccess.open();
            dbAccess.setName(id, s);

            EditText editText1 = findViewById(R.id.editName);
            editText1.setFocusableInTouchMode(false);
            editText1.setVisibility(View.INVISIBLE);
            Button button1 = findViewById(R.id.save1);
            button1.setFocusable(false);
            button1.setVisibility(View.INVISIBLE);

            newName.setText(null);
        }catch (NullPointerException null_e){
            Toast.makeText(getApplicationContext(), "Write name first!", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveAddress(View v){
        int id = userIdSelection;
        //customers =  Main2Activity.getInstance().getCustomerlist();

        try {
        currentString = newAddress.getText().toString();
        String[] separated = currentString.split(",");
        String r = separated[0];
        String t = separated[1];
        addressText.setText("Address: " + r + " " + t);
        /*for (Customer c : customers) {
            if (id == c.getUserId()) {
                c.setPostalCode(r);
                c.setAddress(t);
            }
        }*/

        DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
        dbAccess.open();
        dbAccess.setPostalCode(id, r);
        dbAccess.setAddress(id, t);

        EditText editText2 = findViewById(R.id.editAddress);
        editText2.setFocusableInTouchMode(false);
        editText2.setVisibility(View.INVISIBLE);
        Button button2 = findViewById(R.id.save2);
        button2.setFocusable(false);
        button2.setVisibility(View.INVISIBLE);

        newAddress.setText(null);
        }catch (NullPointerException null_e){
            Toast.makeText(getApplicationContext(), "Write name first!", Toast.LENGTH_SHORT).show();
        }catch (IllegalStateException illegal_e){
            Toast.makeText(getApplicationContext(), "Write name first!", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveNumber(View v){
        String s;
        int id = userIdSelection;
        //customers =  Main2Activity.getInstance().getCustomerlist();
        try {

        s = newNumber.getText().toString();
        numberText.setText("Phone number: " + s);

        DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
        dbAccess.open();
        dbAccess.setNumber(id, s);

        EditText editText3 = findViewById(R.id.editNumber);
        editText3.setFocusableInTouchMode(false);
        editText3.setVisibility(View.INVISIBLE);
        Button button3 = findViewById(R.id.save3);
        button3.setFocusable(false);
        button3.setVisibility(View.INVISIBLE);

        newNumber.setText(null);
        }catch (NumberFormatException empty_e) {
            Toast.makeText(getApplicationContext(), "Set postal code first!", Toast.LENGTH_SHORT).show();
        }
    }


}
