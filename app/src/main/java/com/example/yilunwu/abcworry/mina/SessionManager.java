package com.example.yilunwu.abcworry.mina;

import org.apache.mina.core.session.IoSession;

/**
 * Created by yilunwu on 16/12/15.
 */
public class SessionManager {
    private static SessionManager mInstance = null;
    /**
     * 最终于服务器进行通信的对象
     */

    private IoSession mSession;

    public static SessionManager getInstance() {
        if (mInstance == null) {
            synchronized (SessionManager.class) {
                if (mInstance == null) {
                    mInstance = new SessionManager();
                }
            }
        }
        return mInstance;
    }

    private SessionManager() {

    }

    public void setSession(IoSession session) {
        this.mSession = session;
    }


    public void writeToServer(Object msg) {
        if (mSession != null) {
            mSession.write(msg);
        }
    }

    public void closeSession() {
        if (mSession != null) {
            mSession.close();
        }
    }


    public void removeSession(){
        this.mSession=null;
    }


}
