package com.huawei.hmskit.wrapper_library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class HMS_Kits_Wrapper {

    public static void registerReceiver(Context context) {
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.huawei.hmskit.wrapper_library.ON_NEW_TOKEN");
        context.registerReceiver(receiver,filter);
    }

    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.huawei.hmskit.wrapper_library.ON_NEW_TOKEN".equals(intent.getAction())) {
                String token = intent.getStringExtra("token");
                Toast.makeText(context, "Token: "+token, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
