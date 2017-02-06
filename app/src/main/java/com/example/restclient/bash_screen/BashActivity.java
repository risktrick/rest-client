package com.example.restclient.bash_screen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.restclient.R;
import com.example.restclient.model.BashModel;

public class BashActivity extends Activity implements IBashActivity{

    private LinearLayout layoutBash;
    private BashPresenter presenter;
    private BashModel[] bashModels;
    private String KEY_BASH_MODELS = "KEY_BASH_MODELS";
    private ScrollView scrollViewBash;
    private static final String ARTICLE_SCROLL_POSITION = "ARTICLE_SCROLL_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bash);

        layoutBash = (LinearLayout)findViewById(R.id.layoutBash);
        scrollViewBash = (ScrollView)findViewById(R.id.scrollViewBash);
        presenter = new BashPresenter(this);

        if (savedInstanceState != null) {
            bashModels = (BashModel[]) savedInstanceState.getSerializable(KEY_BASH_MODELS);
            if (bashModels != null) {
                updateLayout();
            }

            final int[] position = savedInstanceState.getIntArray(ARTICLE_SCROLL_POSITION);
            if(position != null) {
                scrollViewBash.post(new Runnable() {
                    public void run() {
                        scrollViewBash.scrollTo(position[0], position[1]);
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bashModels == null) {
            presenter.getBashJokes();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(KEY_BASH_MODELS, bashModels);
        outState.putIntArray(ARTICLE_SCROLL_POSITION, new int[]{ scrollViewBash.getScrollX(), scrollViewBash.getScrollY()});
    }

    @Override
    public void addTextViewsToListView(final BashModel[] bashModels) {
        this.bashModels = bashModels;
        if (bashModels != null) {
            updateLayout();
        }
    }

    private void updateLayout() {
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
