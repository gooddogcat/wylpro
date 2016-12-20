package com.example.yilunwu.abcworry.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.model.DataModel;

/**
 * Created by yilunwu on 16/12/20.
 */
public class TypeThreeViewHolder extends TypeAbstractViewHolder {

    public ImageView avatar ;

    public TextView name;

    public TextView content;

    public ImageView contentImage;


    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        contentImage= (ImageView) itemView.findViewById(R.id.contentImage);
        name= (TextView) itemView.findViewById(R.id.name);
        content= (TextView) itemView.findViewById(R.id.content);
    }


    @Override
    public void bindHolder(DataModel model){
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        contentImage.setBackgroundColor(model.avatarColor);
        content.setText(model.content);
    }
}
