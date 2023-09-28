package com.example.myghmc;

import static com.example.myghmc.home_login.ddp;
import static com.example.myghmc.home_login.driver_dat;
import static com.example.myghmc.home_login.sat_glob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profilefragment extends Fragment {
    private TextView name,sfi,sat1,ph_no,vh_no,driver_id;
    private ApiService.driver_profile_response dp;
    private Button logout;
    ProgressDialog p;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilefragment, container, false);
        name = view.findViewById(R.id.name_prof);
        sfi = view.findViewById(R.id.sfino_prof);
        sat1 = view.findViewById(R.id.sat_prof);
        ph_no = view.findViewById(R.id.phno_prof);
        vh_no = view.findViewById(R.id.VehicleNo_prof);
        driver_id = view.findViewById(R.id.driver_id_prof);
        logout=view.findViewById(R.id.logout);
        p = new ProgressDialog(view.getContext());
        p.setMessage("Please wait Loading");
        p.setTitle("Profile");
        p.setCanceledOnTouchOutside(false);
        p.show();
        try{if (ddp == null) {
            String BASE_URL = BuildConfig.API_KEY;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);
            ApiService.driver_profile_request request = new ApiService.driver_profile_request();
            request.sat = sat_glob;
            Toast.makeText(view.getContext(), sat_glob, Toast.LENGTH_SHORT).show();
            String satValue=sat_glob;
            apiService.driver_profile(sat_glob).enqueue(new Callback<List<ApiService.driver_profile_response>>() {
                @Override
                public void onResponse(Call<List<ApiService.driver_profile_response>> call, Response<List<ApiService.driver_profile_response>> response) {
                    try {
                        if (response.isSuccessful()) {
                            driver_dat = response.body();
                            if (driver_dat != null && !driver_dat.isEmpty()) {
                                ddp = driver_dat.get(0);
                            } else {
                                Toast.makeText(view.getContext(), "data not available", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(view.getContext(), "ok with input", Toast.LENGTH_SHORT).show();
                            p.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "data not taken", Toast.LENGTH_SHORT).show();
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
                    } catch (Exception e) {
                        Log.e("error", e.getMessage());
                        Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiService.driver_profile_response>> call, Throwable t) {
                    Toast.makeText(view.getContext(), "failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("msg", t.getMessage());
                    p.dismiss();
                }
            });
        } else {
            dp = ddp;
        }
        if (dp != null) {
            name.setText(dp.name);
            driver_id.setText(dp.driverId);
            sat1.setText(dp.sat);
            vh_no.setText(String.valueOf(dp.vehicleNumber));
            ph_no.setText(String.valueOf(dp.phoneNumber));
            sfi.setText(String.valueOf(dp.sfiNumber));
            p.dismiss();
        }
    }catch (Exception e) {
            Toast.makeText(view.getContext(), "error occurred while fetching data", Toast.LENGTH_SHORT).show();
            p.dismiss();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                try {
                    if (getActivity() != null) {
                        getActivity().finish();
                         getActivity().overridePendingTransition(R.anim.swipe_in_left, R.anim.swipe_out_right);
                    }
                }catch (Exception e)
                {
                    Toast.makeText(view.getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}