package com.example.restclient.utils;

import android.util.Log;

public class LoggerConsole implements ILogger {

    @Override
    public void log(String s) {
        Log.e("client", s);
    }

}
