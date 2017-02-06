package com.example.restclient.dagger_classes;

import com.example.restclient.utils.ILogger;
import com.example.restclient.utils.LoggerConsole;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoggerModule {

    @Provides
    @Singleton
    ILogger provideLogger() {
        return new LoggerConsole();
    }
}
