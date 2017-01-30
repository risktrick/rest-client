package com.example.restclient;

import android.util.Log;

public class LoggerConsole implements ILogger {
    private static LoggerConsole instance;

    private LoggerConsole() {
    }

    public static LoggerConsole getInstance() {
        if (instance == null) {
            instance = new LoggerConsole();
        }
        return instance;
    }

    @Override
    public void log(String s) {
        Log.e("client", s);
    }
}
