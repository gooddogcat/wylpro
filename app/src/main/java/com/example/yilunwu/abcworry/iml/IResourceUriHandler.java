package com.example.yilunwu.abcworry.iml;

import com.example.yilunwu.abcworry.Server.HttpContext;

import java.io.IOException;

/**
 * Created by yilunwu on 16/12/2.
 */
public interface IResourceUriHandler {
    boolean accept(String uri);

    void handle(String uri,HttpContext httpContext) throws IOException;
}
