package com.example.yilunwu.abcworry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends Activity implements SlideSwitch.OnChangedListener {

    private  SlideSwitch slideButton;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sp=getSharedPreferences("flag", 0);
        editor=sp.edit();
        slideButton=(SlideSwitch)findViewById(R.id.slide_button);
        slideButton.SetOnChangedListener(this);
        //根据条件显示slidebutton状态
        if(sp.getBoolean("isChecked", false)){
            slideButton.setChecked(true);
        }else{
            slideButton.setChecked(false);


        }
        btn= (Button) findViewById(R.id.btn);





        Intent intent=getIntent();
        String string=intent.getStringExtra("data");

        btn.setText(string);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                returnData();
            }
        });



    }

    private void returnData() {
        Intent intent=new Intent();
        intent.putExtra("returnData", "from MainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBackPressed() {
// TODO Auto-generated method stub
        returnData();
    }


    @Override
    public void OnChanged(boolean checkState) {
        // TODO Auto-generated method stub
        if(checkState){
            editor.putBoolean("isChecked", true);
        }else{
            editor.putBoolean("isChecked", false);
        }
        editor.commit();
    }




}

