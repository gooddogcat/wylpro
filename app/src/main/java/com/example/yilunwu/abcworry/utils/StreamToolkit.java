package com.example.yilunwu.abcworry.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yilunwu on 16/12/2.
 */
public class StreamToolkit {
    public static final String readLine(InputStream nis) throws IOException {
        StringBuilder sb=new StringBuilder();
        int c1=0;
        int c2=0;
        while (c2!=-1&&!(c1=='\r' && c2=='\n')){
            c1=c2;
            c2=nis.read();
            sb.append((char)c2);
        }
        if (sb.length()==0){
            return null;
        }
        if (sb.length()>2){
            return sb.toString().substring(0,sb.length()-2);
        }
        return sb.toString();
    }


    public static byte[] readRawFromStream(InputStream fis) throws IOException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int nReaded;
        while((nReaded=fis.read(buffer))>0){
            bos.write(buffer,0,nReaded);
        }
        return bos.toByteArray();
    }
}
