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


public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        protected DrawerLayout mDrawer;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.drawer_layout);
            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
            mDrawer.addDrawerListener(toggle);
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
                    Intent intentAccount = new Intent(BaseActivity.this, AccountActivity.class);
                    startActivity(intentAccount);
                    break;
                case R.id.nav_payments:
                    Intent intentPayment = new Intent(BaseActivity.this, PaymentActivity.class);
                    startActivity(intentPayment);
                    break;
                case R.id.nav_cards:
                    Intent intentCard = new Intent(BaseActivity.this, CardActivity.class);
                    startActivity(intentCard);
                    break;
                case R.id.nav_bank_info:
                    Intent intentBankInfo = new Intent(BaseActivity.this, BankInfoActivity.class);
                    startActivity(intentBankInfo);
                    break;
                case R.id.nav_user_info:
                    Intent intentUserInfo = new Intent(BaseActivity.this, UserInfoActivity.class);
                    startActivity(intentUserInfo);
                    break;

            }
            mDrawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

