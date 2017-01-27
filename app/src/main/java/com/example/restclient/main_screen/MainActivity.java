package com.example.restclient.main_screen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.restclient.R;

import java.util.List;

public class MainActivity extends Activity implements IMainActivity {

    private MainPresenter presenter;
    private TextView textViewSelectSource;
    private Button buttonGetSources;
    private LinearLayout layoutSources;
    private Handler handler;

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

        handler = new Handler();
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
            Log.e("aa", "textViewSelectSource is null");
        }
    }

    @Override
    public void clickGetSources() {
        presenter.clickGetSources();
    }

    @Override
    public void addButtonsInLayout(final List<String> buttonNames) {

        final Context context = MainActivity.this;
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (String buttonName : buttonNames) {
                    Button button = new Button(context);
                    button.setText(buttonName);
                    layoutSources.addView(button);
                }
            }
        });

        //layoutSources
    }


}
