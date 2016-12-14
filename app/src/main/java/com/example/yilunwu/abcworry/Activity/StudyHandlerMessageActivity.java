package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.yilunwu.abcworry.R;

public class StudyHandlerMessageActivity extends Activity implements View.OnClickListener{
    //创建主线程的Handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Message message=new Message();
            System.out.println("main Handler");
            //向子线程发送消息
            threadHandler.sendMessageDelayed(message,1000);
        }
    };

    private Handler threadHandler;

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_handler_message);
        button1= (Button) findViewById(R.id.button5);
        button2= (Button) findViewById(R.id.button6);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        HandlerThread thread=new HandlerThread("handlerThread");
        thread.start();
        //创建子线程的Handler
        threadHandler=new Handler(thread.getLooper()){
            Message message=new Message();

            @Override
            public void handleMessage(Message msg) {
                System.out.println("thread Handler");
                //向主线程发送消息
                handler.sendMessageDelayed(message,1000);
            }
        };


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button5:
                handler.sendEmptyMessage(1);
                break;
            case R.id.button6:
                handler.removeMessages(1);
                break;
            default:
                break;
        }
    }
}
