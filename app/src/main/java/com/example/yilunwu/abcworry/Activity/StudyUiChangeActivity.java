package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;

/**
 * 更新UI的四种方式
 */
public class StudyUiChangeActivity extends Activity {
    private TextView textView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("ok");
        }
    };

    private void handler1(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok");
            }
        });

    }


    private void handler2(){
        handler.sendEmptyMessage(1);
    }


    private void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok");

            }
        });
    }

    private void viewUI(){
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok");

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_ui_change);
        textView= (TextView) findViewById(R.id.textviewui);
        new Thread(){
            @Override
            public void run() {
//                textView.setText("ok");
//
                Handler handler=new Handler();


                try {
                    Thread.sleep(2000);
//                    handler1();
//                    handler2();
//                    updateUI();
                      viewUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }


}
