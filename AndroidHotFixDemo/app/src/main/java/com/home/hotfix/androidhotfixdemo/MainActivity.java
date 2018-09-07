package com.home.hotfix.androidhotfixdemo;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.home.hot.HotEntity;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.requestPermissions(new String[]{
                "android.permission.WRITE_EXTERNAL_STORAGE"
        },1);
    }

    public void getDexContent(View v) {
        String result = new HotEntity().hot();
        Toast.makeText(this, result + "2", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ((MyApplication)getApplication()).hotFixBug();
    }
}
