package com.example.yilunwu.abcworry.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class Main6Activity extends AppCompatActivity {
    private OkHttpClient okHttpClient=new OkHttpClient();
    public TextView show;
    public Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        show= (TextView) findViewById(R.id.show);
        show.setMovementMethod(ScrollingMovementMethod.getInstance());
        Request request=new Request.Builder().
                url("http://www.baidu.com").
                addHeader("User-Agent","android").
                header("Content-Type", "text/html; charset=utf-8").
                get().
                build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final Headers headers=response
            }
        });

    }


}
