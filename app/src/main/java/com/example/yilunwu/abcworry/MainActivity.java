package com.example.yilunwu.abcworry;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("data","s");
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
