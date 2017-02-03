package com.example.restclient.main_screen;

import com.example.restclient.model.SourceModel;

import java.util.ArrayList;
import java.util.List;

public interface IMainActivity {

    String getText();

    void setText(String text);

    void clickGetSources();

    void addButtonsInLayout(ArrayList<SourceModel> buttonNames);

    void showError(String errorStr);
}
