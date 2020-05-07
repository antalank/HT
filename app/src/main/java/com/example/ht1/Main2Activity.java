package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.ht1.MainActivity.bankBicSelection;
import static com.example.ht1.SHA512.getSHA512;


public class Main2Activity extends AppCompatActivity {

    EditText giveUser;
    EditText givePass;
    Context context = null;
    TextView text;

    ArrayList<Salt> salt_listt = new ArrayList<>();

    public static int userIdSelection;
    public static String userNameSelection;
    public static Main2Activity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        giveUser = findViewById(R.id.giveUserName);
        givePass = findViewById(R.id.givePassword);
        context = Main2Activity.this;
        text = (TextView) findViewById(R.id.textView_error);
        text.setText("");
        instance = this;
    }

    public static Main2Activity getInstance() {
        return instance;
    }


    public void loadActivity2(View v) {
        int apply = 0;

        String bic;
        String password;
        String gU = giveUser.getText().toString();
        String gPassword = givePass.getText().toString();

        int lenght1;
        int lenght2;

        lenght1 = gU.length();
        lenght2 = gPassword.length();

        if (lenght1 == 0 || lenght2 == 0) {
            text.setText("Give your user id and password");
        } else {
            int gUser = Integer.parseInt(gU);
            DB_Customer dbAccess = DB_Customer.getInstance(getApplicationContext());
            dbAccess.open();
            salt_listt = dbAccess.getSaltList();
            password = dbAccess.getPassword(gUser);
            bic = dbAccess.getBIC(gUser);

            for(int i = 0; i < salt_listt.size(); i++){
                System.out.println(gUser);
                System.out.println(salt_listt.size());
                if(gUser == salt_listt.get(i).getUser()){
                    // take the salt connected to the user id and use it to hash the password that is given by the user
                    String currSalt = salt_listt.get(i).getSalt();
                    String pass = getSHA512(gPassword, currSalt);
                    // compare the password from database to the one given by user
                    if (pass.equals(password) && bankBicSelection.equals(bic)) {
                        apply++;
                        userIdSelection = gUser;
                        userNameSelection = dbAccess.getName(gUser);
                        dbAccess.close();
                    }
                }
            }
            if (apply > 0) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);

            } else if (apply == 0) {
                text.setText("Wrong user id or password!");
            }
        }
    }

    public void addUserActivity(View v) {
        Intent intent = new Intent(Main2Activity.this, AddUserActivity.class);
        startActivity(intent);
    }
}


