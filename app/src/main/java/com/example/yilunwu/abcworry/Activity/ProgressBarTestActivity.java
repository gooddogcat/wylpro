package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.yilunwu.abcworry.R;

public class ProgressBarTestActivity extends Activity {
    private ProgressBar mProgressBar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);
        mProgressBar= (ProgressBar) findViewById(R.id.pg);
        mTask=new MyAsyncTask();
        mTask.execute();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTask!=null&&mTask.getStatus()==AsyncTask.Status.RUNNING){
            //cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            mTask.cancel(true);

        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i=0;i<100;i++){
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            //获取进度更新值
            mProgressBar.setProgress(values[0]);
        }
    }



}
