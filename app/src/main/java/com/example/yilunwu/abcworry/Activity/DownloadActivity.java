package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Service.DownloadService;
import com.example.yilunwu.abcworry.utils.FileInfo;

//断点续传
public class DownloadActivity extends Activity {
    private TextView mTvFileName=null;
    private ProgressBar mPbProgress=null;
    private Button mBtnStop=null;
    private Button mBtnStart=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        //初始化组件
        mTvFileName= (TextView) findViewById(R.id.tvFileName);
        mPbProgress= (ProgressBar) findViewById(R.id.pbProgress);
        mBtnStop= (Button) findViewById(R.id.btnStop);
        mBtnStart= (Button) findViewById(R.id.btnStart);
        mPbProgress.setMax(100);
        //创建文件信息对象
        final FileInfo fileInfo=new FileInfo(0,"http://dlsw.baidu.com/sw-search-sp/soft/1a/11798/kugou_V7.6.85.17344_setup.1427079848.exe",
        "kugou_V7.6.85.17344_setup.1427079848.exe",0,0);
        //添加事件监听
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Intent传递参数给Service
                Intent intent=new Intent(DownloadActivity.this, DownloadService.class);
                intent.setAction(DownloadService.ACTION_START);
                intent.putExtra("fileInfo",fileInfo);
                startService(intent);

            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Intent传递参数给Service
                Intent intent=new Intent(DownloadActivity.this, DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra("fileInfo", fileInfo);
                startService(intent);

            }
        });

        //注册广播接收器
        IntentFilter filter=new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(mReceiver,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * 更新UI的广播接收器
     */

    BroadcastReceiver mReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.getAction())){
                int finish=intent.getIntExtra("finished",0);
                mPbProgress.setProgress(finish);
            }
        }
    };




}
