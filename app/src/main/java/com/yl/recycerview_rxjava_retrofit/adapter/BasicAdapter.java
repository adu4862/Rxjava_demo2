package com.yl.recycerview_rxjava_retrofit.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yl.recycerview_rxjava_retrofit.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public abstract class BasicAdapter<T> extends RecyclerView.Adapter<BasicAdapter.BasicHolder> implements View.OnClickListener {

//    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }

    //private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Activity newsActivity;
    private List<T> list;

    public BasicAdapter(Activity newsActivity, List<T> list) {
        this.newsActivity = newsActivity;
        this.list = list;
    }

    @Override
    public BasicHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(newsActivity).inflate(R.layout.item_list_news, parent, false);
        BasicHolder basicHolder = new BasicHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return basicHolder;
    }

    @Override
    public void onBindViewHolder(BasicHolder holder, int position) {
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    static class BasicHolder extends RecyclerView.ViewHolder {
        public BasicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
