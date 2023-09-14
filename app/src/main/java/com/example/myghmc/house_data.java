package com.example.myghmc;

public class house_data {
    private String House_no;
    private String Time;

    public String getHouse_no() {
        return House_no;
    }

    public void setHouse_no(String house_no) {
        House_no = house_no;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public house_data(String house_no, String time) {
        House_no = house_no;
        Time = time;
    }
}
