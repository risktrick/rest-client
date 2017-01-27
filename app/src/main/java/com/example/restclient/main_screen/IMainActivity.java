package com.example.restclient.main_screen;

import com.example.restclient.model.SourceModel;

import java.util.List;

public interface IMainActivity {

    String getText();

    void setText(String text);

    void clickGetSources();

    void signalAddButtonsInLayout(List<SourceModel> buttonNames);

    void showNetworkNotAvailable();
}
