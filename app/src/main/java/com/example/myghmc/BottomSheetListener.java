package com.example.myghmc;

import org.json.JSONException;

public interface BottomSheetListener {
    void onBottomSheetDismissed();
    void initialize() throws JSONException;
}