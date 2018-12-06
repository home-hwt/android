package com.home.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.home.mvp.presenter.impl.LoginPresenterImpl;
import com.home.mvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {
    private LoginPresenterImpl mLoginPresenterImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginPresenterImpl = new LoginPresenterImpl(this);
    }


    public void loginHandler(View view){
        mLoginPresenterImpl.doLogin("name","password");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loginSuccess(String msg) {

    }

    @Override
    public void loginFail(String errorMsg) {

    }
}
