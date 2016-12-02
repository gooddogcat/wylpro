package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.view.SlideMenuView;

import java.util.ArrayList;

public class Main4Activity extends Activity {

    private ListView mListView;
    private MyAdapter mAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initList();
        mListView = (ListView) findViewById(R.id.listView);

        mAdapter = new MyAdapter(this, list);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initList() {

        list = new ArrayList<>();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        list.add("测试4");
        list.add("测试5");
        list.add("测试6");
        list.add("测试7");
        list.add("测试8");
        list.add("测试9");
        list.add("测试10");

    }

    private class MyAdapter extends BaseAdapter {

        private Context mContext;

        private ArrayList<String> list;

        private LayoutInflater mInflater;

        public MyAdapter(Context mContext, ArrayList<String> list) {

            this.mContext = mContext;
            this.list = list;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;
            if (convertView == null) {
                holder = new Holder();
                convertView = mInflater.inflate(R.layout.item_test, null);
                holder.view = (SlideMenuView) convertView.findViewById(R.id.view);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
                holder.view.setTextName("");
            }

            String item = (String) getItem(position);

            if (item != null && !"".equals(item)) {
                holder.view.setTextName(item);
            }
            return convertView;
        }

        /**
         * Holder模式要解决的是性能问题：

         场景：在我们的Adapter中每次都要从row中通过findViewById来找到子控件，然后设置值。
         如果row的布局比较复杂，或者row的数目特别多。这个查找就要不断发生。从而导致性能问题。

         方案：在row第一次被构建出来的时候，调用findViewById, 通过Holder对象存储起来,
         然后把Holder对象通过row.setTag方法，直接缓存在row上。这样下次就不用在查找了。
         */
        public class Holder {

            private SlideMenuView view;
        }
    }
}
