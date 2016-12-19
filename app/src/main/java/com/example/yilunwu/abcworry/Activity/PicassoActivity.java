package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.yilunwu.abcworry.R;
import com.squareup.picasso.Picasso;

/**
 * 用picasso加载本地图片和网络图片
 */
public class PicassoActivity extends Activity {
    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        iv_img = (ImageView) findViewById(R.id.id_picasso);
        String urihttp = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        Picasso.with(this).load(urihttp).into(iv_img);
//        Picasso.with(this).load(urihttp).resize(50, 50).into(iv_img);
        Picasso.with(this).load(urihttp).error(R.drawable.ic_launcher).into(iv_img);
    }


}
