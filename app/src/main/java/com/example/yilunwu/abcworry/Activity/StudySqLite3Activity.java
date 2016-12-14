package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.DBOpenHelper;

public class StudySqLite3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_sq_lite3);
        DBOpenHelper helper = new DBOpenHelper(StudySqLite3Activity.this, "stu.db");
        helper.getReadableDatabase();//获取一个只读的数据库 只能查询 不能写入 不能更新
        SQLiteDatabase db = helper.getWritableDatabase();//可读可写
        Cursor c = db.rawQuery("select * from stutb", null);
        if(c!=null){
            String[] cols=c.getColumnNames();
            while (c.moveToNext()){
                for (String ColumnName: cols){
                    Log.i("info",ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
                }
            }
            c.close();
        }
        db.close();

    }


}
