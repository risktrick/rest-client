package com.example.restclient.bash_screen;

import android.content.Context;
import android.os.Handler;

import com.example.restclient.LoggerConsole;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.network.NetworkResultReceiver;
import com.example.restclient.network.NetworkThread;
import com.example.restclient.network.VolleyHelper;
import com.example.restclient.network.VolleyResultReceiver;

public class BashPresenter implements VolleyResultReceiver {

    private final IBashActivity iBashActivity;
    private final LoggerConsole logger;
    private final VolleyHelper volleyHelper;

    public BashPresenter(IBashActivity iBashActivity) {
        this.iBashActivity = iBashActivity;
        logger = LoggerConsole.getInstance();

        Context context = (Context) iBashActivity;
        volleyHelper = new VolleyHelper(context);
    }

    public void getBashJokes() {
        volleyHelper.requestUsingVolley("http://www.umori.li/api/get?" + "site=bash.im", this);
    }

    /* called in main-UI thread */
    @Override
    public void onGettingResponse(String response) {
        if (response != null) {
            logger.log("response len " + response.length());
            parseResponse(response);
        } else {
            logger.log("response is null");
        }
    }

    /* called in main-UI thread */
    @Override
    public void onGettingError(String response) {
        iBashActivity.showError();
    }

    void parseResponse(String response) {
        final BashModel[] bashModels = new ModelJsonParser().parseBash(response);
        for (BashModel bashModel : bashModels) {
            logger.log(bashModel.toString());
        }
        iBashActivity.addTextViewsToListView(bashModels);
    }

}
