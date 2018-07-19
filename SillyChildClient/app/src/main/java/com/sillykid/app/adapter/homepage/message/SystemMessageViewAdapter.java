package com.sillykid.app.adapter.homepage.message;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.message.SystemMessageBean.DataBean;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 系统消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class SystemMessageViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public SystemMessageViewAdapter(Context context) {
        super(context, R.layout.item_systemmessage);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         * 头像
         */
        GlideImageLoader.glideLoader(mContext, listBean.getTitle_img(), viewHolderHelper.getImageView(R.id.img_head), 0, R.mipmap.avatar);

        /**
         * 未读消息数
         */
        if (listBean.getNum() <= 0) {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_messageTag, String.valueOf(listBean.getNum()));
        }

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getNews_title());

        /**
         * 内容
         */
        viewHolderHelper.setText(R.id.tv_content, listBean.getNews_text());

        /**
         * 时间
         */
        viewHolderHelper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(listBean.getLastTime()), "yyyy-MM-dd HH:mm"));

    }

}