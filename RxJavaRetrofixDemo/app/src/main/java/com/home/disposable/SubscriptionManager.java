package com.home.disposable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class SubscriptionManager implements SubscriptionHelper {
    private CompositeDisposable mCompositeDisposable;


    private SubscriptionManager(){
        if(mCompositeDisposable != null){
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    public static SubscriptionManager getInstance(){
        return Creator.INSTANCE;
    }

    @Override
    public void add(Disposable disposable) {
        if(disposable != null && mCompositeDisposable != null){
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void cancel(Disposable disposable) {
        if(mCompositeDisposable != null){
            mCompositeDisposable.delete(disposable);
        }
    }

    @Override
    public void cancelAll() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
    }


    private static class Creator{
        private static final SubscriptionManager INSTANCE = new SubscriptionManager();
    }
}
