package com.home.adproj.viewfinder;

import android.app.Activity;
import android.view.View;

import com.home.adproj.viewfinder.provider.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuguohong on 2018/6/20.
 */

public class ViewFinder {
    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    private static final ViewProvider PROVIDER_VIEW = new ViewProvider();
    //缓存反射实例对象,提高性能
    private static final Map<String, Finder> FINDER_MAP = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }
    public static void inject(View view) {
        // for view
        inject(view, view);
    }
    public static void inject(Object host, View view) {
        // for fragments
        inject(host, view, PROVIDER_VIEW);
    }
    public static void inject(Object host, Object source, Provider provider){
        // how to implement ?
        String className = host.getClass().getName();
        Finder finder = FINDER_MAP.get(className);
        if(finder == null){
            Class<?> finderClass = null;
            try {
                finderClass = Class.forName(className + "$$Finder");
                finder = (Finder) finderClass.newInstance();
                FINDER_MAP.put(className,finder);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            finder.inject(host,source,provider);//调用实现的Finder接口内部类的inject方法
        }
    }
}
