package com.example.restclient.main_screen;

import java.util.List;

public interface IMainActivity {

    String getText();

    void setText(String text);

    void clickGetSources();

    void signalAddButtonsInLayout(List<String> buttonNames);
}
