package com.home.adproj.viewfinder;

import com.home.adproj.viewfinder.provider.Provider;

/**
 * Created by xuguohong on 2018/6/20.
 */

public interface Finder<T> {
    void inject(T host, Object source, Provider provider);
}
