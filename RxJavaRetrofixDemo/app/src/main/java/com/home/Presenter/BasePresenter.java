package com.home.Presenter;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class BasePresenter<V> {
    public V view;

    public BasePresenter(V v){
        this.view = v;
    }

    //添加关联
    public void addView(V v){
        this.view = v;
    }

    //断开关联
    public void detattch(){
        if(view != null){
            view = null;
        }
    }
}
