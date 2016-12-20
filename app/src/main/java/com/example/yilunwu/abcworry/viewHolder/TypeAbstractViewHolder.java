package com.example.yilunwu.abcworry.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yilunwu.abcworry.model.DataModel;

/**
 * Created by yilunwu on 16/12/20.
 */
public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(DataModel model);
}
