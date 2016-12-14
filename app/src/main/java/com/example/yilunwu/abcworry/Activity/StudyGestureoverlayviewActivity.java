package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

import java.util.ArrayList;

public class StudyGestureoverlayviewActivity extends Activity {
    GestureOverlayView gestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_gestureoverlayview);
     /*   gestureOverlayView= (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
        //1找到预设定的手势文件gestures 2加载那个手势文件中的所有手势  3匹配 识别 4从资源中将手势库文件加载进来
        final GestureLibrary library=GestureLibraries.fromRawResource(StudyGestureoverlayviewActivity.this,R.raw.gestures);
        library.load();
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {

            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                //读出手势库中的内容  识别手势
                ArrayList<Prediction> mygesture=library.recognize(gesture);
                Prediction prediction = mygesture.get(0);
                if (prediction.score>=5.0){
                    if (prediction.name.equals("exit")){
                        finish();
                    }if (prediction.name.equals("next")){
                        Toast.makeText(StudyGestureoverlayviewActivity.this,"播放下一首歌",Toast.LENGTH_SHORT).show();

                    }
                    if (prediction.name.equals("previous")){
                        Toast.makeText(StudyGestureoverlayviewActivity.this,"播放上一首歌",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(StudyGestureoverlayviewActivity.this,"没有该手势",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }


}
