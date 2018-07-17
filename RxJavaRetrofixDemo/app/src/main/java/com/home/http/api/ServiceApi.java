package com.home.http.api;

import com.home.http.entity.WordResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by xuguohong on 2018/7/16.
 */

public interface ServiceApi {

    @GET("/chengyu/query?key=825efd5376b68222183056d472d159a0&word=wordValue")
    Observable<WordResult> wordRequest(@Query("wordValue") String wordValue);
}
