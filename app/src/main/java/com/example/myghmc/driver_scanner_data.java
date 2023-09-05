package com.example.myghmc;

public class driver_scanner_data {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public driver_scanner_data(String userId, String name, String phoneNumber, int zone,int circle, int ward,String timestamp,String driverSat) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timestamp = timestamp;
        this.circle = circle;
        this.ward = ward;
        this.zone = zone;
        this.driverSat = driverSat;
    }
    public driver_scanner_data(String data,String driverSat)
    {
        this.data=data;
        this.driverSat=driverSat;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {this.ward = ward;}

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    private String userId,name,phoneNumber,timestamp;
    private int circle,ward,zone;

    public String getDriverSat() {
        return driverSat;
    }

    public void setDriverSat(String driverSat) {
        this.driverSat = driverSat;
    }

    private String driverSat;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

}
