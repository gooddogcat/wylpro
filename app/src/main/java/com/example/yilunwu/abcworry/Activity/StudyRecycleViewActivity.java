package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.adapter.DemoAdapter;
import com.example.yilunwu.abcworry.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class StudyRecycleViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    private DemoAdapter mAdapter;
    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_recycle_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type=mRecyclerView.getAdapter().getItemViewType(position);
                if (type==DataModel.TYPE_THREE){
                    return gridLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new DemoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        initData();

    }

    private void initData() {
        List<DataModel> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            int type;
            if (i < 5 || (i > 15 && i < 20)) {
                type = 1;
            } else if (i < 10 || i > 26) {
                type = 2;
            } else {
                type = 3;
            }
            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "name:" + type;
            data.content = "content" + i;
            data.contentColor = colors[(type + 1) % 3];
            list.add(data);


        }

        mAdapter.addList(list);
        mAdapter.notifyDataSetChanged();
    }


}
