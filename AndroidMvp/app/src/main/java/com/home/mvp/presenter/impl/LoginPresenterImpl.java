package com.home.mvp.presenter.impl;

import com.home.mvp.model.LoginModel;
import com.home.mvp.model.LoginModel.OnLoginListener;
import com.home.mvp.model.impl.LoginModelImpl;
import com.home.mvp.presenter.LoginPresenter;
import com.home.mvp.view.LoginView;

/**
 * Created by xuguohong on 2018/12/6.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void doLogin(String userName, String password) {
        this.mLoginModel.login(userName, password, new OnLoginListener() {
            @Override
            public void onLoginSuccess(String content) {
                mLoginView.loginSuccess(content);
            }

            @Override
            public void onLoginFail(String errorMsg) {
                mLoginView.loginFail(errorMsg);
            }
        });
    }
}
