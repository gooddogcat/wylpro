package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Service.MyBindService;
import com.example.yilunwu.abcworry.Service.MyStartService;

public class StudyServiceActivity extends Activity {
    Intent intent1;
    Intent intent2;
    MyBindService service;
    ServiceConnection conn=new ServiceConnection() {
        @Override//当启动源跟Service成功连接
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service=((MyBindService.MyBinder)binder).getService();
        }

        @Override//当启动源跟Service连接意外丢失
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_service);

    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.start:
                 intent1 = new Intent(StudyServiceActivity.this, MyStartService.class);
                startService(intent1);
                break;
            case R.id.stop:
                stopService(intent1);
                break;
            case R.id.bind:
                intent2=new Intent(StudyServiceActivity.this, MyBindService.class);
                bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(conn);
                break;
            case R.id.play:
                service.Play();
                break;
            case R.id.pause:
                service.Pause();
                break; 
            case R.id.next:
                service.Next();
                break;
            case R.id.previous:
                service.Previous();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        stopService(intent2);
        unbindService(conn);
        super.onDestroy();
    }
}
