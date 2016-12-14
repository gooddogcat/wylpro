package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.model.Person;

public class StudyActivity3 extends Activity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study3);
        button= (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_activity3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //第一种显式启动方式
//        Intent intent=new Intent(StudyActivity3.this,StudyActivity4.class);
//        Intent intent=new Intent();
        //第二种显式启动方式
      /* ComponentName componentName=new ComponentName(StudyActivity3.this,StudyActivity4.class);
        intent.setComponent(componentName);*/

        //匿名启动，一般用于启动系统的Activity
//        intent.setAction("www.imooc.com");

        //启动系统的Activity
        //启动系统的浏览器
      /*  intent.setAction(Intent.ACTION_VIEW);
        Uri uri= Uri.parse("http://www.imooc.com");
        intent.setData(uri);*/
        //启动系统的相册
       /* intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image*//*");*/
        //启动系统的发送短信
      /*  intent.setAction(intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"I am a boy");*/
        //启动系统的电话界面
       /* intent.setAction(Intent.ACTION_VIEW);
        Uri uri=Uri.parse("tel:123456");
        intent.setData(uri);*/
//        startActivity(intent);


        //Activity之间数据交换
        Intent intent=new Intent(StudyActivity3.this,StudyActivity4.class);
      /*  intent.putExtra("name","nate");
        intent.putExtra("age",23);*/

      /*  Bundle bundle=new Bundle();
        bundle.putString("name","nate");
        bundle.putInt("age",23);
        intent.putExtras(bundle);*/

      /*  Person person=new Person(1,"nate","beijing");
        Bundle bundle=new Bundle();
        bundle.putSerializable("person",person);
        intent.putExtras(bundle);*/

        Bundle bundle=new Bundle();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        bundle.putParcelable("bitmap",bitmap);
        intent.putExtras(bundle);


   /*     Bundle bundle=new Bundle();
        int[]data=new int[1024*1024/16];
//        bundle.putIntArray("name",data);
        Bitmap bitmap= Bitmap.createBitmap(480,1200, Bitmap.Config.ARGB_8888);
        bundle.putParcelable("bitmap",bitmap);*/


//        intent.putExtras(bundle);


        startActivity(intent);


    }
}
