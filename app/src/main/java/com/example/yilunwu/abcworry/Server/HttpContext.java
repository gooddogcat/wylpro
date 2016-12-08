package com.example.yilunwu.abcworry.Server;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by yilunwu on 16/12/2.
 */
public class HttpContext {

    private final HashMap<String, String> requestHeaders;

    public HttpContext(){
        requestHeaders=new HashMap<String,String>();
    }
    private Socket underlySocket;

    public void setUnderlySocket(Socket socket) {
        this.underlySocket=socket;
    }

    public Socket getUnderlySocket(){
        return this.underlySocket;
    }

    public void addRequestHeader(String headerName, String headerValue) {
        requestHeaders.put(headerName,headerValue);
    }

    public String getRequestHeaderValue(String headerName){
        return requestHeaders.get(headerName);
    }
}
