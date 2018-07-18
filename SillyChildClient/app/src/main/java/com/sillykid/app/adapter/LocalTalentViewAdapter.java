package com.sillykid.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.LocalTalentBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 当地达人 适配器
 * Created by Admin on 2017/8/15.
 */

public class LocalTalentViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public LocalTalentViewAdapter(Context context) {
        super(context, R.layout.item_localtalent1);
    }


//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper helper) {
//        super.setItemChildListener(helper);
//        helper.setItemChildClickListener(R.id.img_play);
//        helper.setItemChildClickListener(R.id.img_localTalent);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 图片
         */
        if (StringUtils.isEmpty(listBean.getVideo_url())) {
            viewHolderHelper.setVisibility(R.id.img_play, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.img_play, View.VISIBLE);
        }
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent), R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getName());

        /**
         * 地址
         */
        viewHolderHelper.setText(R.id.tv_address, listBean.getCity());

        /**
         * 类别
         */
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.shopkeepers))|| listBean.getLable() == 4) {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.landlord))|| listBean.getLable() == 3) {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.companyGuide))|| listBean.getLable() == 2) {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.GONE);
        }

        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.user)) || listBean.getLable() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_user, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_user, View.GONE);
        }

        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.platform)) || listBean.getIs_admin() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_platform, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_platform, View.GONE);
        }
        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zan, listBean.getGood_num() + mContext.getString(R.string.zan));

    }

}