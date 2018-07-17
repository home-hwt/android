package com.home.model;

import com.home.http.api.ServiceApi;
import com.home.http.entity.WordResult;
import com.home.http.retrofit.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class WordModel {
    public void wordRequest(String wordValue, Observer<WordResult> observer){
        RetrofitFactory.getInstance().createApi(ServiceApi.class)
        .wordRequest(wordValue)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
    }
}
