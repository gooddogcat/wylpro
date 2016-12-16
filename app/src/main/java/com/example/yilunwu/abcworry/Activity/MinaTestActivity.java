package com.example.yilunwu.abcworry.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.Service.MinaService;
import com.example.yilunwu.abcworry.mina.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinaTestActivity extends Activity implements OnClickListener {


    private TextView mConnectView;
    private TextView mSendView;

    //自定义了一个广播接收器
    private MessageBroadcastReceiver receiver=new MessageBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina_test);
        initView();
        registerBroadcast();

    }

    private void registerBroadcast() {
        IntentFilter filter=new IntentFilter("com.commonlibrary.mina.broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void initView() {
        mConnectView= (TextView) findViewById(R.id.start_service_view);
        mSendView= (TextView) findViewById(R.id.send_view);
        mConnectView.setOnClickListener(this);
        mSendView.setOnClickListener(this);
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MinaService.class));
        unregisterBroadcast();
    }

    private void unregisterBroadcast() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_view:
                SessionManager.getInstance().writeToServer("123");
                break;
            case R.id.start_service_view:
                Intent intent=new Intent(this,MinaService.class);
                startService(intent);
                break;
        }

    }

    /**
     * 接收mina发送来的消息，并更新UI
     */
    private class MessageBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            setTitle(intent.getStringExtra("message"));
        }
    }
}
