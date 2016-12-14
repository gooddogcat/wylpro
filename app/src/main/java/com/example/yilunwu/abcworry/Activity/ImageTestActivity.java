package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.yilunwu.abcworry.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class ImageTestActivity extends Activity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private static String URL="http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        mImageView= (ImageView) findViewById(R.id.image);
        mProgressBar= (ProgressBar) findViewById(R.id.progressbar);
        //设置传递进去的参数
        new MyAsyncTask().execute(URL);

    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传递进来的参数
            String url=params[0];
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;

            try {
                connection=new URL(url).openConnection();
                is=connection.getInputStream();
                BufferedInputStream bis=new BufferedInputStream(is);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //通过decodeStream方法解析输出流
                bitmap= BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将Bitmap作为返回值
            return bitmap;
        }
    }


}
