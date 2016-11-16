package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yilunwu.abcworry.Activity.Main2Activity;
import com.example.yilunwu.abcworry.R;

public class MainActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("data", "s");
        startActivityForResult(intent, 1);


    }






    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        switch (requestCode) {
            case 1:
                if(resultCode==RESULT_OK){
                    String string=data.getStringExtra("returnData");
                    Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }




}
