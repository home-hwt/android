package com.home.mvp.view;

/**
 * Created by xuguohong on 2018/12/6.
 */

public interface LoginView {
    public void showProgress();
    public void hideProgress();
    public void loginSuccess(String msg);
    public void loginFail(String errorMsg);
}
