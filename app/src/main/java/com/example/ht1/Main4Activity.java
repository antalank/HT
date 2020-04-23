package com.example.ht1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Main4Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

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
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
            case R.id.nav_payments:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new PaymentFragment()).commit();
                break;
            case R.id.nav_cards:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new CardFragment()).commit();
                break;
            case R.id.nav_bank_info:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new BankInfoFragment()).commit();
                break;
            case R.id.nav_user_info:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new UserInfoFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
