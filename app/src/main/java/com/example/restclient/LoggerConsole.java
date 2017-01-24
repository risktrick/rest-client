package com.example.restclient;

import android.util.Log;

public class LoggerConsole implements ILogger {
    @Override
    public void log(String s) {
        Log.e("client", s);
    }
}
