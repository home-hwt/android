package com.home.view;

import com.home.disposable.SubscriptionManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class MyObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        SubscriptionManager.getInstance().add(d);
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
