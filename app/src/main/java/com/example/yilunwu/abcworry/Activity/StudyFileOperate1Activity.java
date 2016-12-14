package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudyFileOperate1Activity extends Activity {
    EditText edt;
    Button but;
    TextView contentvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_file_operate1);
        edt = (EditText) findViewById(R.id.editText1);
        but = (Button) findViewById(R.id.write);
        contentvalue = (TextView) findViewById(R.id.contentvalue);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteFiles(edt.getText().toString());
                contentvalue.setText(readFiles());
            }
        });


    }

    //保存文件内容
    public void WriteFiles(String content) {
        try {
            try {
                FileOutputStream fos = openFileOutput("a.txt", MODE_PRIVATE + MODE_APPEND);
                fos.write(content.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //读取文件内容
    public String readFiles() {
        String content=null;
        try {
            FileInputStream fis = openFileInput("a.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while ((len=fis.read(buffer))!=-1){
                baos.write(buffer,0,len);

            }
            content=baos.toString();
            fis.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


}
