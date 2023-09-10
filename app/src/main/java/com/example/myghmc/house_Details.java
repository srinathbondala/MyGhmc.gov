package com.example.myghmc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class house_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipe_in_left,
                R.anim.swipe_out_right);
    }
}