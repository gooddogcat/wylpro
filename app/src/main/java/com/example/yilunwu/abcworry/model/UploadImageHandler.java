package com.example.yilunwu.abcworry.model;

import com.example.yilunwu.abcworry.Server.HttpContext;
import com.example.yilunwu.abcworry.iml.IResourceUriHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by yilunwu on 16/12/2.
 */
public class UploadImageHandler implements IResourceUriHandler {

    private String acceptPrefix="/upload_image/";

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handle(String uri, HttpContext httpContext) throws IOException {
       /* OutputStream nos = httpContext.getUnderlySocket().getOutputStream();
        PrintWriter writer = new PrintWriter(nos);
        writer.println("HTTP/1.1 200 OK");
        writer.println();
        writer.println("from upload image  handler");
        writer.flush();*/
        String tmpPath="/mnt/sdcard/test_upload.jpg";
        long totalLength = Long.parseLong(httpContext.getRequestHeaderValue("Content-Length"));
        FileOutputStream fos = new FileOutputStream(tmpPath);
        InputStream nis = httpContext.getUnderlySocket().getInputStream();
        byte[]buffer=new byte[10240];
        int nReaded=0;
        long nLeftLength=totalLength;
        while ((nReaded=nis.read(buffer)) > 0 && nLeftLength > 0){
            fos.write(buffer,0,nReaded);
            nLeftLength-=nReaded;
        }
        fos.close();

        OutputStream nos=httpContext.getUnderlySocket().getOutputStream();
        PrintStream printer = new PrintStream(nos);
        printer.println("HTTP/1.1 200 OK");
        printer.println();

        onImageLoaded(tmpPath);

    }

    protected void onImageLoaded(String path){

    }
}
