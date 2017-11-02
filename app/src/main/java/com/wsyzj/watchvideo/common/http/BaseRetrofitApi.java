package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.test.City;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Admin on 2017/10/30.
 */

public interface BaseRetrofitApi {

    @GET("api/v1/common/region")
    Flowable<BaseEntity<List<City>>> getRegion();

}
