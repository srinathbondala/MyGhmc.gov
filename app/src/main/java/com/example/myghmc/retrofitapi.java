package com.example.myghmc;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface retrofitapi {
    @POST ("/driver/house-scanned-info")
    Call<ResponseBody> enterData(@Body driver_scanner_data request);
    @POST("/driver/house-scanned-info")
    Call<ResponseBody> enterData1(@Body HashMap<String,String> map);

}
