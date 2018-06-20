package com.home.adproj.viewfinder;

import android.content.Context;
import android.view.View;

import com.home.adproj.viewfinder.provider.Provider;

/**
 * Created by xuguohong on 2018/6/20.
 */

public class ViewProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((View)source).getContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((View)source).findViewById(id);
    }
}
