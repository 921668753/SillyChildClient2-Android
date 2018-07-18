package com.sillykid.app.adapter.trip;

import android.content.Context;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.GastronomyBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;


import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 美食 列表 适配器
 * Created by Admin on 2017/9/22.
 */

public class GastronomyViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public GastronomyViewAdapter(Context context) {
        super(context, R.layout.item_gastronomy);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean resultBean) {

        ImageView img_icon = (ImageView) viewHolderHelper.getImageView(R.id.img_icon);
        GlideImageLoader.glideOrdinaryLoader(mContext, resultBean.getImg(), img_icon, R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_name, resultBean.getSubTitle());

        switch (resultBean.getId()) {
            case 0:
                viewHolderHelper.setImageDrawable(R.id.img_starLevel, null);
                break;
            case 1:
                viewHolderHelper.setImageResource(R.id.img_starLevel, R.mipmap.star_one);
                break;
            case 2:
                viewHolderHelper.setImageResource(R.id.img_starLevel, R.mipmap.star_two);
                break;
            case 3:
                viewHolderHelper.setImageResource(R.id.img_starLevel, R.mipmap.star_three);
                break;
            case 4:
                viewHolderHelper.setImageResource(R.id.img_starLevel, R.mipmap.star_four);
                break;
            case 5:
                viewHolderHelper.setImageResource(R.id.img_starLevel, R.mipmap.star_full);
                break;
            default:
                viewHolderHelper.setImageDrawable(R.id.img_starLevel, null);
                break;
        }
        viewHolderHelper.setText(R.id.tv_address, resultBean.getTitle());
        viewHolderHelper.setText(R.id.tv_label, resultBean.getTitle());
        viewHolderHelper.setText(R.id.tv_distance, resultBean.getTitle());
    }
}