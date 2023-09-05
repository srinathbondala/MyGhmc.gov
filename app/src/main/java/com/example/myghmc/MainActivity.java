package com.example.myghmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Admin,Driver,User;
    private TextView about,faq;
    private ProgressDialog p;
    static String what="dum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Admin = (Button) findViewById(R.id.admin);
        Driver=(Button) findViewById(R.id.driver);
        User =(Button)findViewById(R.id.user);
        faq = (TextView) findViewById(R.id.faq);
        about = (TextView) findViewById(R.id.about);
        p = new ProgressDialog(this);
        Admin.setOnClickListener(this);
        Driver.setOnClickListener(this);
        User.setOnClickListener(this);
        faq.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == Admin)
        {
            what="Admin";
            Intent i = new Intent(this,home_login.class);
            startActivity(i);
        }
        else if(view==Driver)
        {
            what = "Driver";
            Intent i = new Intent(this,home_login.class);
            startActivity(i);
        }
        else if(view==User)
        {
            what="User";
            Intent i = new Intent(this,home_login.class);
            startActivity(i);
        } else if (view==about) {
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Do you want to exit ?");

            builder.setTitle("Alert !");

            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                finish();
            });

            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }
}