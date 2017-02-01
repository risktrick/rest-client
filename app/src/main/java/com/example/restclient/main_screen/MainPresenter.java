package com.example.restclient.main_screen;

import android.content.Context;

import com.example.restclient.Utils;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.model.SourceModel;
import com.example.restclient.network.HttpHelper;
import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    final String UMORILI_SOURCE = "http://www.umori.li/api/sources";

    IMainActivity iMainActivity;
    ILogger logger;
    Context context;

    public MainPresenter(IMainActivity IMainActivity) {
        this.iMainActivity = IMainActivity;
        IMainActivity.setText("Hello from presenter :)");
        logger = LoggerConsole.getInstance();
        context = (Context) iMainActivity;
    }

    void clickGetSources() {
        logger.log("will start threadGetSources here");

        if (Utils.isNetworkAvailable(context)) {
            threadGetSources.start();
        } else {
            iMainActivity.showNetworkNotAvailable();
        }
    }

    private Thread threadGetSources = new Thread(new Runnable() {
        @Override
        public void run() {
            HttpHelper httpHelper = new HttpHelper();
            String response = httpHelper.openUrl(UMORILI_SOURCE);

            if (response != null) {
                logger.log(response);

                List<List<SourceModel>> sourceModelss = new ModelJsonParser().parseSources(response, logger);
                List<SourceModel> reformattedSourceModels = new ArrayList<>();

                for (List<SourceModel> sourceModels : sourceModelss) {
                    for (SourceModel sourceModel : sourceModels) {
                        reformattedSourceModels.add(sourceModel);
                    }
                }

                for (SourceModel reformattedSourceModel : reformattedSourceModels) {
                    logger.log(reformattedSourceModel.toString());
                }

                iMainActivity.signalAddButtonsInLayout(reformattedSourceModels);
            }
        }
    });

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
                } else {
                    logger.log("error");
                }
            }
        }).start();
    }
}
