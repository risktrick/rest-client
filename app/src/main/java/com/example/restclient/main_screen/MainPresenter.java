package com.example.restclient.main_screen;

import android.content.Context;

import com.example.restclient.Utils;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.model.SourceModel;
import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;
import com.example.restclient.network.NetworkResultReceiver;
import com.example.restclient.network.NetworkThread;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements NetworkResultReceiver{

    private final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    private final String UMORILI_SOURCES = "http://www.umori.li/api/sources";

    private IMainActivity iMainActivity;
    private ILogger logger;
    private Context context;
    private NetworkThread networkThread;

    public MainPresenter(IMainActivity IMainActivity) {
        this.iMainActivity = IMainActivity;
        logger = LoggerConsole.getInstance();
        context = (Context) iMainActivity;

        networkThread = new NetworkThread("main_presenter_thread");
        networkThread.setResultReceiver(this);
        networkThread.start();
        networkThread.getLooper();
    }

    void clickGetSources() {
        logger.log("will start threadGetSources here");

        if (Utils.isNetworkAvailable(context)) {
            networkThread.makeGetRequest(UMORILI_SOURCES);
        } else {
            iMainActivity.showNetworkNotAvailable();
        }
    }

    @Override
    public void onGettingResults(String response) {
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
}
