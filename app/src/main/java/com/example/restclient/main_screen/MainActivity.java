package com.example.restclient.main_screen;

import android.app.Activity;
import android.content.Intent;
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
import com.example.restclient.bash_screen.BashActivity;
import com.example.restclient.model.SourceModel;

import java.util.List;

public class MainActivity extends Activity implements IMainActivity {

    private MainPresenter presenter;
    private TextView textViewSelectSource;
    private Button buttonGetSources;
    private LinearLayout layoutSources;

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
        //startActivity(new Intent(this, BashActivity.class));
    }

    @Override
    public void showError(String errorStr) {
        if (errorStr == null) {
            Toast.makeText(MainActivity.this, errorStr, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void addButtonsInLayout(List<SourceModel> sourceModels) {
        for (SourceModel sourceModel : sourceModels) {
            Button button = new Button(MainActivity.this);
            button.setText(sourceModel.getDesc());
            layoutSources.addView(button);
        }
    }

}
