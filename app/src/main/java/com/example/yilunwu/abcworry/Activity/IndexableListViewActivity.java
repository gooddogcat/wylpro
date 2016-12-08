package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.StringMacher;
import com.example.yilunwu.abcworry.widget.IndexableListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Android高级特效－索引
 */

public class IndexableListViewActivity extends Activity {

    private ArrayList<String> mItems;
    private IndexableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexable_list_view);
        /**
         * 1.初始化items
         * 2.根据section获取position
         */
        mItems=new ArrayList<String>();
        mItems.add("12345");
        mItems.add("A a");
        mItems.add("B b");
        mItems.add("C c");
        mItems.add("D d");
        mItems.add("E e");
        mItems.add("F f");
        mItems.add("G g");
        mItems.add("H h");
        mItems.add("I i");
        mItems.add("J j");
        mItems.add("K d");
        mItems.add("L l");
        mItems.add("M m");
        mItems.add("N n");
        mItems.add("O o");
        mItems.add("P p");
        mItems.add("Q q");
        mItems.add("R r");
        mItems.add("S s");
        mItems.add("T t");
        mItems.add("U u");
        mItems.add("V v");
        mItems.add("W w");
        mItems.add("X x");
        mItems.add("Y y");
        mItems.add("Z z");

        //排序
        Collections.sort(mItems);

        //可以直接使用ArrayAdapter
        // SectionIndexer
        ContentAdapter adapter=new ContentAdapter(this,android.R.layout.simple_list_item_1,mItems);
        mListView=(IndexableListView)findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);

    }

    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer{
        private String mSections="#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public ContentAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public Object[] getSections() {
            String[] sections=new String[mSections.length()];
            //将每个section作为单个数组元素放到sections中
            for (int i=0;i<mSections.length();i++){
                //从Sections中获得每一个字符
                sections[i]=String.valueOf(mSections.charAt(i));
            }
            return sections;
        }

        @Override
        public int getPositionForSection(int sectionIndex) {
            // 从当前的Section往前查，直到遇到第一个对应的item的为止，否则不进行定位
            for (int i=sectionIndex;i>=0;i--){
                for (int j=0;j<getCount();j++){
                    //查询数字
                    if (i==0){
                        for (int k=0;k<=9;k++){
                            //value：item的首字母
                            if(StringMacher.match(String.valueOf(String.valueOf(getItem(j).charAt(0))),String.valueOf(k))){
                                return j;
                            }
                        }
                    }else {
                        //查询字母
                        if(StringMacher.match(String.valueOf(getItem(j).charAt(0)),String.valueOf(mSections.charAt(i)))){
                            return j;
                        }
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }
    }


}
