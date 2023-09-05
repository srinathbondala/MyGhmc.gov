package com.example.myghmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private EditText name,phno,sfino,sat,vehicleno,password;
    Button register;
    ProgressDialog p;
    FirebaseAuth mAuth;
    private DatabaseReference root,dfroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        name = (EditText) findViewById(R.id.name1);
        phno = (EditText) findViewById(R.id.phoneno_reg);
        sfino = (EditText) findViewById(R.id.sfino_reg);
        sat = (EditText) findViewById(R.id.sat);
        vehicleno = (EditText) findViewById(R.id.vehicle_reg);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.submit1);
        root = FirebaseDatabase.getInstance().getReference().child("Driver");
        dfroot=FirebaseDatabase.getInstance().getReference().child("Driver Id");
        mAuth = FirebaseAuth.getInstance();
        dfroot.setValue(0000);
        p = new ProgressDialog(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_driver();
            }
        });
    }

    private void register_driver() {
        String sname = name.getText().toString();
        String sphno = phno.getText().toString();
        String ssfino = sfino.getText().toString();
        String ssat = sat.getText().toString();
        String svechno = vehicleno.getText().toString();
        String spass = password.getText().toString();
        if (sname.equals("")) {
            name.setError("enter User Name");
        } else if (sfino.equals(""))
            name.setError("enter your SFI number");
        else if (sphno.equals("") || sphno.length() < 10)
            phno.setError("enter valid phone Number");
        else if (ssfino.equals("") || sfino.length()<2)
            sfino.setError("enter 2 Digit sfi no");
        else if (ssat.equals(""))
            sat.setError("enter SAT");
        else if (svechno.equals(""))
            vehicleno.setError("enter Vehicle Number");
        else if (spass.equals("") || spass.length() < 6)
            password.setError("enter valid password");
        else{
            p.setMessage("Please Wait Registering");
            p.setTitle("Registering");
            p.setCanceledOnTouchOutside(false);
            p.show();
            String driver_id="DRIVER00";
            driver_data dd = new driver_data(sname,sphno,ssfino,ssat,svechno,driver_id,spass);
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(ssat))
                    {
                        Toast.makeText(MainActivity2.this, "User already exist", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                    else {
                        if(search_for_user_sfi(ssfino)) {
                            root.child(ssat).setValue(dd).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        p.dismiss();
                                        String mail=ssat+"@gmail.com";
                                        mAuth.createUserWithEmailAndPassword(mail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    FirebaseUser user = mAuth.getCurrentUser();

                                                    Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                                        p.dismiss();
                                    }
                                }
                            });
                        }
                        else {
                            p.dismiss();
                            Toast.makeText(MainActivity2.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private boolean search_for_user_sfi(String ssfino) {
        boolean[] ret = {true};
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    driver_data i1 = ds.getValue(driver_data.class);
                    assert i1 != null;
                    if(Objects.equals(i1.getSfi_no(), ssfino)) {
                        ret[0] = false;
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return ret[0];
    }
}