package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;

public class StudyLooperActivity extends Activity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("UI-------->"+Thread.currentThread());
        }
    };

    class MyThread extends Thread {
        private Handler handler;
        public Looper looper;

        @Override
        public void run() {
            Looper.prepare();
            looper=Looper.myLooper();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("currentThread:" + Thread.currentThread());
                }

                ;
            };
            Looper.loop();
        }
    }

    private MyThread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_study_looper);
        TextView textView=new TextView(this);
        textView.setText("hello Handler");
        setContentView(textView);
        myThread=new MyThread();
        myThread.start();
     /*   try {
            myThread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);*/

        handler=new Handler(myThread.looper){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg);

            }
        };
        handler.sendEmptyMessage(1);



    }


}
