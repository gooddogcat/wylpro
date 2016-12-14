package com.example.yilunwu.abcworry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.bean.ItemBean;

import java.util.List;

/**
 * Created by yilunwu on 16/12/10.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Context context,List<ItemBean> list) {
        mList=list;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //逗逼式
       /* View view=mInflater.inflate(R.layout.baseadapteritem,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.iv_image);
        TextView title= (TextView) view.findViewById(R.id.tv_title);
        TextView content= (TextView) view.findViewById(R.id.tv_content);
        ItemBean bean=mList.get(position);

        imageView.setImageResource(bean.ItemImageResid);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);
        return view;*/

        //普通式
      /*  if (convertView==null){
            convertView=mInflater.inflate(R.layout.baseadapteritem,null);
        }
        ImageView imageView= (ImageView) convertView.findViewById(R.id.iv_image);
        TextView title= (TextView) convertView.findViewById(R.id.tv_title);
        TextView content= (TextView) convertView.findViewById(R.id.tv_content);
        ItemBean bean=mList.get(position);
        imageView.setImageResource(bean.ItemImageResid);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);
        return convertView;*/


        //文艺式
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.baseadapteritem,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);

        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }
        ItemBean bean=mList.get(position);
        viewHolder.imageView.setImageResource(bean.ItemImageResid);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);
        return convertView;

    }


    class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }



}
