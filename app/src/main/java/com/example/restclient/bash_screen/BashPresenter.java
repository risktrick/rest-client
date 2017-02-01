package com.example.restclient.bash_screen;

import com.example.restclient.LoggerConsole;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.network.HttpHelper;

public class BashPresenter {

    private final IBashActivity iBashActivity;
    private final LoggerConsole logger;

    public BashPresenter(IBashActivity iBashActivity) {
        this.iBashActivity = iBashActivity;
        logger = LoggerConsole.getInstance();
    }

    public void getBashJokes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpHelper httpHelper = new HttpHelper();
                String request = "http://www.umori.li/api/get?" + "site=bash.im";
                String response = httpHelper.openUrl(request);
                logger.log("response len " + response.length());

                if (response != null) {
                    BashModel[] bashModels = new ModelJsonParser().parseBash(response);

                    for (BashModel bashModel : bashModels) {
                        logger.log(bashModel.toString());
                    }

                    iBashActivity.addTextViewsToListView(bashModels);
                } else {
                    logger.log("error");
                }
            }
        }).start();
    }
}
