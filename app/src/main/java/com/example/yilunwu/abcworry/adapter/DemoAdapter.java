package com.example.yilunwu.abcworry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.model.DataModel;
import com.example.yilunwu.abcworry.viewHolder.TypeAbstractViewHolder;
import com.example.yilunwu.abcworry.viewHolder.TypeOneViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yilunwu on 16/12/20.
 */
public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    private List<DataModel> mList = new ArrayList<>();


    public DemoAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);

    }


    public void addList(List<DataModel> list) {
        mList.addAll(list);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_one, parent, false));
            case DataModel.TYPE_TWO:
                return new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_two, parent, false));
            case DataModel.TYPE_THREE:
                return new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TypeAbstractViewHolder)holder).bindHolder(mList.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }
}
