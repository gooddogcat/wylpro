package com.example.yilunwu.abcworry.Service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    public MyBindService() {

    }

    public class MyBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info", "BindService--onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("info", "BindService--onCreate");

    }



    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i("info", "BindService--unbindService");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info", "BindService--onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.i("info", "BindService--onDestroy");

        super.onDestroy();

    }

    public void Play(){
        Log.i("info", "播放");
    }

    public void Pause(){
        Log.i("info","暂停");
    }

    public void Previous(){
        Log.i("info","上一首");
    }

    public void Next(){
        Log.i("info","下一首");
    }
}
