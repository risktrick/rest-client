package com.example.restclient;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

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
            String response = httpHelper.openUrl("http://example.com");

            if (response != null) {
                new LoggerConsole().log(response);
            }
        }
    });


}
