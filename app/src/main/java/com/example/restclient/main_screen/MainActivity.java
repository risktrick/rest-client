package com.example.restclient.main_screen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restclient.LoggerConsole;
import com.example.restclient.R;
import com.example.restclient.model.SourceModel;

import java.util.List;

public class MainActivity extends Activity implements IMainActivity {

    public static final int ADD_BUTTONS_IN_LAYOUT = 1;
    public static final int SHOW_PROGRESS_BAR = 2;
    public static final int HIDE_PROGRESS_BAR = 3;
    private static final int SHOW_NETWORK_ERROR = 4;

    private MainPresenter presenter;
    private TextView textViewSelectSource;
    private Button buttonGetSources;
    private LinearLayout layoutSources;

    private Handler handler;
    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            LoggerConsole.getInstance().log("handleMessage");
            if (msg.what == ADD_BUTTONS_IN_LAYOUT) {
                try{
                    // TODO: 27.01.17 check casting
                    List<SourceModel> sourceModels = (List<SourceModel>) msg.obj;
                    addButtonsInLayout(sourceModels);
                }catch (Exception e) {
                    LoggerConsole.getInstance().log(e.toString());
                    e.printStackTrace();
                }
            } else if (msg.what == SHOW_PROGRESS_BAR) {
                // TODO: 27.01.17 add progress bar
            } else if (msg.what == HIDE_PROGRESS_BAR){
                // TODO: 27.01.17 add progress bar
            } else if (msg.what == SHOW_NETWORK_ERROR) {
                Toast.makeText(MainActivity.this, "Network is not available. Check the connection.", Toast.LENGTH_LONG).show();
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
    public void signalAddButtonsInLayout(final List<SourceModel> buttonNames) {
        LoggerConsole.getInstance().log("MainActivity.signalAddButtonsInLayout");
        Message message = handler.obtainMessage(ADD_BUTTONS_IN_LAYOUT, buttonNames);
        handler.sendMessage(message);
    }

    @Override
    public void showNetworkNotAvailable() {
        handler.sendEmptyMessage(SHOW_NETWORK_ERROR);
    }

    void addButtonsInLayout(List<SourceModel> sourceModels) {
        for (SourceModel sourceModel : sourceModels) {
            Button button = new Button(MainActivity.this);
            button.setText(sourceModel.getDesc());
            layoutSources.addView(button);
        }
    }

}
