package com.example.myghmc;

import static com.example.myghmc.MainActivity.what;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home_login extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    private TextView recovery,sign_up;
    public static ApiService.driver_profile_response ddp=null;
    ProgressDialog p;
    String emailPat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser muser;
    FirebaseDatabase mdata;
    DatabaseReference databaseReference;
    private Button login;
    public static List<ApiService.driver_profile_response> driver_dat;
    public static String sat_glob;
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
                    firebase_login_method(mail,pass,Admin.class);
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
                mongoDb_login_method(sat_inp,pass,Driver.class);
                //firebase_login_method(input1,pass,Driver.class);
            }

        } else if (Objects.equals(what, "User")) {
            Toast.makeText(this, "Login in user", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(this,User.class);
            startActivity(i);
        }
    }
    private void mongoDb_login_method(String sat,String pass,Class a){
        p.setMessage("Please wait Logging in");
        p.setTitle("Login");
        p.setCanceledOnTouchOutside(false);
        p.show();
            String BASE_URL = BuildConfig.API_KEY;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);

                ApiService.LoginRequest request = new ApiService.LoginRequest();
                request.sat = sat;
                request.password = pass;

                apiService.loginUser(request).enqueue(new Callback<ApiService.LoginResponse>() {
                    @Override
                    public void onResponse(Call<ApiService.LoginResponse> call, Response<ApiService.LoginResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                Intent i= new Intent(getApplicationContext(),a);
                                email.setText("");
                                password.setText("");
                                sat_glob=sat;
                                load_data(sat);
                                p.dismiss();
                                String message = response.body().message;
                                Toast.makeText(home_login.this, message, Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(home_login.this, "please enter correct valid details", Toast.LENGTH_SHORT).show();
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
                        }catch (Exception e){
                        Toast.makeText(home_login.this, ""+e, Toast.LENGTH_SHORT).show();
                        Log.e("",e.getMessage());
                        p.dismiss();}
                    }

                    @Override
                    public void onFailure(Call<ApiService.LoginResponse> call, Throwable t) {
                        Toast.makeText(home_login.this, "failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    private void firebase_login_method(String mail,String pass,Class a) {
//        p.setMessage("Please wait Logging in");
//        p.setTitle("Login");
//        p.setCanceledOnTouchOutside(false);
//        p.show();
//        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                Toast.makeText(getApplicationContext(), "successful login", Toast.LENGTH_SHORT).show();
//                p.dismiss();
//                load_data(mail);
//                }
//                else
//                {
//                    p.dismiss();
//                    Toast.makeText(getApplicationContext(), "please enter correct valid details", Toast.LENGTH_SHORT).show();
//                }
//                //load req data of admin
//            }
//        });
    }
    private void load_data(String sat) {
        String BASE_URL = BuildConfig.API_KEY;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
//        ApiService.driver_profile_request request = new ApiService.driver_profile_request();
//        request.sat = sat_glob;
        apiService.driver_profile(sat).enqueue(new Callback<List<ApiService.driver_profile_response>>() {
            @Override
            public void onResponse(Call<List<ApiService.driver_profile_response>> call, Response<List<ApiService.driver_profile_response>> response) {
                try {
                    if (response.isSuccessful()) {
                        driver_dat = response.body();
                        String st = response.body().toString();
                        if (driver_dat != null && !driver_dat.isEmpty()) {
                            ddp = driver_dat.get(0);
                        } else {
                            Toast.makeText(home_login.this, st, Toast.LENGTH_SHORT).show();
                            Toast.makeText(home_login.this, "data not available", Toast.LENGTH_SHORT).show();
                        }
                        p.dismiss();
                    } else {
                        Toast.makeText(home_login.this, "data not taken", Toast.LENGTH_SHORT).show();
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
                }catch (Exception e)
                {Log.e("error",e.getMessage());}
            }
            @Override
            public void onFailure(Call<List<ApiService.driver_profile_response>> call, Throwable t) {
                Toast.makeText(home_login.this, "failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("msg",t.getMessage());
                p.dismiss();
            }
        });
//        apiService.driver_profile_nolist(request).enqueue(new Callback<ApiService.driver_profile_response>() {
//            @Override
//            public void onResponse(Call<ApiService.driver_profile_response> call, Response<ApiService.driver_profile_response> response) {
//                try{
//                    if(response.isSuccessful())
//                    {
//                        ddp=response.body();
//                        Toast.makeText(home_login.this, "data fetched successfully", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(home_login.this, "data not found", Toast.LENGTH_SHORT).show();
//                    }
//                    p.dismiss();
//                }
//                catch (Exception e){
//                    Toast.makeText(home_login.this, "Error occurred while taking data", Toast.LENGTH_SHORT).show();
//                    Log.e("msg",e.getMessage());
//                    p.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiService.driver_profile_response> call, Throwable t) {
//                Toast.makeText(home_login.this, "Error occurred while taking data", Toast.LENGTH_SHORT).show();
//                Log.e("msg",t.getMessage());
//                p.dismiss();
//            }
//        });
    }
}