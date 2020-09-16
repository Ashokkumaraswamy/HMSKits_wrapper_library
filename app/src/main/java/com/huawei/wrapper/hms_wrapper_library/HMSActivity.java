package com.huawei.wrapper.hms_wrapper_library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hmskit.wrapper_library.HMS_Kits_Wrapper;

public class HMSActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HMS_Kits_Wrapper.registerReceiver(this);
    }
}
