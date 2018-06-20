package com.home.adproj.viewfinder.provider;

import android.content.Context;
import android.view.View;

/**
 * Created by xuguohong on 2018/6/20.
 */

public interface Provider {
    Context getContext(Object source);
    View findView(Object source, int id);
}
