package com.example.myghmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class User extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;
    private ImageView openDrawerButton;
    private TextView home,scan,profile,track_user;
    String demo;
//    private TextView qr_code,lit_qr,details,lit_details,user_faq,lit_faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();

        drawerLayout = findViewById(R.id.drawer_layout2);
        openDrawerButton = findViewById(R.id.open_drawer_button);
        home = (TextView)findViewById(R.id.driver_home_Idx);
        scan=(TextView)findViewById(R.id.driver_scanner);
        profile=(TextView)findViewById(R.id.admin_driver);
        track_user = (TextView) findViewById(R.id.user_track_vehicle);
        openDrawerButton.setOnClickListener(this);
        home.setOnClickListener(this);
        scan.setOnClickListener(this);
        track_user.setOnClickListener(this);
        profile.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content_frame1,  new user_home_fragment()).commit();
        demo="home";
    }

    @Override
    public void onClick(View view) {
        if(view==openDrawerButton) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if(view == home)
        {
            assert home != null;
            home.setBackgroundColor(Color.parseColor("#00ddff"));
            scan.setBackgroundColor(0);
            profile.setBackgroundColor(0);
            track_user.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new user_home_fragment())
                    .commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            demo="home";
        }
        if(view==scan)
        {
            assert scan != null;
            scan.setBackgroundColor(Color.parseColor("#00ddff"));
            home.setBackgroundColor(0);
            profile.setBackgroundColor(0);
            track_user.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new user_report_fragment())
                    .commit();

            drawerLayout.closeDrawer(GravityCompat.START);
            demo="scan";
        }
        if(view==profile)
        {
            assert profile != null;
            profile.setBackgroundColor(Color.parseColor("#00ddff"));
            scan.setBackgroundColor(0);
            home.setBackgroundColor(0);
            track_user.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new profile_user_fragment())
                    .commit();

            drawerLayout.closeDrawer(GravityCompat.START);
            demo="profile";
        }
        if(view==track_user)
        {
            assert track_user != null;
            track_user.setBackgroundColor(Color.parseColor("#00ddff"));
            scan.setBackgroundColor(0);
            home.setBackgroundColor(0);
            profile.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new track_user())
                    .commit();

            drawerLayout.closeDrawer(GravityCompat.START);
            demo="track_user";
        }
    }

    @Override
    public void onBackPressed() {
        if(!Objects.equals(demo, "home")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new user_home_fragment())
                    .commit();
            demo="home";
        }
        else
        {
            super.onBackPressed();
        }
    }
}