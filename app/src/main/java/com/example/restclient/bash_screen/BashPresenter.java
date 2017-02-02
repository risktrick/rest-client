package com.example.restclient.bash_screen;

import android.os.Handler;

import com.example.restclient.LoggerConsole;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.network.NetworkResultReceiver;
import com.example.restclient.network.NetworkThread;

public class BashPresenter implements NetworkResultReceiver {

    private final IBashActivity iBashActivity;
    private final Handler handlerResponse;
    private final LoggerConsole logger;
    private final NetworkThread networkThread;

    public BashPresenter(IBashActivity iBashActivity, Handler handlerResponse) {
        this.iBashActivity = iBashActivity;
        this.handlerResponse = handlerResponse;

        logger = LoggerConsole.getInstance();

        networkThread = new NetworkThread("bash_thread");
        networkThread.setResultReceiver(this);
        networkThread.start();
        networkThread.getLooper();
    }

    public void getBashJokes() {
        networkThread.makeGetRequest("http://www.umori.li/api/get?" + "site=bash.im");
    }

    @Override
    public void onGettingResults(String response) {
        logger.log("response len " + response.length());
        if (response != null) {
            parseResponse(response);
        } else {
            logger.log("response is null");
        }
    }

    void parseResponse(String response) {
        final BashModel[] bashModels = new ModelJsonParser().parseBash(response);
        for (BashModel bashModel : bashModels) {
            logger.log(bashModel.toString());
        }

        handlerResponse.post(new Runnable() {
            @Override
            public void run() {
                iBashActivity.addTextViewsToListView(bashModels);
            }
        });
    }
}
