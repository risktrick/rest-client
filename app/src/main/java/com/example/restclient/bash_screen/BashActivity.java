package com.example.restclient.bash_screen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.restclient.R;
import com.example.restclient.main_screen.MainActivity;
import com.example.restclient.model.BashModel;
import com.example.restclient.model.SourceModel;

public class BashActivity extends Activity implements IBashActivity{

    private LinearLayout layoutBash;
    private BashPresenter presenter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bash);

        layoutBash = (LinearLayout)findViewById(R.id.layoutBash);
        handler = new Handler();
        presenter = new BashPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getBashJokes();
    }

    @Override
    public void addTextViewsToListView(final BashModel[] bashModels) {
        for (int i = 0; i < bashModels.length; i++) {
            TextView textView = new TextView(BashActivity.this);
            textView.setText(bashModels[i].elementPureHtml);

            if (i % 2 == 0) {
                textView.setTextColor(Color.BLUE);
            }

            layoutBash.addView(textView);
        }
    }

    @Override
    public void showError() {

    }
}
