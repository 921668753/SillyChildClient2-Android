package com.sillykid.app.adapter.homepage.boutiqueline;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.klavor.widget.RatingBar;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.boutiqueline.fragment.BoutiqueLineBean.DataBean.ResultBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 包车定制---精品路线 适配器
 * Created by Admin on 2017/8/15.
 */

public class BoutiqueLineViewAdapter extends BGARecyclerViewAdapter<ResultBean> {

    public BoutiqueLineViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if (!getItem(position).isSize()) {
            return R.layout.item_hpboutiqueline1;
        } else {
            return R.layout.item_hpboutiqueline;
        }
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
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
        if (listBean.isSize() && StringUtils.isEmpty(listBean.getSubtitle())) {
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
//        viewHolderHelper.setText(R.id.tv_attentionNum, listBean.getPraiseNum());

    }


//    public BoutiqueLineViewAdapter(RecyclerView recyclerView) {
//        super(recyclerView, R.layout.item_boutiqueline);
//    }
//
//    @Override
//    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
//
//        ImageView imageView = viewHolderHelper.getImageView(R.id.img_boutiqueLine);
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
//        float width1 = (DensityUtils.getScreenW() - 6 * 3 - 10 * 2) / 2;
//        lp.width = (int) width1;
//        float scale = 0;
//        float tempHeight = 0;
//        if (listBean.getWidth() <= 0 || listBean.getHeight() <= 0) {
//            tempHeight = width1;
//        } else {
//            scale = (width1 + 0f) / listBean.getWidth();
//            tempHeight = listBean.getHeight() * scale;
//        }
//        lp.height = (int) tempHeight;
//        imageView.setLayoutParams(lp);
//        /**
//         * 图片
//         */
//        GlideImageLoader.glideLoader(mContext, listBean.getMain_picture() + "?imageView2/0/w/" + lp.width + "/h/" + lp.height, imageView, 5, (int) lp.width, (int) lp.height, R.mipmap.placeholderfigure);
//
//        /**
//         * 标题
//         */
//        viewHolderHelper.setText(R.id.tv_boutiqueLineTitle, listBean.getProduct_name());
//
//        /**
//         * 简介
//         */
//        viewHolderHelper.setText(R.id.tv_subtitle, listBean.getSubtitle());
//
//
//        /**
//         * 价格
//         */
//        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + listBean.getPrice());
//
//        /**
//         * 评分
//         */
//        viewHolderHelper.setText(R.id.tv_score, listBean.getRecommended());
//
//    }

}