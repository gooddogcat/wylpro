package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.yilunwu.abcworry.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 1、Universal-ImageLoader的配置
 * 2、用Universal-ImageLoader加载网络图片和本地图片
 */

public class Universal_ImageLoaderActivity extends Activity {
    private ImageLoader loader;
    private ImageView iv_mg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal__image_loader);
        loader = ImageLoader.getInstance();
        iv_mg = (ImageView) findViewById(R.id.iv_img);
        //加载本地图片
        String uri="file://"+"本地路径";
//        loader.displayImage(uri, iv_mg);
        //加载网络图片
        String urihttp="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        loader.displayImage(urihttp, iv_mg);
        
        loader.displayImage(urihttp, iv_mg, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                Log.i("info","onLoadingStarted");

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

                Log.i("info","onLoadingFailed");


            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                Log.i("info","onLoadingComplete");

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

                Log.i("info","onLoadingCancelled");

            }
        });



    }


}
