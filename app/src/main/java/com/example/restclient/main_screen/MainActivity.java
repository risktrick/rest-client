package com.example.restclient.main_screen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.restclient.LoggerConsole;
import com.example.restclient.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements IMainActivity {

    private MainPresenter presenter;
    private TextView textViewSelectSource;
    private Button buttonGetSources;
    private LinearLayout layoutSources;


    public static final int ADD_BUTTONS_IN_LAYOUT = 1;

    private Handler handler;
    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            LoggerConsole.getInstance().log("handleMessage");
            if (msg.what == ADD_BUTTONS_IN_LAYOUT) {
                if(msg.obj != null && msg.obj instanceof List){
                    List<String> buttonNames = (List<String>) msg.obj;
                    addButtonsInLayout(buttonNames);
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSelectSource = (TextView) findViewById(R.id.textViewGetSources);
        buttonGetSources = (Button) findViewById(R.id.buttonGetSources);
        buttonGetSources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGetSources();
            }
        });
        layoutSources = (LinearLayout)findViewById(R.id.layoutSources);

        handler = new Handler(callback);
        presenter = new MainPresenter(this);
    }

    @Override
    public String getText() {
        return textViewSelectSource != null ? textViewSelectSource.getText().toString() : null;
    }

    @Override
    public void setText(String text) {
        if (textViewSelectSource != null) {
            textViewSelectSource.setText(text);
        } else {
            LoggerConsole.getInstance().log("textViewSelectSource is null");
        }
    }

    @Override
    public void clickGetSources() {
        presenter.clickGetSources();
    }

    @Override
    public void signalAddButtonsInLayout(final List<String> buttonNames) {
        LoggerConsole.getInstance().log("MainActivity.signalAddButtonsInLayout");
        Message message = handler.obtainMessage(ADD_BUTTONS_IN_LAYOUT, buttonNames);
        handler.sendMessage(message);
    }

    void addButtonsInLayout(List<String> buttonNames) {
        for (String buttonName : buttonNames) {
            Button button = new Button(MainActivity.this);
            button.setText(buttonName);
            layoutSources.addView(button);
        }
    }

}
