package com.home.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.home.Presenter.WordPresenter;
import com.home.adproj.rxjavaretrofixdemo.R;
import com.home.disposable.SubscriptionManager;
import com.home.http.entity.WordResult;

public class MainActivity extends AppCompatActivity implements WordView{

    private static final String TAG = "MainActivity";
    private WordPresenter wordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordPresenter = new WordPresenter(this);
    }

    @Override
    public void wordRequestResult(WordResult wordResult) {
        Log.e(TAG, "wordRequestResult: " + wordResult);
    }

    public void request(View view){
        wordPresenter.wordRequest("好");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wordPresenter.detattch();
        wordPresenter = null;
        SubscriptionManager.getInstance().cancelAll();
    }
}
