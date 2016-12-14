package com.example.yilunwu.abcworry.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yilunwu on 16/12/13.
 */
public class BC1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        System.out.println("receiver1收到消息："+s);
        Bundle bundle = new Bundle();
        bundle.putString("test","广播处理的数据");
        setResultExtras(bundle);
    }
}
