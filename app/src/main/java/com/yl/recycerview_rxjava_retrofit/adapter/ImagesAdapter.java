package com.yl.recycerview_rxjava_retrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yl.recycerview_rxjava_retrofit.ImageActivity;
import com.yl.recycerview_rxjava_retrofit.R;
import com.yl.recycerview_rxjava_retrofit.api.ApiService;
import com.yl.recycerview_rxjava_retrofit.bean.ImageBean;
import com.yl.recycerview_rxjava_retrofit.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.NewsHolder> implements View.OnClickListener {



    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /**
     * 定义接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, ImageBean.TngouBean dataBean);
    }


    private ImageActivity newsActivity;
    private List<ImageBean.TngouBean> dataList;

    public ImagesAdapter(ImageActivity newsActivity, List<ImageBean.TngouBean> dataList) {
        this.newsActivity = newsActivity;
        this.dataList = dataList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // View view = View.inflate(newsActivity, R.layout.item_list_news, null);
        View view = LayoutInflater.from(newsActivity).inflate(R.layout.item_iamge, parent, false);
        NewsHolder newsHolder = new NewsHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        ImageBean.TngouBean dataBean = dataList.get(position);
        String icon = dataBean.getImg();
        icon = ApiService.IMAGE_BASE + icon;
        GlideUtil.setCircleImageUrl(icon, holder.iv, 4);
        holder.tv.setText(dataBean.getTitle());
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
            mOnItemClickListener.onItemClick(view, (ImageBean.TngouBean) view.getTag());
        }
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
