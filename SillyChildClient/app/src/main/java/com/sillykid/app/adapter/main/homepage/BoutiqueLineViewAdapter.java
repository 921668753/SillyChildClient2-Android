package com.sillykid.app.adapter.main.homepage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.klavor.widget.RatingBar;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.DataBean.GoodsListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 精品线路 适配器
 * 描述:RecyclerView 多 item 类型适配器
 * Created by Admin on 2018/8/15.
 */

public class BoutiqueLineViewAdapter extends BGARecyclerViewAdapter<GoodsListBean> {

    public BoutiqueLineViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getIs_size() == 1) {
            return R.layout.item_hpboutiqueline1;
        } else {
            return R.layout.item_hpboutiqueline;
        }
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, GoodsListBean listBean) {
        Log.d("position", position + "");

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getMain_picture(), viewHolderHelper.getImageView(R.id.img_boutiQueLine), R.mipmap.placeholderfigure);

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_boutiQueLine, listBean.getProduct_name());

        /**
         * 简介
         */
        if (listBean.getIs_size() == 0 && StringUtils.isEmpty(listBean.getSubtitle())) {
            viewHolderHelper.setVisibility(R.id.tv_synopsis, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_synopsis, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_synopsis, listBean.getSubtitle());
        }


        /**
         * 收藏数
         */
        RatingBar ratingbar = (RatingBar) viewHolderHelper.getView(R.id.ratingbar);
        ratingbar.setRating((float) StringUtils.toDouble(listBean.getRecommended()));
        viewHolderHelper.setText(R.id.tv_collectionNum, listBean.getRecommended());

        /**
         * 关注数
         */
        viewHolderHelper.setVisibility(R.id.tv_attentionNum, View.GONE);

    }

}