package com.sillykid.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.AllCompanyGuideBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 全部司导 适配器
 * Created by Admin on 2017/8/15.
 */

public class AllCompanyGuideViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public AllCompanyGuideViewAdapter(Context context) {
        super(context, R.layout.item_localguide);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getHead_pic(), (ImageView) viewHolderHelper.getView(R.id.img_companyGuide), R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getNickname());

        /**
         * 工号
         */
        viewHolderHelper.setText(R.id.tv_jobNumber, listBean.getDrv_code());

        /**
         *城市
         */
        viewHolderHelper.setText(R.id.tv_address, listBean.getCountry() + "•" + listBean.getCity());

        if (!StringUtils.isEmpty(listBean.getLine())) {
            viewHolderHelper.setText(R.id.tv_title, listBean.getLine());
        } else {
            viewHolderHelper.setText(R.id.tv_title, "");
        }

        /**
         * 服务星级
         */
        switch (StringUtils.toInt(listBean.getPlat_start(), 0)) {
            case 0:
                viewHolderHelper.setImageDrawable(R.id.img_service, null);
                break;
            case 1:
                viewHolderHelper.setImageResource(R.id.img_service, R.mipmap.star_one);
                break;
            case 2:
                viewHolderHelper.setImageResource(R.id.img_service, R.mipmap.star_two);
                break;
            case 3:
                viewHolderHelper.setImageResource(R.id.img_service, R.mipmap.star_three);
                break;
            case 4:
                viewHolderHelper.setImageResource(R.id.img_service, R.mipmap.star_four);
                break;
            case 5:
                viewHolderHelper.setImageResource(R.id.img_service, R.mipmap.star_full);
                break;
            default:
                viewHolderHelper.setImageDrawable(R.id.img_service, null);
                break;
        }
        /**
         * 评价星级
         */
        switch (StringUtils.toInt(listBean.getStar(), 0)) {
            case 0:
                viewHolderHelper.setImageDrawable(R.id.img_evaluation, null);
                break;
            case 1:
                viewHolderHelper.setImageResource(R.id.img_evaluation, R.mipmap.heart_one);
                break;
            case 2:
                viewHolderHelper.setImageResource(R.id.img_evaluation, R.mipmap.heart_two);
                break;
            case 3:
                viewHolderHelper.setImageResource(R.id.img_evaluation, R.mipmap.heart_three);
                break;
            case 4:
                viewHolderHelper.setImageResource(R.id.img_evaluation, R.mipmap.heart_four);
                break;
            case 5:
                viewHolderHelper.setImageResource(R.id.img_evaluation, R.mipmap.heart_full);
                break;
            default:
                viewHolderHelper.setImageDrawable(R.id.img_evaluation, null);
                break;
        }

    }

}