package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Server.SimpleHttpServer;
import com.example.yilunwu.abcworry.Server.WebConfiguration;
import com.example.yilunwu.abcworry.model.ResourceInAssetsHandler;
import com.example.yilunwu.abcworry.model.UploadImageHandler;

import java.io.IOException;

/**
 * 手机服务器微架构设计与实现
 */

public class Main7Activity extends Activity {

    private SimpleHttpServer shs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        WebConfiguration wc = new WebConfiguration();
        wc.setPort(8088);
        wc.setMaxParallels(50);
        shs = new SimpleHttpServer(wc);
        shs.registerResourceHandler(new ResourceInAssetsHandler());
        shs.registerResourceHandler(new UploadImageHandler(){
            @Override
            protected void onImageLoaded(String path) {
                showImage(path);
            }
        });
        shs.startAsync();
    }

    private void showImage(final String path) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                ivImage.setImageBitmap(bitmap);
                Toast.makeText(Main7Activity.this, "image received and show", Toast.LENGTH_SHORT);
            }
        });

    }

    @Override
    protected void onDestroy() {
        try {
            shs.stopAsync();
        }catch (IOException e){
            Log.e("spy",e.toString());
        }
        super.onDestroy();
    }
}
