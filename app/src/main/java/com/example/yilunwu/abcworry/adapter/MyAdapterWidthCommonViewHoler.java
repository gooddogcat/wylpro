package com.example.yilunwu.abcworry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.bean.Bean;
import com.example.yilunwu.abcworry.utils.ViewHolder;

import java.util.List;

/**
 * Created by yilunwu on 16/12/10.
 */
public class MyAdapterWidthCommonViewHoler extends CommonAdapter<Bean> {

    public MyAdapterWidthCommonViewHoler(Context context, List<Bean> datas) {
        super(context, datas,R.layout.item_listview);
    }


    @Override
    public void convert(ViewHolder holder, Bean bean) {

        holder.setText(R.id.id_title, bean.getTitle())
                .setText(R.id.id_time, bean.getTime())
                .setText(R.id.id_desc, bean.getDesc())
                .setText(R.id.id_phone, bean.getPhone());




/*        TextView desc=holder.getView(R.id.id_desc);
        desc.setText(bean.getDesc());
         TextView title = holder.getView(R.id.id_title);
        title.setText(bean.getTitle());*
        ((TextView)holder.getView(R.id.id_time)).setText(bean.getTime());
        ((TextView)holder.getView(R.id.id_phone)).setText(bean.getPhone());*/

    }


}
