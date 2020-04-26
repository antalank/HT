package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView textView1;
    TextView textView5;
    TextView textViewError;
    EditText editText1;
    String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView1 = findViewById(R.id.textView1);
        textView5 = findViewById(R.id.textView5);
        textView5.setText("Import keycode list number");
        textViewError = findViewById(R.id.textViewError);
        textViewError.setText("");
        editText1 = findViewById(R.id.editText1);
        randomNumber();
    }

    public void randomNumber() {
        double rand = Math.random();
        rand = rand * 999999 + 100000;
        int IntRand = (int) rand;
        System.out.println(IntRand);

        text = Integer.toString(IntRand);
        textView1.setText(text);
    }

    public void loadActivity3(View v){
        String number = editText1.getText().toString();
        if (number.equals(text)) {
            Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
            startActivity(intent);
        } else if (number != text) {
            textViewError.setText("Wrong number!, try again");
        }
    }
}
