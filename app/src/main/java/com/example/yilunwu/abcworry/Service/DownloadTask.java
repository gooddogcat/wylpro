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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载任务类
 */
public class DownloadTask {
    private Context mContext = null;
    private FileInfo mFileInfo = null;
    private ThreadDAO mDao = null;
    private int mFinished = 0;
    public boolean isPause = false;
    private int mThreadCount = 1;//线程数量
    private List<DownloadThread> mThreadList=null;//线程集合
    public static ExecutorService sExecutorService= Executors.newCachedThreadPool();//线程池

    public DownloadTask(Context context, FileInfo mFileInfo, int mThreadCount) {
        this.mContext = context;
        this.mFileInfo = mFileInfo;
        this.mThreadCount = mThreadCount;
        mDao = new ThreadDAOImpl(mContext);

    }

    public void download() {
        //读取数据库的线程信息,从数据库获取下载进度
        List<ThreadInfo> threads = mDao.getThreads(mFileInfo.getUrl());
        if (threads.size() == 0) {
            //获取每个线程下载长度
            int length = mFileInfo.getLength() / mThreadCount;
            for (int i = 0; i < mThreadCount; i++) {
                ThreadInfo threadInfo = new ThreadInfo(i, mFileInfo.getUrl(),
                        length * i, (i + 1) * length - 1, 0);
                if (i == mThreadCount - 1) {
                    threadInfo.setEnd(mFileInfo.getLength());
                }
                //添加到线程信息集合中
                threads.add(threadInfo);
                //数据库中插入线程的信息
                mDao.insertThread(threadInfo);


            }
        }
        mThreadList=new ArrayList<DownloadThread>();

        //启动多个线程进行下载
        for (ThreadInfo info:threads){
            DownloadThread thread=new DownloadThread(info);
//            thread.start();
            DownloadTask.sExecutorService.execute(thread);
            //添加线程到线程集合
            mThreadList.add(thread);

        }

    }

    /**
     * 判断是否所有线程都执行完毕
     */
    private synchronized void checkAllThreadsFinished(){
        boolean allFinished=true;
        //遍历线程集合，判断线程是否都执行完毕
        for (DownloadThread thread:mThreadList){
            if (thread.isFinished){
                allFinished=false;
                break;
            }
        }
        if (allFinished){
            //删除线程信息
            mDao.deleteThread(mFileInfo.getUrl());
            //发送广播通知UI下载任务结束
            Intent intent=new Intent(DownloadService.ACTION_FINISH);
            intent.putExtra("fileInfo",mFileInfo);
            mContext.sendBroadcast(intent);

        }
    }




    /**
     * 下载线程
     */

    class DownloadThread extends Thread {
        private ThreadInfo mThreadinfo = null;
        public boolean isFinished=false;//线程是否执行完毕

        public DownloadThread(ThreadInfo mThreadinfo) {
            this.mThreadinfo = mThreadinfo;

        }

        @Override
        public void run() {
            //打开连接
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            InputStream input = null;
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
                        //累加整个文件完成进度
                        mFinished += len;
                        //累加每个线程完成的进度
                        mThreadinfo.setFinished(mThreadinfo.getFinished()+len);
                        //间隔500毫秒更新一次进度
                        if (System.currentTimeMillis() - time > 1000) {
                            time = System.currentTimeMillis();
                            //发送进度到Activity
                            intent.putExtra("finished", mFinished * 100 / mFileInfo.getLength());
                            intent.putExtra("id", mFileInfo.getId());
                            mContext.sendBroadcast(intent);
                        }
                        //下载暂停时，保存下载进度
                        if (isPause) {
                            //保存进度到数据
                            mDao.updateThread(mThreadinfo.getUrl(), mThreadinfo.getId(), mThreadinfo.getFinished());
                            return;
                        }
                    }
                    //标识线程执行完毕
                    isFinished=true;
                    //检查下载任务是否执行完毕
                    checkAllThreadsFinished();
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
