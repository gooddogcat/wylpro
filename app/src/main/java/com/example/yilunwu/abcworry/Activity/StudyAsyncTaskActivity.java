package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.MyAsyncTask;

public class StudyAsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_async_task);
        MyAsyncTask task=new MyAsyncTask();
        task.execute();
    }

    public void loadImage(View view){
        startActivity(new Intent(this, ImageTestActivity.class));
    }

    public void loadProgress(View view){
        startActivity(new Intent(this,ProgressBarTestActivity.class));
    }




}
