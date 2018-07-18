package com.sillykid.app.adapter.main.activities;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;

import com.sillykid.app.entity.main.ActivitiesBean.DataBean.SpecialBean;
import com.sillykid.app.utils.DisplayUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——活动特价商品列表  适配器
 */
public class BargainViewAdapter extends BGAAdapterViewAdapter<SpecialBean> {

    private LayoutParams layoutParams;
    private int minspacing;
    private int maxspacing;

    public BargainViewAdapter(Context context, HorizontalListView listView) {
        super(context, R.layout.item_bargain);
        maxspacing = DisplayUtil.dip2px(context, 15);
        minspacing = DisplayUtil.dip2px(context, 15);
        int width = DisplayUtil.getWidthForScreen(context, 30, 2f, 3f);
        layoutParams = new LayoutParams(width, width / 2);
        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, width / 2 + (int) (context.getResources().getDimension(R.dimen.dimen49)));
        listView.setLayoutParams(layoutParams2);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, SpecialBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_bargain = (LinearLayout) viewHolderHelper.getView(R.id.ll_bargain);
        ll_bargain.requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatus() != null && listBean.getStatus().equals("last")) {
            ll_bargain.setPadding(maxspacing, 0, maxspacing, 0);
        } else {
            if (position == 0) {
                ll_bargain.setPadding(maxspacing, 0, 0, 0);
            } else if (listBean.getStatus() != null && listBean.getStatus().equals("last")) {
                ll_bargain.setPadding(minspacing, 0, maxspacing, 0);
            } else {
                ll_bargain.setPadding(minspacing, 0, 0, 0);
            }
        }
        ll_bargain.setLayoutParams(layoutParams);

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getThumbnail() + "?imageView2/0/w/130", viewHolderHelper.getImageView(R.id.img_good), R.mipmap.placeholderfigure);

        if (StringUtils.isEmpty(listBean.getStore_name())) {
            viewHolderHelper.setVisibility(R.id.img_proprietary, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.img_proprietary, View.VISIBLE);
        }

        viewHolderHelper.setText(R.id.tv_goodName, listBean.getName());

        viewHolderHelper.setText(R.id.tv_specialOffer, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));

        viewHolderHelper.setText(R.id.tv_marketPrice, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(listBean.getMktprice())));
        viewHolderHelper.getTextView(R.id.tv_marketPrice).getPaint().setFlags((Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG));
    }

}