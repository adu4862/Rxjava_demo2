package com.yl.recycerview_rxjava_retrofit.api;

import com.yl.recycerview_rxjava_retrofit.bean.ImageBean;
import com.yl.recycerview_rxjava_retrofit.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * http://v.juhe.cn/toutiao/?key=15df77608761144bb78d282dd3f0cc36&type=toutiao
 * <p>
 * http://www.tngou.net/tnfs/api/news
 */

public interface ApiService {
    public static final String IMAGE_BASE = "http://tnfs.tngou.net/image";


    @GET("/toutiao")
    Observable<NewsBean> getNewsBeanList(@Query("key") String key, @Query("type") String type);

    @GET("/tnfs/api/news")
    Observable<ImageBean> getImageBeanList();
}
