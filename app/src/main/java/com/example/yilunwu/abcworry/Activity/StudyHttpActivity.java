package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.HttpThread;

public class StudyHttpActivity extends Activity {

    private WebView webView;
    private Handler handler=new Handler();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_http);
        webView= (WebView) findViewById(R.id.webView1);
        imageView= (ImageView) findViewById(R.id.imageView1);

//        new HttpThread("http://www.baidu.com",webView,handler).start();
        new HttpThread("http://www.baidu.com",imageView,handler).start();
    }


}
