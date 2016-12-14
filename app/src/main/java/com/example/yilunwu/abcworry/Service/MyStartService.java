package com.example.yilunwu.abcworry.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyStartService extends Service {
    public MyStartService() {
    }

    @Override
    public void onCreate() {
        Log.i("info","Service--onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info","Service--onBind");

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info","Service--onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("info","Service--onDestroy");

        super.onDestroy();
    }
}
