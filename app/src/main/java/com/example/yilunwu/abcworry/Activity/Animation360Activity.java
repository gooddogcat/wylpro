package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Service.MyFloatService;

public class Animation360Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation360);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyFloatService.class);
        startService(intent);
        finish();
    }
}
