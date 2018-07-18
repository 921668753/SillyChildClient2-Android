package com.sillykid.app.adapter.trip;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.TripBean.ResultBean.ReliableDrvBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 出行--可靠司导 适配器
 * Created by Admin on 2017/8/15.
 */

public class TripCompanyGuideViewAdapter extends BGAAdapterViewAdapter<ReliableDrvBean> {

    public TripCompanyGuideViewAdapter(Context context) {
        super(context, R.layout.item_greathouses);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ReliableDrvBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_greathouses = (LinearLayout) viewHolderHelper.getView(R.id.ll_greathouses);
        ll_greathouses.getParent().requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            ll_greathouses.setPadding(30, 0, 30, 0);
        } else {
            if (position == 0) {
                ll_greathouses.setPadding(30, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                ll_greathouses.setPadding(20, 0, 30, 0);
            } else {
                ll_greathouses.setPadding(20, 0, 0, 0);
            }
        }

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getHead_pic(), (ImageView) viewHolderHelper.getView(R.id.img_companyGuide), R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getSeller_name());


        /**
         *城市
         */
        viewHolderHelper.setText(R.id.tv_city, listBean.getCity());

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

    }

}