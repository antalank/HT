package com.example.ht1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.ht1.MainActivity.bankNameSelection;

public class Main4Activity extends BaseActivity {
    Context context = null;
    TextView textView4BankName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView4BankName = findViewById(R.id.textView4BankName);
        //     textView4BankName.setText("Welcome to " + bankNameSelection + "!");

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_main4, null, false);
        mDrawer.addView(contentView, 0);

    }

}
