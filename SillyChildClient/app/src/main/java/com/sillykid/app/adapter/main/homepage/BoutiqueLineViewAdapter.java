package com.sillykid.app.adapter.main.homepage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.ResultBean.ActionBean.HotBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 精品线路 适配器
 * 描述:RecyclerView 多 item 类型适配器
 * Created by Admin on 2018/8/15.
 */

public class BoutiqueLineViewAdapter extends BGARecyclerViewAdapter<HotBean> {

    public BoutiqueLineViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1) {
            return R.layout.item_hpboutiqueline1;
        } else {
            return R.layout.item_hpboutiqueline;
        }
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, HotBean listBean) {
        Log.d("position", position + "");

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), viewHolderHelper.getImageView(R.id.img_boutiQueLine), R.mipmap.placeholderfigure);


        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_boutiQueLine, listBean.getCity());

        /**
         * 简介
         */
        viewHolderHelper.setText(R.id.tv_synopsis, listBean.getCity());

        /**
         * 收藏数
         */
        viewHolderHelper.setText(R.id.tv_collectionNum, listBean.getPraiseNum());

        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_attentionNum, listBean.getPraiseNum());

    }

}