package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewFlipper;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.view.MyWebView;

public class TestWebViewFlipActivity extends Activity {
    private WebView webview;
    private ViewFlipper flipper;
    MyWebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view_flip);
        flipper= (ViewFlipper) this.findViewById(R.id.ViewFlipper);
        flipper.addView(addWebView("http://www.baidu.com"));
        flipper.addView(addWebView("http://www.sina.com.cn"));


    }
    //往flipper中添加WebView
    private View addWebView(String url) {
        myWebView = new MyWebView(this, flipper);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return myWebView;


    }















   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        int endX = (int) event.getX();
                        if (endX > startX && webview.canGoBack() && endX - startX > scrollSize) {
                            webview.goBack();
                        } else if (endX < startX && webview.canGoForward() && startX - endX > scrollSize) {
                            webview.goForward();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        return super.dispatchTouchEvent(ev);
    }*/
}
