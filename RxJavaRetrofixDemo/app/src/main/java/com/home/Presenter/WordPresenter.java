package com.home.Presenter;

import android.util.Log;

import com.home.http.entity.WordResult;
import com.home.model.WordModel;
import com.home.view.MyObserver;
import com.home.view.WordView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class WordPresenter extends BasePresenter<WordView> {
    private static final String TAG = "WordPresenter";
    private WordModel wordModel;

    public WordPresenter(WordView baseView) {
        super(baseView);
        wordModel = new WordModel();
    }

    public void wordRequest(String wordValue) {
        wordModel.wordRequest(wordValue, new MyObserver<WordResult>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                super.onSubscribe(d);
            }

            @Override
            public void onNext(@NonNull WordResult wordResult) {
                super.onNext(wordResult);
                Log.e(TAG, "onNext: " + wordResult);
                view.wordRequestResult(wordResult);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        });
    }

}
