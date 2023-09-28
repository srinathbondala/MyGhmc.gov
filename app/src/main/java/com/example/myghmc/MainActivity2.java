package com.example.myghmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {
    private EditText name;
    private EditText phno;
    private EditText sfino;
    private EditText sat;
    private EditText vehicleno;
    private EditText password;
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
        }else if (sphno.equals("") || sphno.length() < 10)
            phno.setError("enter valid phone Number");
        else if (ssfino.equals("") || sfino.length()<3)
            sfino.setError("enter your SFI number");
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
            data_register(sname,sphno,ssfino,ssat,svechno,spass);
        }
    }

    private void data_register(String sname, String sphno, String ssfino, String ssat, String svechno, String spass) {
        String BASE_URL = BuildConfig.API_KEY;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService retro_aip = retrofit.create(ApiService.class);
        ApiService.driver_profile_response data = new ApiService.driver_profile_response();
        data.name=sname;
        data.password=spass;
        data.sfiNumber= Long.valueOf(ssfino);
        data.vehicleNumber= Long.valueOf(svechno);
        data.phone=Long.valueOf(sphno);
        data.sat=ssat;
        data.role="driver";
        Toast.makeText(this, "ready to register", Toast.LENGTH_SHORT).show();
        retro_aip.driver_register1(data).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    if(response.isSuccessful())
                    {
                        Toast.makeText(MainActivity2.this, "Successfully Registered"+response.message(), Toast.LENGTH_SHORT).show();
                        p.dismiss();
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity2.this, "process failed", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                        Log.e("API_ERROR", "Error response code: " + response.code());
                        if (response.errorBody() != null) {
                            try {
                                Log.e("API_ERROR_BODY", response.errorBody().string());
                            } catch (IOException e) {
                                Log.e("API_ERROR_BODY", "Error reading error body.", e);
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity2.this, "failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    p.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                p.dismiss();
            }
        });
    }
}