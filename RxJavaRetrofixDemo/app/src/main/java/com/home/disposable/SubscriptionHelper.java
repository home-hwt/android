package com.home.disposable;

import io.reactivex.disposables.Disposable;

/**
 * Created by xuguohong on 2018/7/16.
 */

public interface SubscriptionHelper {
    void add(Disposable disposable);
    void cancel(Disposable disposable);
    void cancelAll();
}
