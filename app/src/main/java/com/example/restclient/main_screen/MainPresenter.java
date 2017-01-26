package com.example.restclient.main_screen;

import com.example.restclient.HttpHelper;
import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;

public class MainPresenter {

    final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    final String UMORILI_SOURCE = "http://www.umori.li/api/sources";

    IMainActivity IMainActivity;
    ILogger logger;

    public MainPresenter(IMainActivity IMainActivity) {
        this.IMainActivity = IMainActivity;
        IMainActivity.setText("Hello from presenter :)");
        logger = new LoggerConsole();
    }

    void onClick() {

        logger.log("will start thread here");

        //if inet is turn on
        //thread.start();
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
