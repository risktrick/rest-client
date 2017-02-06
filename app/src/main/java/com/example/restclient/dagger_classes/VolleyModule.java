package com.example.restclient.dagger_classes;

import android.content.Context;

import com.example.restclient.network.VolleyHelper;
import com.example.restclient.utils.ILogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//@Module(includes = ContextModule.class)
@Module
public class VolleyModule {

    @Provides
    @Singleton
    VolleyHelper provideVolleyHelper(Context context, ILogger logger) {
        return new VolleyHelper(context, logger);
    }
}
