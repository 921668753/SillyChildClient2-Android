package com.sillykid.app.adapter.homepage.privatecustom.cityselect.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.RecommendedBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;


/**
 * 热门目的地 适配器
 * Created by Admin on 2017/8/15.
 */

public class RecommendedViewAdapter extends RecyclerView.Adapter<RecommendedViewAdapter.ViewHolder> {
    protected Context mContext;
    protected List<DataBean> mDatas;
    protected LayoutInflater mInflater;

    private ViewCallBack callBack;//回调

    public RecommendedViewAdapter(Context mContext, List<DataBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<DataBean> getDatas() {
        return mDatas;
    }

    public RecommendedViewAdapter setDatas(List<DataBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_cityclassification, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DataBean resultBean = mDatas.get(position);
        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, resultBean.getCity_picture(),holder.img_classification, R.mipmap.placeholderfigure);
        /**
         * 城市名字
         */
        holder.tv_classificationName.setText(resultBean.getCity_name());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClickListener(resultBean);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_classificationName;
        ImageView img_classification;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_classificationName = (TextView) itemView.findViewById(R.id.tv_classificationName);
            img_classification = (ImageView) itemView.findViewById(R.id.img_classification);
            content = itemView.findViewById(R.id.content);
        }
    }

    public void setViewCallBack(ViewCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ViewCallBack {
        void onClickListener(DataBean dataBean);
    }

}
