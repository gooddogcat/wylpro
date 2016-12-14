package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.adapter.CommonAdapter;
import com.example.yilunwu.abcworry.adapter.CommonMyAdapter;
import com.example.yilunwu.abcworry.adapter.MyAdapterWidthCommonViewHoler;
import com.example.yilunwu.abcworry.bean.Bean;
import com.example.yilunwu.abcworry.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StudyCommonAdapterActivity extends Activity  {

    private ListView mListView;
    private List<Bean> mDatas;
    private CommonMyAdapter commonAdapter;

    private MyAdapterWidthCommonViewHoler myAdapterWidthCommonViewHoler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_common_adapter);

        initDatas();

        initView();
    }

    private void initView() {
        mListView= (ListView) findViewById(R.id.id_commonlistView);
//        mListView.setAdapter(commonAdapter);
//        mListView.setAdapter(myAdapterWidthCommonViewHoler);
        mListView.setAdapter(new CommonAdapter<Bean>(StudyCommonAdapterActivity.this,mDatas,R.layout.item_listview) {
            private List<Integer> mPos=new ArrayList<Integer>();
            @Override
            public void convert(final ViewHolder holder, final Bean bean) {
                holder.setText(R.id.id_title, bean.getTitle())
                        .setText(R.id.id_time, bean.getTime())
                        .setText(R.id.id_desc, bean.getDesc())
                        .setText(R.id.id_phone, bean.getPhone());
                final CheckBox cb=holder.getView(R.id.id_cb);
//                cb.setChecked(bean.isCheck());
                cb.setChecked(false);
               if(mPos.contains(holder.getPostion())){
                   cb.setChecked(true);

               }
                   cb.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
//                        bean.setIsCheck(cb.isChecked());
                           if (cb.isChecked()) {
                               mPos.add(holder.getPostion());
                           } else {
                               mPos.remove((Integer) holder.getPostion());
                           }

                       }
                });
            }
        });
    }


    private void initDatas() {
        mDatas=new ArrayList<Bean>();
        Bean bean=new Bean("Androidx新技能Get1","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get2","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get3","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get4","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get5","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get6","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get7","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get8","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);

        bean=new Bean("Androidx新技能Get9","Android打造万能的ListView和GridView适配器","2016-12-10","10086");
        mDatas.add(bean);


//        commonAdapter=new CommonMyAdapter(this,mDatas);
        myAdapterWidthCommonViewHoler=new MyAdapterWidthCommonViewHoler(this,mDatas);
        
    }


}
