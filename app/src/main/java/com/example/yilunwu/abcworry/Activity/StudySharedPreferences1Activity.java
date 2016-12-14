package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yilunwu.abcworry.R;

public class StudySharedPreferences1Activity extends Activity {
    EditText etUserName,etUserPass;
    CheckBox chk;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_shared_preferences1);

//        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(StudySharedPreferences1Activity.this);
   /*      SharedPreferences pref=getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("name","张三");
        editor.putInt("age", 30);
        editor.putLong("time", System.currentTimeMillis());
        editor.putBoolean("default", true);
        editor.commit();
        editor.remove("default");
        editor.commit();
        System.out.println(pref.getString("name",""));
        System.out.println(pref.getInt("age",0));*/

        etUserName= (EditText) findViewById(R.id.etuserName);
        etUserPass= (EditText) findViewById(R.id.etPassword);
        chk= (CheckBox) findViewById(R.id.chkSaveName);
        pref=getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor=pref.edit();
        String name=pref.getString("userName","");
        if (name==null){
            chk.setChecked(false);
        }else {
            chk.setChecked(true);
            etUserName.setText(name);
        }

    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                String name=etUserName.getText().toString().trim();
                String pass=etUserPass.getText().toString().trim();
                if ("admin".equals(name)&&"123456".equals(pass)){
                    if (chk.isChecked()){
                        editor.putString("userName", name);
                        editor.commit();
                    }else {
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(StudySharedPreferences1Activity.this,"登陆成功",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(StudySharedPreferences1Activity.this,"禁止登陆",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancel:
                break;
            default:
                break;
        }
    }


}
