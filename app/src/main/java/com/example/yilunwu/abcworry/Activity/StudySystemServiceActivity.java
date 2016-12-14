package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

public class StudySystemServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_study_system_service);
        LayoutInflater inflater = (LayoutInflater) StudySystemServiceActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_study_system_service, null);
        setContentView(view);



    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.network:
                if (isNetWorkConnected(StudySystemServiceActivity.this)==true){
                    Toast.makeText(StudySystemServiceActivity.this,"网络已经打开",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(StudySystemServiceActivity.this,"网络未连接",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.enableOrDisable_WIFI:
                WifiManager wifiManager= (WifiManager) StudySystemServiceActivity.this.getSystemService(WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(StudySystemServiceActivity.this,"WIFI已经关闭",Toast.LENGTH_SHORT).show();
                }else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(StudySystemServiceActivity.this,"WIFI已经打开",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getvoice:
                AudioManager mAudioManager= (AudioManager) StudySystemServiceActivity.this.getSystemService(AUDIO_SERVICE);
                int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int current=mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
                Toast.makeText(StudySystemServiceActivity.this,"系统的最大音量为:"+max+",当前音量为:"+current,Toast.LENGTH_SHORT).show();
                break;
            case R.id.getPackagename:
                ActivityManager activityManager= (ActivityManager) StudySystemServiceActivity.this.getSystemService(ACTIVITY_SERVICE);
                String packageName = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(StudySystemServiceActivity.this,"当前运行的Activity的包名"+packageName,Toast.LENGTH_SHORT).show();



                break;

            default:
                break;
        }
    }

    public boolean isNetWorkConnected(Context context){
        if (context!=null){
            ConnectivityManager mConnectivityManager= (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mNetWorkInfo=mConnectivityManager.getActiveNetworkInfo();
            if (mNetWorkInfo!=null){
                return mNetWorkInfo.isAvailable();

            }

        }
        return false;


    }







}
