package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yilunwu.abcworry.Activity.Main2Activity;
import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.bean.RecentChat;
import com.example.yilunwu.abcworry.utils.BaseCallback;
import com.example.yilunwu.abcworry.utils.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;


public class MainActivity extends Activity  {
    private OkHttpHelper mHttpHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mHttpHelper = OkHttpHelper.getinstance();
         mHttpHelper.get(SyncStateContract.Constants.CONTENT_DIRECTORY, new BaseCallback<List<RecentChat>>(){
             @Override
             public void onRequestBefore() {

             }

             @Override
             public void onFailure(Request request, Exception e) {

             }

             @Override
             public void onSuccess(Response response, List<RecentChat> recentChats) {

             }



             @Override
             public void onError(Response response, int errorCode, Exception e) {

             }
         });



        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("data", "DisposeDataListener");
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
