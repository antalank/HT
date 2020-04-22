package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void loadActivity3(View v){
        Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
        startActivity(intent);

    }
}
