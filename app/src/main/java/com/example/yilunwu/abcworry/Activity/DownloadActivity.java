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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Service.DownloadService;
import com.example.yilunwu.abcworry.adapter.FileListAdapter;
import com.example.yilunwu.abcworry.utils.FileInfo;

import java.util.ArrayList;
import java.util.List;

//断点续传
public class DownloadActivity extends Activity {
     private ListView mLvFile=null;
     private List<FileInfo> mFileList=null;
     private FileListAdapter mAdapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        //初始化组件
        mLvFile= (ListView) findViewById(R.id.lvFile);
        //创建文件集合
        mFileList=new ArrayList<FileInfo>();

        //创建文件信息对象
        final FileInfo fileInfo=new FileInfo(0,"http://www.imooc.com/mobile/imooc.apk",
        "imooc.apk",0,0);
        final FileInfo fileInfo1=new FileInfo(1,"http://www.imooc.com/download/Activator.exe",
                "Activator.exe",0,0);
        final FileInfo fileInfo2=new FileInfo(2,"http://www.imooc.com/download/iTunes64Setup.exe",
                "iTunes64Setup.exe",0,0);
        final FileInfo fileInfo3=new FileInfo(3,"http://dlsw.baidu.com/sw-search-sp/soft/1a/11798/kugou_V7.6.85.17344_setup.1427079848.exe",
                "kugou_V7.6.85.17344_setup.1427079848.exe",0,0);


        mFileList.add(fileInfo);
        mFileList.add(fileInfo1);
        mFileList.add(fileInfo2);
        mFileList.add(fileInfo3);
        //创建适配器
        mAdapter=new FileListAdapter(this,mFileList);
        //设置ListView
        mLvFile.setAdapter(mAdapter);
        //注册广播接收器
        IntentFilter filter=new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        filter.addAction(DownloadService.ACTION_FINISH);
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
                //更新进度条
                int finished=intent.getIntExtra("finished",0);
                int id=intent.getIntExtra("id",0);
                mAdapter.updateProgress(id,finished);
            }else if (DownloadService.ACTION_FINISH.equals(intent.getAction())){
                //下载结束
               FileInfo fileInfo= (FileInfo) intent.getSerializableExtra("fileInfo");
                //更新进度为0
                mAdapter.updateProgress(fileInfo.getId(),0);
                Toast.makeText(DownloadActivity.this,mFileList.get(fileInfo.getId()).getFileName()+"下载完毕",
                        Toast.LENGTH_SHORT).show();

            }
        }
    };




}
