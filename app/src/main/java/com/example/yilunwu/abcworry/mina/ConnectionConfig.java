package com.example.yilunwu.abcworry.mina;

import android.content.Context;

/**
 * Created by yilunwu on 16/12/15.
 */

/**
 * 一个构建者模式
 */
public class ConnectionConfig {
    private Context context;
    private String ip;
    private int port;
    private int readBufferSize;
    private long connectionTimeout;

    public Context getContext() {
        return context;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public static class Builder{
        private Context context;
        private String ip="192.68.81.111";
        private int port=9123;
        private int readBufferSize=10240;
        private long connectionTimeout=10000;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }


        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setConnectionTimeout(int timeout) {
            this.connectionTimeout = timeout;
            return this;
        }


        public Builder setReadBufferSize(int size) {
            this.readBufferSize = size;
            return this;
        }

        private void applyConfig(ConnectionConfig config){
            config.context=this.context;
            config.ip=this.ip;
            config.port=this.port;
            config.connectionTimeout=this.connectionTimeout;

        }

        public ConnectionConfig builder(){
            ConnectionConfig config=new ConnectionConfig();
            applyConfig(config);

            return config;
        }
    }
}
