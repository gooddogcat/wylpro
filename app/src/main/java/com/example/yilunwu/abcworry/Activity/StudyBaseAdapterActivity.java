package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.adapter.MyAdapter;
import com.example.yilunwu.abcworry.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class StudyBaseAdapterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_base_adapter);
        List<ItemBean> itemBeanList=new ArrayList<>();
        for (int i=0;i<20;i++) {
            itemBeanList.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "我是标题"+i,
                    "我是内容"+i

            ));
        }
        ListView listView= (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new MyAdapter(this,itemBeanList));

    }


}
