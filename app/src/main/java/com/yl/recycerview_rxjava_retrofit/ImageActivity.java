package com.yl.recycerview_rxjava_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.yl.recycerview_rxjava_retrofit.adapter.ImagesAdapter;
import com.yl.recycerview_rxjava_retrofit.api.ApiService;
import com.yl.recycerview_rxjava_retrofit.api.WarpApiService;
import com.yl.recycerview_rxjava_retrofit.bean.ImageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class ImageActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    public static final String baseUrl = "http://www.tngou.net";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        ApiService apiService = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加Rxjava回调适配器工厂
                .client(new OkHttpClient())
                .build().create(ApiService.class);

        WarpApiService
                .createWarpAipService(apiService)
                .getImageResults()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageBean.TngouBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(final List<ImageBean.TngouBean> tngouBeen) {
                        //设置recycler的布局
                        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recycler.setLayoutManager(manager);
                                ImagesAdapter imagesAdapter = new ImagesAdapter(ImageActivity.this, tngouBeen);
                                recycler.setAdapter(imagesAdapter);
                            }
                        });
                    }
                });

    }
}
//    //设置recycler的布局
//    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
////                        manager.setOrientation(LinearLayoutManager.VERTICAL);
//recycler.setLayoutManager(manager);
//        ImagesAdapter imagesAdapter = new ImagesAdapter(ImageActivity.this, tngouBeen);
//        recycler.setAdapter(imagesAdapter);
//        imagesAdapter.setmOnItemClickListener(new ImagesAdapter.OnRecyclerViewItemClickListener() {
//@Override
//public void onItemClick(View view, ImageBean.TngouBean dataBean) {
//        Toast.makeText(ImageActivity.this, dataBean.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//        });