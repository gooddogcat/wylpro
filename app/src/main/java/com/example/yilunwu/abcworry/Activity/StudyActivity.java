package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;

public class StudyActivity extends Activity implements View.OnClickListener{



    public static final String TAG="nate";

    private Button button;

    private TextView textView;

    private MediaPlayer mediaPlayer;

    private int position;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "StudyActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString("name","nate");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Log.i(TAG, "StudyActivity onCreate");
        Log.i(TAG, "StudyActivity taskId"+getTaskId());
        button= (Button) findViewById(R.id.button1);
        textView= (TextView) findViewById(R.id.textView1);
        button.setOnClickListener(this);
        if (savedInstanceState!=null){
            textView.setText(savedInstanceState.getString("name"));
        }
        mediaPlayer=MediaPlayer.create(this,R.raw.sing);
        mediaPlayer.start();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"StudyActivity onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "StudyActivity onResume");
        if (position!=0){
            mediaPlayer.seekTo(position);
            mediaPlayer.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "StudyActivity onPause");
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            position=mediaPlayer.getCurrentPosition();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "StudyActivity onStop");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "StudyActivity onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "StudyActivity onDestroy");

        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study, menu);
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
        Intent intent=new Intent(StudyActivity.this,StudyActivity2.class);
        startActivity(intent);
    }
}
