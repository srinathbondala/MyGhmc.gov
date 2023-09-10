package com.example.myghmc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    class LoginRequest {
        public String email;       // For admin role
        public String sat;         // For driver role
        public String phoneNumber; // For user role
        public String password;
    }

    class LoginResponse {
        public String message;
    }
    class driver_profile_request{
        public String sat;
    }
    class driver_profile_response {
        public String name;
        public Long phoneNumber;
        public Long sfiNumber;
        public String sat;
        public Long vehicleNumber;
        public String password;
        public String driverId;
        public String role;

        public driver_profile_response( String name, Long phoneNumber, String password, String role, Long sfiNumber, String sat, Long vehicleNumber, String driverId) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.sfiNumber = sfiNumber;
            this.sat = sat;
            this.vehicleNumber = vehicleNumber;
            this.password = password;
            this.driverId = driverId;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Long getSfiNumber() {
            return sfiNumber;
        }

        public void setSfiNumber(Long sfiNumber) {
            this.sfiNumber = sfiNumber;
        }

        public String getSat() {
            return sat;
        }

        public void setSat(String sat) {
            this.sat = sat;
        }

        public Long getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(Long vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public driver_profile_response(){}
    }

    @POST("users/driver/login")
    Call<LoginResponse> loginUser(@Body LoginRequest request);
    @GET("users/drivers/fetch")
    Call<List<driver_profile_response>> driver_profile(@Query("sat") String satValue);
    @POST("/driver/register")
    Call<LoginResponse> driver_register(@Body driver_profile_response dpr);
}

