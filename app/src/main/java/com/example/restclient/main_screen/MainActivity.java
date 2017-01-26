package com.example.restclient.main_screen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restclient.R;

public class MainActivity extends Activity implements IMainActivity {

    MainPresenter presenter;
    TextView textViewSelectSource;
    Button buttonShowSources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSelectSource = (TextView) findViewById(R.id.textViewSelectSource);
        buttonShowSources = (Button) findViewById(R.id.buttonShowSources);
        buttonShowSources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick();
            }
        });

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
    public void onClickButton() {
        presenter.onClick();
    }
}
