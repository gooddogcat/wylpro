package com.example.yilunwu.abcworry.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yilunwu on 16/12/13.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }


    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override//首次创建数据库的时候调用，一般可以把建库 建表的操作放入
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,name text not null,sex text not null,age integer not null)");
        db.execSQL("insert into stutb(name,sex,age) values('张三','女',18)");

    }

    @Override//当数据库的版本发生变化的时候 会自动执行
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
