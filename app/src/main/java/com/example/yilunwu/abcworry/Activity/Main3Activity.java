package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yilunwu.abcworry.R;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       /* if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        Button btn = (Button) findViewById(R.id.btn);
        if(btn !=null){
            btn.setOnClickListener(listener);
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Main3Activity.this, SecondActivity.class));
        }
    };


}
