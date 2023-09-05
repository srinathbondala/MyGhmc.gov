package com.example.myghmc;

import static com.example.myghmc.MainActivity.what;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class home_login extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    private TextView recovery,sign_up;
    public static driver_data ddp;
    ProgressDialog p;
    String emailPat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser muser;
    FirebaseDatabase mdata;
    DatabaseReference databaseReference;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        email = (EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.Password);
        recovery = (TextView) findViewById(R.id.recover_pass);
        sign_up = (TextView) findViewById(R.id.sign_up_org);
        login = (Button) findViewById(R.id.button);
        p = new ProgressDialog(this);
        login.setOnClickListener(this);
        recovery.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        mdata = FirebaseDatabase.getInstance();
        databaseReference = mdata.getReference();
        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        if(what=="Driver")
        {
            email.setHint("*SAT");
        }
        if(what=="User")
        {
            password.setVisibility(View.INVISIBLE);
            email.setHint("*Phone Number");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            verifyuser();
            if (Objects.equals(what, "Admin")) {
                Toast.makeText(this, "admin login", Toast.LENGTH_SHORT).show();
            } else if (Objects.equals(what, "Driver")) {
                Toast.makeText(this, "driver login", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "user login", Toast.LENGTH_SHORT).show();
            }
        }
         else if (view == sign_up) {
            if (Objects.equals(what, "Driver")) {
                Intent i = new Intent(this, MainActivity2.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "work in progress", Toast.LENGTH_SHORT).show();
            }
        }
    }
        private void verifyuser() {
        if(Objects.equals(what, "Admin"))
        {
            String mail=email.getText().toString();
            String pass = password.getText().toString();
            if(!mail.matches(emailPat))
                email.setError("Enter proper Email Id");
            else if(pass.equals("")||pass.length()<6){
                password.setError("Enter Password with 6 length");}
            else
                {
                    login_method(mail,pass,Admin.class);
                }
        }
        else if(Objects.equals(what, "Driver"))
        {
            String sat_inp= email.getText().toString();
            String pass = password.getText().toString();
            String input1=sat_inp+"@gmail.com";
            if(sat_inp.equals(""))
                email.setError("Enter valid SAT");
            else if(pass.equals("")||pass.length()<6){
                password.setError("Enter Password with 6 length");}
            else
            {
                login_method(input1,pass,Driver.class);
            }

        } else if (Objects.equals(what, "User")) {
            Toast.makeText(this, "Login in user", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(this,User.class);
            startActivity(i);
        }
    }

    private void login_method(String mail,String pass,Class a) {
        p.setMessage("Please wait Logging in");
        p.setTitle("Login");
        p.setCanceledOnTouchOutside(false);
        p.show();
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "successful login", Toast.LENGTH_SHORT).show();
                p.dismiss();
                Intent i= new Intent(getApplicationContext(),a);
                email.setText("");
                password.setText("");
                load_data();
                startActivity(i);
                }
                else
                {
                    p.dismiss();
                    Toast.makeText(getApplicationContext(), "please enter correct valid details", Toast.LENGTH_SHORT).show();
                }
                //load req data of admin
            }

            private void load_data() {
                String user_email1 = muser.getEmail();
                assert user_email1 != null;
                String user_email2=user_email1.replace("@gmail.com", "");
                databaseReference.child("Driver").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                driver_data i1 = ds.getValue(driver_data.class);
                                if (i1 != null && i1.getSat().equals(user_email2)) {
                                    ddp=i1;
                                    break;
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                });
            }
        });
    }
}