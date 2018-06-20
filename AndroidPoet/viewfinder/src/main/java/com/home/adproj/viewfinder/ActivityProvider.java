package com.home.adproj.viewfinder;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.home.adproj.viewfinder.provider.Provider;

/**
 * Created by xuguohong on 2018/6/20.
 */

public class ActivityProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((Activity)source);
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity)source).findViewById(id);
    }
}
