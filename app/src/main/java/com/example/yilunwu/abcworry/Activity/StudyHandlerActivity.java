package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

public class StudyHandlerActivity extends Activity implements View.OnClickListener{

    private TextView textView;
    private Button button;
    //    private Handler handler=new Handler();
/*    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//        textView.setText(""+msg.arg1+"-"+msg.arg2);
            textView.setText("" + msg.obj);
            super.handleMessage(msg);
        }
    };*/

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),""+1,Toast.LENGTH_SHORT).show();

            return true;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),""+2,Toast.LENGTH_SHORT).show();


        }
    };
    private ImageView imageView;
    private int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int index;
    private MyRunnable myRunnable = new MyRunnable();

    @Override
    public void onClick(View v) {

//        handler.removeCallbacks(myRunnable);
          handler.sendEmptyMessage(1);
    }


    class Person {
        public int age;
        public String name;

        @Override
        public String toString() {
            return "name=" + name + "age=" + age;
        }
    }


    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            imageView.setImageResource(images[index]);
            handler.postDelayed(myRunnable, 1000);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_handler);
        textView = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.imageView);
        button= (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                   /* Message message=new Message();
                    message.arg1=88;
                    message.arg2=100;*/
                    Message message = handler.obtainMessage();
                    Person person = new Person();
                    person.age = 23;
                    person.name = "nate";
                    message.obj = person;
                    message.sendToTarget();
//                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        }.start();
        handler.postDelayed(myRunnable, 1000);

  /*      new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("update thread");

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();*/
    }


}
