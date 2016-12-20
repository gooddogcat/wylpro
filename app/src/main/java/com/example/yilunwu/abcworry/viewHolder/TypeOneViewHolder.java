package com.example.yilunwu.abcworry.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.model.DataModel;

/**
 * Created by yilunwu on 16/12/20.
 */
public class TypeOneViewHolder extends TypeAbstractViewHolder {

    public ImageView avatar ;

    public TextView name;


    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        name= (TextView) itemView.findViewById(R.id.name);
    }

    @Override
    public void bindHolder(DataModel model){
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
    }
}
