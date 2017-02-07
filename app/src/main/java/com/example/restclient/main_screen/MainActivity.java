package com.example.restclient.main_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restclient.MainApplication;
import com.example.restclient.utils.ILogger;
import com.example.restclient.R;
import com.example.restclient.bash_screen.BashActivity;
import com.example.restclient.model.SourceModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements IMainActivity {

    static final String KEY_SOURCE_MODELS = "KEY_SOURCE_MODELS";

    @BindView(R.id.textViewGetSources) TextView textViewGetSources;
    @BindView(R.id.buttonGetSources) Button buttonGetSources;
    @BindView(R.id.layoutSources) LinearLayout layoutSources;

    ArrayList<SourceModel> sourceModels;
    MainPresenter presenter;
    @Inject ILogger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainApplication.getComponent().inject(this);
        ButterKnife.bind(this);

        buttonGetSources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGetSources();
            }
        });
        presenter = new MainPresenter(this);

        if (savedInstanceState != null) {
            sourceModels = (ArrayList<SourceModel>) savedInstanceState.getSerializable(KEY_SOURCE_MODELS);
            updateButtonsInLayout();
        }
    }

    @Override
    public String getText() {
        return textViewGetSources != null ? textViewGetSources.getText().toString() : null;
    }

    @Override
    public void setText(String text) {
        if (textViewGetSources != null) {
            textViewGetSources.setText(text);
        } else {
            logger.log("textViewGetSources is null");
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
    public void addButtonsInLayout(ArrayList<SourceModel> sourceModels) {
        this.sourceModels = sourceModels;
        if (this.sourceModels != null) {
            updateButtonsInLayout();
        }
    }

    void updateButtonsInLayout(){
        for (SourceModel sourceModel : sourceModels) {
            Button button = new Button(MainActivity.this);
            button.setText(sourceModel.getDesc());
            layoutSources.addView(button);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(KEY_SOURCE_MODELS, sourceModels);
    }
}
