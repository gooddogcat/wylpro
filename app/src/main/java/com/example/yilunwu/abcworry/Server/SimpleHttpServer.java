package com.example.yilunwu.abcworry.Server;

import android.util.Log;

import com.example.yilunwu.abcworry.iml.IResourceUriHandler;
import com.example.yilunwu.abcworry.utils.StreamToolkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yilunwu on 16/12/2.
 */
public class SimpleHttpServer {
    private final WebConfiguration webConfig;
    private final ExecutorService threadPool;
    private boolean isEnable;
    private ServerSocket socket;
    private Set<IResourceUriHandler> resourceHandlers;

    public SimpleHttpServer(WebConfiguration webConfig){
        this.webConfig=webConfig;
        threadPool=Executors.newCachedThreadPool();
        resourceHandlers=new HashSet<IResourceUriHandler>();
    }
    /**
     * 启动Server(异步)
     */
    public void startAsync(){
        isEnable=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcSync();
            }

            
        }).start();
    }

    /**
     * 停止Server(异步)
     */
    public void stopAsync() throws IOException {
        if (!isEnable){
            return;
        }
        isEnable=false;
        socket.close();
        socket=null;
    }

    private void doProcSync() {
        try {
            InetSocketAddress socketAddr = new InetSocketAddress(webConfig.getPort());
            socket=new ServerSocket();
            socket.bind(socketAddr);
            while (isEnable){
                final Socket remotePeer = socket.accept();
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("spy","a remote peer accepted……"+remotePeer.getRemoteSocketAddress().toString());
                        onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            Log.e("spy",e.toString());
        }
    }

    public void registerResourceHandler(IResourceUriHandler handler){
        resourceHandlers.add(handler);
    }

    private void onAcceptRemotePeer(Socket remotePeer) {
        try {
            HttpContext httpContext = new HttpContext();
            httpContext.setUnderlySocket(remotePeer);
            remotePeer.getOutputStream().write("congratulations,conneted successful".getBytes());
            InputStream nis = remotePeer.getInputStream();
            String headerLine=null;
            String resourceUri = headerLine = StreamToolkit.readLine(nis).split(" ")[1];
            Log.d("spy",resourceUri);
            while ((headerLine= StreamToolkit.readLine(nis))!=null){
                if (headerLine.equals("\r\n")){
                    break;
                }
                String[] pair = headerLine.split(": ");
                httpContext.addRequestHeader(pair[0],pair[1]);
                Log.d("spy","header line= "+headerLine);
            }
            for (IResourceUriHandler handler:resourceHandlers){
                if (!handler.accept(resourceUri)){
                    continue;
                }
                handler.handle(resourceUri,httpContext);
            }
        } catch (IOException e) {
            Log.e("spy",e.toString());
        }finally {
            try {
                remotePeer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
