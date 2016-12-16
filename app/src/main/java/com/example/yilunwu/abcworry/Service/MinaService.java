package com.example.yilunwu.abcworry.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;

import com.example.yilunwu.abcworry.mina.ConnectionConfig;
import com.example.yilunwu.abcworry.mina.ConnectionManager;

public class MinaService extends Service {

    private ConnectionThread thread;

    public MinaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        thread=new ConnectionThread("mina",getApplicationContext());
        thread.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.disConnection();
        thread=null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 负责调用connection manager类来完成与服务器的连接
     */

    class ConnectionThread extends HandlerThread {
        private Context context;
        boolean isConnection;
        ConnectionManager mManager;

        ConnectionThread(String name, Context context) {
            super(name);
            this.context = context;
            ConnectionConfig config = new ConnectionConfig.Builder(context)
                    .setIp("192.68.81.111")
                    .setPort(9123)
                    .setReadBufferSize(10240)
                    .setConnectionTimeout(10000).builder();
        }

        /**
         * 开始连接我们的服务器
         */
        @Override
        protected void onLooperPrepared() {
         for (;;){

             isConnection=mManager.connect();//完成服务器的连接
             if (isConnection){
                 break;
             }

             try {
                 Thread.sleep(3000);
             } catch (Exception e) {
                 e.printStackTrace();
             }

         }
        }

        public void disConnection(){
            mManager.disConnection();//完成与服务器的断开操作
        }
    }
}
