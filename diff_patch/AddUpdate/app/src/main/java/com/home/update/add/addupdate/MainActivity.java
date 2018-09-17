package com.home.update.add.addupdate;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String mOldApkPath;//获取本地安装包的路径
    String mNewApkPath = this.getSdPath() + "new.apk";//新apk用与跟旧的apk生成patch文件
    String mCreateNewApkPath = this.getSdPath() + "createNew.apk";//旧的apk与patch文件合成新的apk包
    String mPatchPath = this.getSdPath() + "patch.patch";//生成的patch差异包文件

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOldApkPath = this.getApplicationContext().getPackageResourcePath();
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1);
        }
    }


    //差异化操作  生成差异patch包
    public void diffHandler(View view){
        this.diff(mOldApkPath,mNewApkPath,mPatchPath);
        Log.e(TAG, "diffHandler: success");
    }

    //合成新包操作
    public void patchHandler(View view){
        Log.e(TAG, "patchHandler: " + mOldApkPath + "patch:" + mPatchPath);
        this.patch(mOldApkPath,mPatchPath,mCreateNewApkPath);
        Log.e(TAG, "patchHandler: success");
    }

    private String getSdPath(){
        return Environment.getExternalStorageDirectory().getPath()+ File.separator;
    }

    /**
     * 差异化操作  生成差异patch包
     * 根据新旧包生成差量包(patch包)
     * @param oldApkPath
     * @param newApkPath
     * @param patchPath
     */
    public native void diff(String oldApkPath,String newApkPath,String patchPath);

    /**
     * 根据旧包和差量包合成新包APK操作
     * @param oldApkPath
     * @param patchPath
     * @param newApkPath
     */
    public native void patch(String oldApkPath,String patchPath,String newApkPath);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
