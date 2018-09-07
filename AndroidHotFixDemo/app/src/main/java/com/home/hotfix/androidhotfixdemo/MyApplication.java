package com.home.hotfix.androidhotfixdemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.home.util.HotFixEngine;

import java.io.File;

/**
 * Created by xuguohong on 2018/9/7.
 */

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.hotFixBug();
    }


    public void hotFixBug() {
        File jarFile = new File(Environment.getExternalStorageDirectory() + File.separator + "hot_dex.jar");
        if (!jarFile.exists() || !jarFile.canRead()) {//文件不存在或者没权限返回
            return;
        }

//        HotFixEngine.getInstance().loadPatchDex(this, jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null);


        HotFixEngine.getInstance().loadPatchDex2(this, jarFile.getAbsolutePath(),getExternalCacheDir().getAbsolutePath(),null);
    }
}
