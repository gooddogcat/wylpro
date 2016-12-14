package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.yilunwu.abcworry.R;

public class StudyActivity2 extends Activity implements View.OnClickListener{

    public static final String TAG="nate";
    private Button button;
    private Button button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study2);
        Log.i(TAG, "StudyActivity2 onCreate");
        Log.i(StudyActivity.TAG, "StudyActivity2 taskId" + getTaskId());
        button= (Button) findViewById(R.id.button);
        button2= (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);



    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(StudyActivity.TAG, "StudyActivity2 onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "StudyActivity2 onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "StudyActivity2 onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "StudyActivity2 onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "StudyActivity2 onStop");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "StudyActivity2 onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "StudyActivity2 onDestroy");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent=new Intent(StudyActivity2.this,StudyActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1=new Intent(StudyActivity2.this,StudyActivity2.class);
                startActivity(intent1);
                break;
        }

    }
}
