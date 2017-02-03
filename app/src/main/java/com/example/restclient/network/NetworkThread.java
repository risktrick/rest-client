package com.example.restclient.network;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class NetworkThread extends HandlerThread {

    private final int MESSAGE_GET = 1;
    private Handler mHandler;
    private NetworkResultReceiver resultReceiver;
    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == MESSAGE_GET) {
                String request = (String) msg.obj;
                if (request != null) {
                    getRequest(request);
                }
            }
            return false;
        }
    };

    public NetworkThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(callback) ;
    }

    public void makeGetRequest(String request) {
        mHandler.obtainMessage(MESSAGE_GET, request).sendToTarget();
    }

    private void getRequest(String request) {
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.openUrl(request);
        if (resultReceiver != null) {
            resultReceiver.onGettingResults(response);
        }
    }

    public void setResultReceiver(NetworkResultReceiver resultReceiver) {
        this.resultReceiver = resultReceiver;
    }
}
