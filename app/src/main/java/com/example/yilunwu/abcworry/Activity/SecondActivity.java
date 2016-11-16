package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.view.SwipeBackLayout;

public class SecondActivity extends Activity implements SwipeBackLayout.SwipeBackListener {

    private static final String[] CITYS = new String[]{

            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",
            "上海","北京","广州","深圳",

    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);
    /*    if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        SwipeBackLayout mSwipeBackLayout = (SwipeBackLayout) findViewById(R.id.mSwipeBackLayout);
        ListView lv_city = (ListView) findViewById(R.id.lv_city);
        if(lv_city != null){
            lv_city.setAdapter(new CityAdapter());
        }

        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setSwipeBackListener(this);
        }
    }

    @Override
    public void onSwipeBackFinish() {
        finish();
        overridePendingTransition(0, 0);
    }

    class CityAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return CITYS.length;
        }

        @Override
        public Object getItem(int position) {
            return CITYS[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(SecondActivity.this).inflate(R.layout.item,parent,false);
            }
            TextView tv_city = (TextView) convertView.findViewById(R.id.tv_city);
            tv_city.setText((String)getItem(position));
            return convertView;
        }
    }
}


