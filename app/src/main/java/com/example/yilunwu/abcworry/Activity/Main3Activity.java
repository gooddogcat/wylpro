package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 侧滑返回
 */
public class Main3Activity extends Activity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.mTv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

       /* if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        Button btn = (Button) findViewById(R.id.btn);
        if (btn != null) {
            btn.setOnClickListener(listener);
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            startActivity(new Intent(Main3Activity.this, SecondActivity.class));
            //Http Get
            //创建okHttpClient对象
            OkHttpClient mOkHttpClient = new OkHttpClient();
            //创建一个Request对象
            final Request request = new Request.Builder().url("https://github.com/hongyangAndroid").build();
            //new call
            Call call = mOkHttpClient.newCall(request);
            //请求加入调度
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

               /* @Override
                public void onResponse(Response response) throws IOException {
                    String htmlStr = response.body().string();//获得返回的字符串
                    byte[] htmlStr2 = response.body().bytes();//获得返回的二进制字节数组
                    InputStream htmlStr3 = response.body().byteStream();//获得返回的文件流，支持大文件下载，有inputStream我们就可以通过IO的方式写文件


                }*/

                @Override
                public void onResponse(final Response response) throws IOException {
                    final String res = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(res);
                        }

                    });
                }


            });






        }
    };


}
