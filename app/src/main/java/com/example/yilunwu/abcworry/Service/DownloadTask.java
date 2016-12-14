package com.example.yilunwu.abcworry.Service;

/**
 * Created by yilunwu on 16/12/14.
 */

import android.content.Context;
import android.content.Intent;

import com.example.yilunwu.abcworry.iml.ThreadDAO;
import com.example.yilunwu.abcworry.impl.ThreadDAOImpl;
import com.example.yilunwu.abcworry.utils.FileInfo;
import com.example.yilunwu.abcworry.utils.ThreadInfo;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 下载任务类
 */
public class DownloadTask {
    private Context mContext = null;
    private FileInfo mFileInfo = null;
    private ThreadDAO mDao = null;
    private int mFinished = 0;
    public boolean isPause = false;

    public DownloadTask(Context context, FileInfo mFileInfo) {
        this.mContext = context;
        this.mFileInfo = mFileInfo;
        mDao = new ThreadDAOImpl(mContext);

    }

    public void download(){
        //读取数据库的线程信息
        List<ThreadInfo> threadInfos=mDao.getThreads(mFileInfo.getUrl());
        ThreadInfo threadInfo=null;
        if (threadInfos.size()==0){
            //初始化线程信息对象
            threadInfo=new ThreadInfo(0,mFileInfo.getUrl(),0,mFileInfo.getLength(),0);
        }else {
            threadInfo=threadInfos.get(0);

        }
        //创建子线程进行下载
        new DownloadThread(threadInfo).start();

    }

    /**
     * 下载线程
     */

    class DownloadThread extends Thread {
        private ThreadInfo mThreadinfo = null;

        public DownloadThread(ThreadInfo mThreadinfo) {
            this.mThreadinfo = mThreadinfo;

        }

        @Override
        public void run() {
            //数据库中插入线程的信息
            if (!mDao.isExists(mThreadinfo.getUrl(), mThreadinfo.getId())) {
                mDao.insertThread(mThreadinfo);
            }
            HttpURLConnection conn=null;
            RandomAccessFile raf=null;
            InputStream input=null;
            try {
                URL url = new URL(mThreadinfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                //设置下载位置
                int start = mThreadinfo.getStart() + mThreadinfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + mThreadinfo.getEnd());
                //设置文件写入位置
                File file = new File(DownloadService.DOWNLOAD_PATH, mFileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                Intent intent = new Intent(DownloadService.ACTION_UPDATE);
                mFinished += mThreadinfo.getFinished();
                //开始下载
                if (conn.getResponseCode() == HttpStatus.SC_PARTIAL_CONTENT) {
                    //读取数据
                    input = conn.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    long time = System.currentTimeMillis();
                    while ((len = input.read(buffer)) != -1) {
                        //写入文件
                        raf.write(buffer, 0, len);
                        //下载进度发送广播给Activity
                        mFinished += len;
                        if (System.currentTimeMillis() - time > 500) {
                            time = System.currentTimeMillis();
                            intent.putExtra("finished", mFinished * 100 / mFileInfo.getLength());
                            mContext.sendBroadcast(intent);
                        }
                        //下载暂停时，保存下载进度
                        if (isPause) {
                            mDao.updateThread(mThreadinfo.getUrl(), mThreadinfo.getId(), mFinished);
                            return;
                        }
                    }

                    //删除线程信息
                    mDao.deleteThread(mThreadinfo.getUrl(), mThreadinfo.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.disconnect();
                    input.close();
                    raf.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
