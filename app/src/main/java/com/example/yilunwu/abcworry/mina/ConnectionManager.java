package com.example.yilunwu.abcworry.mina;

/**
 * Created by yilunwu on 16/12/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

/**
 * function:封装好connection disconnection方法供外层调用
 */
public class ConnectionManager {
    public static final String BROADCAST_ACTION="com.commonlibrary.mina";
    public static final String MESSAGE="message";
    private ConnectionConfig mConfig;
    private WeakReference<Context> mContext;
    private NioSocketConnector mConnection;
    private IoSession mSession;
    private InetSocketAddress mAddress;


    public ConnectionManager(ConnectionConfig config) {
        this.mConfig = config;
        this.mContext = new WeakReference<Context>(config.getContext());
        init();

    }

    private void init() {
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnection = new NioSocketConnector();
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
        mConnection.getFilterChain().addLast("logging", new LoggingFilter());
        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        mConnection.setHandler(new DefaultHandler(mContext.get()));
    }

    /**
     * 外层调用取得与服务器的连接
     * @return
     */
    public boolean connect() {
        try {
            ConnectFuture future = mConnection.connect();
            future.awaitUninterruptibly();
            mSession = future.getSession();
        } catch (Exception e) {
            return false;
        }
        return mSession == null ? false : true;
    }

    /**
     * 断开连接的方法
     */
    public void disConnection(){

        mConnection.dispose();
        mConnection=null;
        mSession=null;
        mAddress=null;
        mContext=null;
    }
    private static class DefaultHandler extends IoHandlerAdapter {
        private Context mContext;

        DefaultHandler(Context context) {
            this.mContext = context;
        }


        @Override
        public void sessionOpened(IoSession session) throws Exception {

            //将我们的session保存到我们的session manager类中，从而可以发送消息到服务器
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {

            if (mContext!=null){
                Intent intent=new Intent(BROADCAST_ACTION);
                intent.putExtra(MESSAGE,message.toString());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);//局部广播

            }
        }
    }
}
