package com.sillykid.app.adapter.main.activity;

import android.content.Context;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;

import com.sillykid.app.entity.main.ActivityBean.DataBean.ActivityListBean;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class ActivityViewAdapter extends BGAAdapterViewAdapter<ActivityListBean> {

    public ActivityViewAdapter(Context context) {
        super(context, R.layout.item_popularactivities);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ActivityListBean model) {

        GlideImageLoader.glideOrdinaryLoader(mContext, model.getMain_picture(), helper.getImageView(R.id.img_picture), R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_title, model.getTitle());

        helper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy" + mContext.getString(R.string.year) + "MM" +
                mContext.getString(R.string.month) + "dd" + mContext.getString(R.string.day) + "HH") + mContext.getString(R.string.hour) + "--" + DataUtil.formatData(StringUtils.toLong(model.getEnd_time()),
                "yyyy" + mContext.getString(R.string.year) + "MM" + mContext.getString(R.string.month) + "dd" + mContext.getString(R.string.day) + "HH") + mContext.getString(R.string.hour));

        helper.setText(R.id.tv_content, model.getSubtitle());
    }
}
