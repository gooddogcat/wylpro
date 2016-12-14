package com.example.yilunwu.abcworry.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yilunwu on 16/12/11.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPostion;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {

        this.mPostion=position;
        this.mViews=new SparseArray<View>();
        this.mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);

    }



    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else {
           ViewHolder holder= (ViewHolder) convertView.getTag();
            holder.mPostion=position;
            return holder;
        }

    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId,String text){
        TextView tv= getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resId){
        ImageView view=getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId,Bitmap bitmap){
        ImageView view=getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
    public ViewHolder setImageResource(int viewId,String url){
        ImageView view=getView(viewId);
//        ImageLoader.getInstance().loadImg(view,url);

        return this;
    }

    public int getPostion(){
        return mPostion;
    }

}
