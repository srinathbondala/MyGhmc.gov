package com.example.myghmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class Admin extends AppCompatActivity implements View.OnClickListener{

    private ImageView open_drawer;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Objects.requireNonNull(getSupportActionBar()).hide();
        open_drawer = (ImageView) findViewById(R.id.open_drawer_button);
        drawerLayout = findViewById(R.id.drawer_layout2);
        open_drawer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==open_drawer)
        {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}