package com.example.yilunwu.abcworry.utils;

import com.example.yilunwu.abcworry.iml.DisposeDataListener;

/**
 * Created by yilunwu on 16/12/15.
 */
public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener Listener) {
        mListener = Listener;
    }

    public DisposeDataHandle(DisposeDataListener Listener, Class<?> Class) {
        mListener = Listener;
        mClass = Class;
    }
}
