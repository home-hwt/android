package com.home.adproj.androidpoet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Code;
import com.example.Print;
import com.home.adproj.viewfinder.ViewFinder;
import com.test.anno.BindView;
import com.test.anno.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.test_tv)
    TextView mTestTv;
    @BindView(R.id.test_bt)
    Button mTestBt;

    @Override
    @Print
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_poet);
        process();
        ViewFinder.inject(this);
    }

    @Code(author = "closedevice", date = "20161225")
    private void process() {
    }

    @OnClick(R.id.test_bt)
    public void btClick() {
        Log.e("btClick", "test_bt");
        mTestTv.setText("btClick");
        Toast.makeText(this, "test_bt", Toast.LENGTH_SHORT).show();
    }
}
