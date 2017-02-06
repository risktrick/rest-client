package com.example.restclient;

import com.example.restclient.bash_screen.BashPresenter;
import com.example.restclient.dagger_classes.LoggerModule;
import com.example.restclient.dagger_classes.VolleyModule;
import com.example.restclient.main_screen.MainActivity;
import com.example.restclient.main_screen.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContextModule.class, LoggerModule.class, VolleyModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);             //LoggerModule
    void inject(MainPresenter mainPresenter);          //LoggerModule
    void inject(BashPresenter bashPresenter);          //LoggerModule

}
