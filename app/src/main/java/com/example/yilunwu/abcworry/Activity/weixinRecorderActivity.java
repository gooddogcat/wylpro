package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.adapter.RecorderAdapter;
import com.example.yilunwu.abcworry.view.recorder.AudioRecorderButton;
import com.example.yilunwu.abcworry.view.recorder.MediaManager;

import java.util.ArrayList;
import java.util.List;

public class weixinRecorderActivity extends Activity {


    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDates=new ArrayList<Recorder>();

    private AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_recorder);

        mListView= (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton= (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filepath) {
                Recorder recorder=new Recorder(seconds,filepath);
                mDates.add(recorder);
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(mDates.size()-1);
            }
        });

        mAdapter=new RecorderAdapter(this,mDates);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAnimView!=null)
                {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView=null;
                }
                //播放动画
                mAnimView=view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim= (AnimationDrawable) mAnimView.getBackground();
                anim.start();
                //播放音频
                MediaManager.playSound(mDates.get(position).filepath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.adj);
                    }
                });

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }


    public class Recorder
    {
        float time;
        String filepath;

        public Recorder(float time, String filepath) {
            this.time = time;
            this.filepath = filepath;
        }

        public float getTime() {
            return time;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }
    }

}
