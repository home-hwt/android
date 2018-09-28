package com.home.dot.androidautodot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBt = (Button) findViewById(R.id.button);
        testBt.setOnClickListener(this);
        this.testBefore();

        aspectJ1();
        aspectJ2();
        aspectJ3();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: execute");
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
    }



    public void testBefore(){
        Log.e(TAG, "testBefore: ");
    }



    public void aspectJTest() {
        Log.e(TAG, "execute aspectJTest");
    }

    public void aspectJ1(){
        aspectJTest();
    }

    public void aspectJ2(){
        aspectJTest();
    }

    public void aspectJ3(){
        aspectJTest();
    }
}
