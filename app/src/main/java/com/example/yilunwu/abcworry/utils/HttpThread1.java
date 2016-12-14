package com.example.yilunwu.abcworry.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yilunwu on 16/12/12.
 */
public class HttpThread1 extends Thread {
    String url;
    String name;
    String age;

    public HttpThread1(String url,String name,String age){
        this.url=url;
        this.name=name;
        this.age=age;
    }

    private void doGet(){
        url=url+"?name="+name+"&age="+age;
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb=new StringBuffer();
            while ((str=reader.readLine())!=null){
                sb.append(str);
            }

            System.out.println("result:"+sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doPost(){
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) httpUrl.openConnection();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        super.run();
    }
}
