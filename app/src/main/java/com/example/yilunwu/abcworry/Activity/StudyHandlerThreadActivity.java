package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;

public class StudyHandlerThreadActivity extends Activity {

    private TextView text;
    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_study_looper_thread);
        text=new TextView(this);
        text.setText("handler Thread");
        setContentView(text);
        thread=new HandlerThread("handler thread");
        thread.start();
        handler=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("current thread---------->"+Thread.currentThread());
            }
        };

        handler.sendEmptyMessage(1);





    }


}
