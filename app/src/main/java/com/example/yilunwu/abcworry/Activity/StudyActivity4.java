package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.model.Person;

import org.w3c.dom.Text;

public class StudyActivity4 extends Activity {

    private TextView textview;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study4);
        textview= (TextView) findViewById(R.id.textview4);
        imageview= (ImageView) findViewById(R.id.imageView);
        Intent intent=getIntent();
        if (intent!=null){
            /*Person person= (Person) intent.getSerializableExtra("person");
            textview.setText(person.toString());*/

            /*String name=intent.getStringExtra("name");
            int age=intent.getIntExtra("age",0);
            textview.setText("name="+name+"age="+age);*/

          /*  Bitmap bitmap=intent.getParcelableExtra("bitmap");
            imageview.setImageBitmap(bitmap);*/

           /* int[]data=intent.getIntArrayExtra("data");
            Log.i("nate","data="+name);*/


            Bitmap bitmap=intent.getParcelableExtra("bitmap");
            imageview.setImageBitmap(bitmap);






        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_activity4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
