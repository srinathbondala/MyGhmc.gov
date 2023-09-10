package com.example.myghmc;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface retrofitapi {
    class driver_scanner_data1 {
        public String userId;
        public String driverSat;
    }

    class driver_scanner_data_out {
        public String message;
    }
    @POST ("users/driver/house-scanned-info")
    Call<driver_scanner_data_out> enterData(@Body driver_scanner_data1 request);
}
