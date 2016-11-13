package com.yl.recycerview_rxjava_retrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yl.recycerview_rxjava_retrofit.NewsActivity;
import com.yl.recycerview_rxjava_retrofit.R;
import com.yl.recycerview_rxjava_retrofit.bean.NewsBean;
import com.yl.recycerview_rxjava_retrofit.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> implements View.OnClickListener {

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /**
     * 定义接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, NewsBean.ResultBean.DataBean dataBean);
    }


    private NewsActivity newsActivity;
    private List<NewsBean.ResultBean.DataBean> dataList;

    public NewsAdapter(NewsActivity newsActivity, List<NewsBean.ResultBean.DataBean> dataList) {
        this.newsActivity = newsActivity;
        this.dataList = dataList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // View view = View.inflate(newsActivity, R.layout.item_list_news, null);
        View view = LayoutInflater.from(newsActivity).inflate(R.layout.item_list_news, parent, false);
        NewsHolder newsHolder = new NewsHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        NewsBean.ResultBean.DataBean dataBean = dataList.get(position);
        String icon = dataBean.getThumbnail_pic_s();
        GlideUtil.setCircleImageUrl(icon, holder.ivIcon, 4);
        holder.tvAuthorName.setText(dataBean.getAuthor_name());
        holder.tvDate.setText(dataBean.getDate());
        holder.tvNewsTittle.setText(dataBean.getTitle());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(dataBean);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view, (NewsBean.ResultBean.DataBean) view.getTag());
        }
    }


    static class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_news_tittle)
        TextView tvNewsTittle;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
