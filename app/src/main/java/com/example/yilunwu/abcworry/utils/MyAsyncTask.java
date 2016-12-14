package com.example.yilunwu.abcworry.utils;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by yilunwu on 16/12/11.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("xys","doInBackground");
        publishProgress();
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d("xys","onPreExecute");

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("xys","onPostExecute");

        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.d("xys","onProgressUpdate");

        super.onProgressUpdate(values);
    }
}
