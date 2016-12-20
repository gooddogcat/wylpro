package com.example.yilunwu.abcworry.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.yilunwu.abcworry.utils.FloatViewManager;

public class MyFloatService extends Service {
    public MyFloatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FloatViewManager manager = FloatViewManager.getInstance(this);
        manager.showFloatCircleView();
    }
}
