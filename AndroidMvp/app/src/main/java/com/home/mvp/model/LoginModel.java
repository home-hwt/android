package com.home.mvp.model;

/**
 * Created by xuguohong on 2018/12/6.
 */

public interface LoginModel {
    interface OnLoginListener{
        public void onLoginSuccess(String content);
        public void onLoginFail(String errorMsg);
    }

    public void login(String userName,String password,OnLoginListener loginListener);
}
