package com.huawei.wrapper.hms_wrapper_library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class HMS_Kits_Wrapper {
    public static String TAG = HMS_Kits_Wrapper.class.getSimpleName();

    public void registerReceiver(Context context) {
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.huawei.wrapper.hms_wrapper_library.ON_NEW_TOKEN");
        context.registerReceiver(receiver,filter);
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.huawei.wrapper.hms_wrapper_library.ON_NEW_TOKEN".equals(intent.getAction())) {
                String token = intent.getStringExtra("token");
                Toast.makeText(context, "Token: "+token, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getToken(final Context context) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(context).getString("client/app_id");
                    String token = HmsInstanceId.getInstance(context).getToken(appId, "HCM");
                    Log.i(TAG, "get token:" + token);
                    if(!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(context, token);
                    }
                } catch (ApiException e) {
                    Log.e(TAG, "get token failed, " + e);
                }
            }
        }.start();
    }

    public void sendRegTokenToServer(Context context, String token) {
        Log.i(TAG, "sending token to server. token:" + token);
        Toast.makeText(context, "token: "+token, Toast.LENGTH_SHORT).show();
    }
}
