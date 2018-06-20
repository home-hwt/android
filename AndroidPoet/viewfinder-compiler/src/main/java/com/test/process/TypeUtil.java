package com.test.process;

import com.squareup.javapoet.ClassName;

/**
 * Created by xuguohong on 2018/6/20.
 */

public class TypeUtil {
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName ANDROID_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName FINDER = ClassName.get("com.home.adproj.viewfinder", "Finder");
    public static final ClassName PROVIDER = ClassName.get("com.home.adproj.viewfinder.provider", "Provider");
}
