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
import android.widget.ProgressBar;
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
    private EditText email,password;
    private Button Admin,Driver,User,login;
    private TextView faq,signup,email_t,title,pass_t;
    private ProgressDialog p;
    private ConstraintLayout cnl;
    static String what="dum";
    FirebaseAuth mAuth;
    FirebaseUser muser;
    FirebaseDatabase mdata;
    DatabaseReference databaseReference;
    String emailPat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        faq = (TextView) findViewById(R.id.faqs);
        Admin = (Button) findViewById(R.id.admin);
        Driver=(Button) findViewById(R.id.driver);
        User =(Button)findViewById(R.id.user);
        login =(Button)findViewById(R.id.login);
        password =(EditText)findViewById(R.id.Password);
        email = (EditText)findViewById(R.id.email);
        pass_t= (TextView) findViewById(R.id.textView6);
        signup = (TextView)findViewById(R.id.sign_up);
        email_t =(TextView)findViewById(R.id.textView5);
        title = (TextView)findViewById(R.id.titledilog);
        p = new ProgressDialog(this);
        cnl = (ConstraintLayout) findViewById(R.id.loginbox);
        faq.setOnClickListener(this);
        Admin.setOnClickListener(this);
        Driver.setOnClickListener(this);
        User.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        cnl.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        mdata = FirebaseDatabase.getInstance();
        databaseReference = mdata.getReference();
        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        if (view==faq)
        {
            Toast.makeText(this, "frequent asked questions", Toast.LENGTH_SHORT).show();
        }
        else if(view == Admin)
        {
            title.setText("Admin Login");
            email_t.setText("*Email:");
            email.setHint("Email");
            what="Admin";
            Admin.setVisibility(View.INVISIBLE);
            Driver.setVisibility(View.INVISIBLE);
            User.setVisibility(View.INVISIBLE);
            cnl.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            pass_t.setVisibility(View.VISIBLE);
        }
        else if(view==Driver)
        {
            title.setText("Driver Login");
            email_t.setText("*SAT:");
            email.setHint("SAT");
            what = "Driver";
            Admin.setVisibility(View.INVISIBLE);
            Driver.setVisibility(View.INVISIBLE);
            User.setVisibility(View.INVISIBLE);
            cnl.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            pass_t.setVisibility(View.VISIBLE);
        }
        else if(view==User)
        {
            title.setText("User Login");
            email_t.setText("*Phone No: ");
            email.setHint("Phone No");
            what="User";
            pass_t.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            Admin.setVisibility(View.INVISIBLE);
            Driver.setVisibility(View.INVISIBLE);
            User.setVisibility(View.INVISIBLE);
            cnl.setVisibility(View.VISIBLE);
        } else if (view==login) {

            verifyuser();
        }
        else if(view==signup)
        {
            if(Objects.equals(what, "Driver")){
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
            else{
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
                Toast.makeText(MainActivity.this, "successful login", Toast.LENGTH_SHORT).show();
                p.dismiss();
                Intent i= new Intent(MainActivity.this,a);
                email.setText("");
                password.setText("");
                startActivity(i);
                load_data();
                }
                else
                {
                    p.dismiss();
                    Toast.makeText(MainActivity.this, "please enter correct valid details", Toast.LENGTH_SHORT).show();
                }
                //load req data of admin
            }
        });
    }

    private void load_data() {
    }

    @Override
    public void onBackPressed() {
        if(!Objects.equals(what, "dum"))
        {
            Admin.setVisibility(View.VISIBLE);
            Driver.setVisibility(View.VISIBLE);
            User.setVisibility(View.VISIBLE);
            cnl.setVisibility(View.INVISIBLE);
            email.setText("");
            password.setText("");
            what="dum";
        }
        else
        {
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
}