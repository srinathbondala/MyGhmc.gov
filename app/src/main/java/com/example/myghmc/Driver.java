package com.example.myghmc;

import static android.widget.Toast.LENGTH_SHORT;

import static com.example.myghmc.home_login.ddp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Driver extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;
    private ImageView openDrawerButton,home_icon,scan_icon,profile_icon;
    private TextView home,scan,profile;
    String demo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        Objects.requireNonNull(getSupportActionBar()).hide();
        drawerLayout = findViewById(R.id.drawer_layout1);
        openDrawerButton = findViewById(R.id.open_drawer_button);
        home = (TextView)findViewById(R.id.driver_home_Idx);
        scan=(TextView)findViewById(R.id.driver_scanner);
        profile=(TextView)findViewById(R.id.driver_profile);
        home_icon = (ImageView)findViewById(R.id.driver_house_ind1);
        scan_icon=(ImageView)findViewById(R.id.driver_scanner_ind1);
        profile_icon=(ImageView)findViewById(R.id.driver_profile_ind1);
        openDrawerButton.setOnClickListener(this);
        home_icon.setOnClickListener(this);
        scan_icon.setOnClickListener(this);
        profile_icon.setOnClickListener(this);
        home.setOnClickListener(this);
        scan.setOnClickListener(this);
        profile.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content_frame1, new homefragment()).commit();
        demo1="home";
    }

    @Override
    public void onClick(View view) {
        if(view==openDrawerButton) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if(view == home || view==home_icon)
        {
            assert home != null;
            home.setBackgroundColor(Color.parseColor("#00ddff"));
            scan.setBackgroundColor(0);
            profile.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new homefragment())
                    .commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            demo1="home";
        }
        if(view==scan || view==scan_icon)
        {
            assert scan != null;
            scan.setBackgroundColor(Color.parseColor("#00ddff"));
            home.setBackgroundColor(0);
            profile.setBackgroundColor(0);
            drawerLayout.closeDrawer(GravityCompat.START);
            demo1="scan";
            requestCameraPermission();
        }
        if(view==profile || view==profile_icon)
        {
            assert profile != null;
            profile.setBackgroundColor(Color.parseColor("#00ddff"));
            scan.setBackgroundColor(0);
            home.setBackgroundColor(0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new profilefragment())
                    .commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            demo1="profile";
        }
    }

    private static final int CAMERA_REQUEST_CODE = 100;

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "started", Toast.LENGTH_LONG).show();
            startQRScan();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startQRScan();
            } else {
                Toast.makeText(this, "Camera permission is required to scan QR code.", LENGTH_SHORT).show();
            }
        }
    }
    private void startQRScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String scannedData = result.getContents();
                try {
                    interpretScannedData(scannedData);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void interpretScannedData(String data) throws JSONException {
        JSONObject jsonObj = new JSONObject(data);
            String BASE_URL = BuildConfig.API_KEY;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitapi retro = retrofit.create(retrofitapi.class);
            retrofitapi.driver_scanner_data1 request = new retrofitapi.driver_scanner_data1();
            request.userId=ddp.getSat();
            request.driverSat =jsonObj.getString("userId") ;
            retro.enterData(request).enqueue(new Callback<retrofitapi.driver_scanner_data_out>() {
                @Override
                public void onResponse(Call<retrofitapi.driver_scanner_data_out> call, Response<retrofitapi.driver_scanner_data_out> response) {
                    if (response.isSuccessful()) {
                        String message = response.body().message;
                        if(Objects.equals(message, "success")) {
                            Toast.makeText(Driver.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                         }
                        else {
                            Toast.makeText(Driver.this, message, LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<retrofitapi.driver_scanner_data_out> call, Throwable t) {
                    Toast.makeText(Driver.this, "Technical Error. Please try again later", LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public void onBackPressed() {
        if(!Objects.equals(demo1, "home")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame1, new homefragment())
                    .commit();
            demo1="home";
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Driver.this);

            builder.setMessage("Do you want to Go back to login ?");

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