package com.yl.recycerview_rxjava_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yl.recycerview_rxjava_retrofit.adapter.NewsAdapter;
import com.yl.recycerview_rxjava_retrofit.api.ApiService;
import com.yl.recycerview_rxjava_retrofit.api.WarpApiService;
import com.yl.recycerview_rxjava_retrofit.bean.NewsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class NewsActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    public static final String baseUrl = "http://v.juhe.cn";

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
                .getResults()
                //观察者处理数据
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsBean.ResultBean.DataBean>>() {
                    @Override
                    public void call(List<NewsBean.ResultBean.DataBean> dataBeen) {
                        NewsAdapter newsAdapter = new NewsAdapter(NewsActivity.this, dataBeen);
                        //设置recycler的布局
                        LinearLayoutManager manager = new LinearLayoutManager(NewsActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recycler.setLayoutManager(manager);

                        //绑定适配器
                        recycler.setAdapter(newsAdapter);
                        //绑定条目点击事件
                        newsAdapter.setmOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(View view, NewsBean.ResultBean.DataBean dataBean) {
                                Toast.makeText(NewsActivity.this, dataBean.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }

}
