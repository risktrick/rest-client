package com.example.restclient.main_screen;

import android.content.Context;

import com.example.restclient.MainApplication;
import com.example.restclient.utils.Utils;
import com.example.restclient.model.ModelJsonParser;
import com.example.restclient.model.SourceModel;
import com.example.restclient.utils.ILogger;
import com.example.restclient.utils.LoggerConsole;
import com.example.restclient.network.VolleyHelper;
import com.example.restclient.network.VolleyResultReceiver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements VolleyResultReceiver{

    private final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    private final String UMORILI_SOURCES = "http://www.umori.li/api/sources";

    private IMainActivity iMainActivity;

    @Inject
    public ILogger logger;
    @Inject
    public VolleyHelper volleyHelper;
    @Inject
    public Context context;

    public MainPresenter(IMainActivity IMainActivity) {
        this.iMainActivity = IMainActivity;
        MainApplication.getComponent().inject(this);
    }

    void clickGetSources() {
        logger.log("clickGetSources");
        if (Utils.isNetworkAvailable(context)) {
            volleyHelper.requestUsingVolley(UMORILI_SOURCES, this);
        } else {
            iMainActivity.showError("Network is not available. Check the connection.");
        }
    }

    @Override
    public void onGettingResponse(String response) {
        if (response != null) {
            logger.log(response);

            List<List<SourceModel>> sourceModelss = new ModelJsonParser().parseSources(response, logger);
            ArrayList<SourceModel> reformattedSourceModels = reformatSourceModels(sourceModelss);

            iMainActivity.addButtonsInLayout(reformattedSourceModels);
        }
    }

    ArrayList<SourceModel> reformatSourceModels(List<List<SourceModel>> sourceModelss) {
        ArrayList<SourceModel> reformattedSourceModels = new ArrayList<>();

        for (List<SourceModel> sourceModels : sourceModelss) {
            for (SourceModel sourceModel : sourceModels) {
                reformattedSourceModels.add(sourceModel);
            }
        }
        return reformattedSourceModels;
    }

    @Override
    public void onGettingError(String response) {
        iMainActivity.showError(response);
    }
}
