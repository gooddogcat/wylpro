package com.example.yilunwu.abcworry.utils;

import android.util.Log;

/**
 * Created by yilunwu on 16/12/19.
 */
public class L {

    private static final String TAG="Imooc_okhttp";
    private static boolean debug=true;

    public static void e(String msg){
        if (debug)
            Log.e(TAG,msg);
    }
}
