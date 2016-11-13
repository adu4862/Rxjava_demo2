package com.yl.recycerview_rxjava_retrofit.api;

import com.yl.recycerview_rxjava_retrofit.bean.ImageBean;
import com.yl.recycerview_rxjava_retrofit.bean.NewsBean;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class WarpApiService {
    public static final String KEY = "15df77608761144bb78d282dd3f0cc36";
    public static final String TYPE = "toutiao";

    private ApiService mApiService;

    public static WarpApiService createWarpAipService(ApiService apiService) {
        return new WarpApiService(apiService);
    }

    public WarpApiService(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<List<NewsBean.ResultBean.DataBean>> getResults() {
        Observable<List<NewsBean.ResultBean.DataBean>> result = mApiService
                .getNewsBeanList(KEY, TYPE)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsBean, List<NewsBean.ResultBean.DataBean>>() {

                    @Override
                    public List<NewsBean.ResultBean.DataBean> call(NewsBean newsBean) {
                        return newsBean.getResult().getData();
                    }
                });

        return result;
    }
    public Observable<List<ImageBean.TngouBean>> getImageResults(){
        Observable<List<ImageBean.TngouBean>> result = mApiService
                .getImageBeanList()
                .subscribeOn(Schedulers.io())
                .map(new Func1<ImageBean, List<ImageBean.TngouBean>>() {
                    @Override
                    public List<ImageBean.TngouBean> call(ImageBean imageBean) {
                        return imageBean.getTngou();
                    }
                });
        return result;
    }

}
