package com.example.yilunwu.abcworry.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by yilunwu on 16/12/13.
 */
public class BC2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        System.out.println("receiver2收到消息：" + s);
        abortBroadcast();//截断广播方法
        Bundle bundle=getResultExtras(true);
        String s2= bundle.getString("test");
        System.out.println("得到的数据结果是：" + s2);

    }
}
