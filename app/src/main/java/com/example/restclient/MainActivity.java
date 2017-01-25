package com.example.restclient;

import android.app.Activity;
import android.os.Bundle;

import com.example.restclient.model.BashModel;
import com.example.restclient.model.JsonParser;
import com.example.restclient.model.SourceModel;
import com.example.restclient.model.SourcesModel;
import com.google.gson.Gson;

public class MainActivity extends Activity {

    final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    final String UMORILI_SOURCE = "http://www.umori.li/api/sources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread.start();
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            HttpHelper httpHelper = new HttpHelper();
            String response = httpHelper.openUrl(UMORILI_SOURCE);

            if (response != null) {
                ILogger logger = new LoggerConsole();
                logger.log(response);

                //parseBashJson(response, logger);
                //new JsonParser().parseSources(response, logger);
            }
        }
    });





}
