package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

public class StudyGestureDetectorActivity extends Activity {
    ImageView img;
    GestureDetector myGestureDetector;

    class myGestrueListener extends SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX()-e2.getX()>50){
                Toast.makeText(StudyGestureDetectorActivity.this,"从右往左滑",Toast.LENGTH_LONG).show();
            }else if (e2.getX()-e1.getX()>50){
                Toast.makeText(StudyGestureDetectorActivity.this,"从左往右滑",Toast.LENGTH_LONG).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_gesture_detector);
        img= (ImageView) findViewById(R.id.imageView);
        myGestureDetector=new GestureDetector(new myGestrueListener());
        img.setOnTouchListener(new View.OnTouchListener() {
            //可以捕获到触摸屏幕发生的Event事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });



    }


}
