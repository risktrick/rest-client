package com.example.restclient.bash_screen;

import android.content.Context;

import com.example.restclient.MainApplication;
import com.example.restclient.utils.ILogger;
import com.example.restclient.utils.LoggerConsole;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.network.VolleyHelper;
import com.example.restclient.network.VolleyResultReceiver;

import javax.inject.Inject;

public class BashPresenter implements VolleyResultReceiver {

    @Inject
    public ILogger logger;
    @Inject
    public VolleyHelper volleyHelper;

    private final IBashActivity iBashActivity;

    public BashPresenter(IBashActivity iBashActivity) {
        this.iBashActivity = iBashActivity;
        MainApplication.getComponent().inject(this);
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

    private void parseResponse(String response) {
        final BashModel[] bashModels = new ModelJsonParser().parseBash(response);
        for (BashModel bashModel : bashModels) {
            logger.log(bashModel.toString());
        }
        iBashActivity.addTextViewsToListView(bashModels);
    }

}
