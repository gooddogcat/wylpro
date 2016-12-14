package com.example.yilunwu.abcworry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.bean.Bean;

import java.util.List;

/**
 * Created by yilunwu on 16/12/10.
 */
public class CommonMyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Bean> mDatas;

    public CommonMyAdapter(Context context, List<Bean> datas) {
        mInflater=LayoutInflater.from(context);
        mDatas=datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_listview,null);
            viewHolder=new ViewHolder();

            viewHolder.mTitle= (TextView) convertView.findViewById(R.id.id_title);
            viewHolder.mDesc= (TextView) convertView.findViewById(R.id.id_desc);
            viewHolder.mTime= (TextView) convertView.findViewById(R.id.id_time);
            viewHolder.mPhone= (TextView) convertView.findViewById(R.id.id_phone);


            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();

        }

        Bean bean = mDatas.get(position);

        viewHolder.mTitle.setText(bean.getTitle());
        viewHolder.mDesc.setText(bean.getDesc());
        viewHolder.mTime.setText(bean.getTime());
        viewHolder.mPhone.setText(bean.getPhone());

        return convertView;
    }

    private class ViewHolder{
        TextView mTitle;
        TextView mDesc;
        TextView mTime;
        TextView mPhone;
    }
}
