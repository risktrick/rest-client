package com.example.restclient.main_screen;

import android.os.Handler;

import com.example.restclient.HttpHelper;
import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
    final String UMORILI_SOURCE = "http://www.umori.li/api/sources";

    IMainActivity mainActivity;
    ILogger logger;

    public MainPresenter(IMainActivity IMainActivity) {
        this.mainActivity = IMainActivity;
        IMainActivity.setText("Hello from presenter :)");
        logger = LoggerConsole.getInstance();
    }

    void clickGetSources() {
        logger.log("will start thread here");
        //if inet is turn on
        //thread.start();

//        List<String> buttonNames = getSourceNames();
//        mainActivity.addButtonsInLayout(buttonNames);

        getAndShowSourceNames();
    }

    private void getAndShowSourceNames() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<String> buttonNames = new ArrayList<>();
                buttonNames.add("bash");
                buttonNames.add("kex");


                mainActivity.addButtonsInLayout(buttonNames);
            }
        }).start();



    }

    void addButtonsInLayout(List<String> buttonNames){

    }


    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            HttpHelper httpHelper = new HttpHelper();
            String response = httpHelper.openUrl(UMORILI_SOURCE);

            if (response != null) {
                logger.log(response);

                //parseBashJson(response, logger);
                //new JsonParser().parseSources(response, logger);
            }
        }
    });

}
