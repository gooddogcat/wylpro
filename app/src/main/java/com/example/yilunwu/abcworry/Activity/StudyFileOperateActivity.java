package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

import java.io.File;
import java.io.IOException;

public class StudyFileOperateActivity extends Activity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_file_operate);
/*        File file = new File("/mnt/sdcard/test");//内置sd卡目录
//        new File("/mnt/extsdcard/test");//外置sd卡目录
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(context,"文件已经存在",Toast.LENGTH_SHORT).show();

        }
        file.delete();*/


     /*   File file=this.getFilesDir();//这个目录是当前应用程序默认的数据存储目录
        Log.i("info",file.toString());*/


      /*  File file=this.getCacheDir();//这个目录是当前应用程序默认的缓存文件的存放位置，把一些不重要的文件在此处创建使用，如果手机内存不足，系统会自动去删除app中的Cache目录的数据
        Log.i("info",file.toString());*/

     /*   File file=this.getDir("imooc",MODE_PRIVATE);//自定义目录在/data/data/<包名>/app_imooc
        Log.i("info",file.toString());*/

//        File file=this.getExternalFilesDir();
            File file=this.getExternalCacheDir();//可以得到外置sd卡存储位置 该位置的数据跟内置的使用是一样，如果app卸载了，这里面的数据也会自动清除掉
            Log.i("info",file.toString());

        //如果开发者不遵守这样的规则，不把数据放入data/data/<包名>  /mnt/sdcard/Android/data/<包名>，卸载之后数据将不会自动清除掉，就会造成所谓的数据垃圾




    }


}
