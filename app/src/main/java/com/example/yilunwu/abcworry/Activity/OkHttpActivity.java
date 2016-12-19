package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.CountingRequestBody;
import com.example.yilunwu.abcworry.utils.L;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;


public class OkHttpActivity extends Activity {
    OkHttpClient okHttpClient = new OkHttpClient();
    private TextView mTvResult;
    private String mBaseUrl = "http://192.68.81.111:8080/Imooc_okhttp/";
    private ImageView mIvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        mTvResult = (TextView) findViewById(R.id.id_tv_result);
        mIvResult= (ImageView) findViewById(R.id.id_iv_result);
    }

    /**
     *         //1.拿到okHttpClient对象
     *         //2.构造Request
     *         //2.1构造requestBody
     *         //2.2包装requestBody
     *         //3.Call->execute

     * @param view
     */
    public void doPost(View view) {
        //1.拿到okHttpClient对象
        //2.构造Request
        //3.1构造requestBody
        FormEncodingBuilder requestBodyBuilder = new FormEncodingBuilder();
        RequestBody requestBody = requestBodyBuilder.add("username", "hyman").add("password", "123").build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "login").post(requestBody).build();
        //3 4
        executeRequest(request);

    }

    public void doPostString(View view) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;chaset=utf-8"), "{username:hyman,password:123}");
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postString").post(requestBody).build();
        //3 4
        executeRequest(request);
    }

    public void doPostFile(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "banner2.jpg");
        if (!file.exists()) {
            L.e(file.getAbsolutePath() + "not exist!");
            return;
        }

        // mime type
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postFile").post(requestBody).build();
        //3 4
        executeRequest(request);
    }

    public void doUpload(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "banner2.jpg");
        if (!file.exists()) {
            L.e(file.getAbsolutePath() + "not exist!");
            return;
        }

        MultipartBuilder multipartBuilder = new MultipartBuilder();

        RequestBody requestBody = multipartBuilder.type(MultipartBuilder.FORM)
                .addFormDataPart("username", "hyman")
                .addFormDataPart("password", "123")
                .addFormDataPart("mPhoto", "hyman.jpg", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        CountingRequestBody countingRequestBody=new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(long byteWrited, long contentLength) {
                L.e(byteWrited+"/"+contentLength);
            }
        });

        // mime type
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "uploadInfo").post(countingRequestBody).build();
        //3 4
        executeRequest(request);
    }


    public void doDownload(View view){
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url(mBaseUrl + "files/banner2.jpg")//
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                L.e("onResponse:");
                final long total = response.body().contentLength();
                long sum=0L;

                InputStream is = response.body().byteStream();
                int len = 0;
                File file = new File(Environment.getExternalStorageDirectory(), "banner12306.jpg");
                byte[] buf = new byte[128];
                FileOutputStream fos = new FileOutputStream(file);

                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    sum+=len;
                    L.e(sum+"/"+total);
                    final long finalSum = sum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvResult.setText(finalSum +"/"+total);
                        }
                    });
                }
                fos.flush();
                fos.close();
                is.close();

                L.e("download success!");

            }
        });


    }


    public void doDownloadImg(View view){
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url(mBaseUrl + "files/banner2.jpg")//
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                L.e("onResponse:");
                InputStream is = response.body().byteStream();

//                BitmapFactory.Options
                final Bitmap bitmap = BitmapFactory.decodeStream(is);

//                is.mark();
//                is.reset();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mIvResult.setImageBitmap(bitmap);
                    }
                });

            }
        });


    }

    public void doGet(View view) throws IOException {

        //1.拿到okHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();
        //2.构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url(mBaseUrl + "login?username=hyman&password=1234")//
                .build();
        executeRequest(request);


    }

    private void executeRequest(Request request) {
        //3.将Request封装为Call
        Call call = okHttpClient.newCall(request);

//        Response response = call.execute();
        //4.执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                L.e("onResponse:");
                final String res = response.body().string();
                L.e(res);
//                InputStream is = response.body().byteStream();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvResult.setText(res);

                    }
                });

            }
        });
    }


}
