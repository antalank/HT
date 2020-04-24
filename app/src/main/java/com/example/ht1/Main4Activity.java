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
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Main4Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        context = Main4Activity.this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_accounts:
                Intent intentAccount = new Intent(Main4Activity.this, AccountActivity.class);
                startActivity(intentAccount);
                break;
            case R.id.nav_payments:
                Intent intentPayment = new Intent(Main4Activity.this, PaymentActivity.class);
                startActivity(intentPayment);
                break;
            case R.id.nav_cards:
                Intent intentCard = new Intent(Main4Activity.this, CardActivity.class);
                startActivity(intentCard);
                break;
            case R.id.nav_bank_info:
                Intent intentBankInfo = new Intent(Main4Activity.this, BankInfoActivity.class);
                startActivity(intentBankInfo);
                break;
            case R.id.nav_user_info:
                Intent intentUserInfo = new Intent(Main4Activity.this, UserInfoActivity.class);
                startActivity(intentUserInfo);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
