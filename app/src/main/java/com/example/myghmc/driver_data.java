package com.example.myghmc;

public class driver_data {
    String name;
    String phone_no,sfi_no,sat,vehicle_no,driver_id,password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getSfi_no() {
        return sfi_no;
    }

    public void setSfi_no(String sfi_no) {
        this.sfi_no = sfi_no;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public driver_data(String name, String phone_no, String sfi_no, String sat, String vehicle_no, String driver_id, String Password) {
        this.name = name;
        this.phone_no = phone_no;
        this.sfi_no = sfi_no;
        this.sat = sat;
        this.vehicle_no = vehicle_no;
        this.driver_id = driver_id;
        this.password = Password;
    }
    public driver_data(){}
}
