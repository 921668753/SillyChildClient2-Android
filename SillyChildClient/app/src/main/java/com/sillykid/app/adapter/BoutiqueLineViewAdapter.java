package com.sillykid.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.PackLineBean.ResultBean.LineBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 包车定制---精品路线 适配器
 * Created by Admin on 2017/8/15.
 */

public class BoutiqueLineViewAdapter extends BGAAdapterViewAdapter<LineBean> {

    public BoutiqueLineViewAdapter(Context context) {
        super(context, R.layout.item_localguide1);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, LineBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_companyGuide), R.mipmap.placeholderfigure);

        if (listBean.getIs_admin() == 0 && listBean.getDriver() != null) {
            viewHolderHelper.setVisibility(R.id.tv_jobNumber1, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_jobNumber, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.img_location, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_address, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.img_service, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_hide, View.VISIBLE);
            /**
             * 姓名
             */
            viewHolderHelper.setText(R.id.tv_name, listBean.getDriver().getNickname());

            /**
             * 工号
             */
            viewHolderHelper.setText(R.id.tv_jobNumber, listBean.getDriver().getDrv_code());

            /**
             *城市
             */
            viewHolderHelper.setText(R.id.tv_address, listBean.getDriver().getCountry_name() + "•" + listBean.getDriver().getCity_name());

            /**
             *城市
             */
            if (!StringUtils.isEmpty(listBean.getLine_title())) {
                viewHolderHelper.setText(R.id.tv_title, listBean.getLine_title());
            } else {
                viewHolderHelper.setText(R.id.tv_title, "");
            }

            /**
             * 服务星级
             */
            switch (StringUtils.toInt(listBean.getDriver().getPlat_start(), 0)) {
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

        } else {

            /**
             * 标题
             */
            viewHolderHelper.setText(R.id.tv_name, listBean.getLine_title());
            viewHolderHelper.setVisibility(R.id.tv_jobNumber1, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_jobNumber, View.GONE);
            viewHolderHelper.setVisibility(R.id.img_location, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_address, View.GONE);
            viewHolderHelper.setVisibility(R.id.img_service, View.GONE);

            /**
             * 66人出游
             */
            viewHolderHelper.setText(R.id.tv_title, listBean.getLine_buy_num() + mContext.getString(R.string.peopleTravel));
            viewHolderHelper.setVisibility(R.id.tv_hide, View.GONE);

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

}