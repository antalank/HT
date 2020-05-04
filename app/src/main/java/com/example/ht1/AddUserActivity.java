package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.ht1.MainActivity.bankBicSelection;


public class AddUserActivity extends AppCompatActivity {

    TextView user_view;
    TextView password_view;
    String bic;
    EditText name_text;
    EditText ssn_text;
    EditText phone_text;
    EditText address_text;
    EditText postal_text;
    EditText password_text;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        user_view = findViewById(R.id.textView16);
        password_view = findViewById(R.id.textView17);
        register = findViewById(R.id.button18);
        bic = bankBicSelection;

        password_view.setText("Password must be 12 min. characters long, has special character, has capital and lower letters and has number");

        //creating a new user id by using .random()
        double rand1 = Math.random();
        rand1 = 100000 + rand1 * 900000;
        final int user_id = (int) rand1;
        user_view.setText("User_id: " + user_id);

        ///////// Creating the new user ///////////////////////////////////////////////////////////////////////////////////////

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    name_text = findViewById(R.id.editText6);
                    ssn_text = findViewById(R.id.editText7);
                    phone_text = findViewById(R.id.editText8);
                    address_text = findViewById(R.id.editText9);
                    postal_text = findViewById(R.id.editText10);
                    password_text = findViewById(R.id.editText11);


                    String ssn = ssn_text.toString();
                    String name = name_text.toString();
                    String phone = phone_text.toString();
                    String address = address_text.toString();
                    String postal = postal_text.toString();
                    String password = password_text.toString();

                    //checking if password has numbers, lower cases and upper cases
                    char[] chars = password.toCharArray();
                    int num_check = 0;
                    int capital_check = 0;
                    int lower_check = 0;
                    for (char c : chars) {
                        if (Character.isDigit(c)) {
                            num_check++;
                        } else if (Character.isUpperCase(c)) {
                            capital_check = 0;
                        } else if (Character.isLowerCase(c)) {
                            lower_check = 0;
                        }
                    }

                    //checking if password has special characters
                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher("I am a string");
                    boolean b = m.find();

                    System.out.println(password.length());
                    //checking that the password is made by the rules
                    if (password.length() < 12) {
                        Toast.makeText(getApplicationContext(), "Password is too short", Toast.LENGTH_SHORT).show();
                    } else if (num_check < 1) {
                        Toast.makeText(getApplicationContext(), "Password must have at least 1 number", Toast.LENGTH_SHORT).show();
                    } else if (capital_check < 1) {
                        Toast.makeText(getApplicationContext(), "Password must have at least 1 capital letter", Toast.LENGTH_SHORT).show();
                    } else if (num_check < 1) {
                        Toast.makeText(getApplicationContext(), "Password must have at least 1 number", Toast.LENGTH_SHORT).show();
                    } else if (b == false) {
                        Toast.makeText(getApplicationContext(), "Password must have at least 1 special character", Toast.LENGTH_SHORT).show();
                    } else {
                        DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
                        dbAccess.open();
                        dbAccess.addCustomer(user_id, ssn, name, phone, address, password, postal, bic);

                        dbAccess.close();
                    }
                    } catch(NullPointerException null_e){
                        Toast.makeText(getApplicationContext(), "Write all information, please", Toast.LENGTH_SHORT).show();
                    } catch(NumberFormatException null_e){
                        Toast.makeText(getApplicationContext(), "Write all information, please", Toast.LENGTH_SHORT).show();
                    }
            }

        });
    }


}
