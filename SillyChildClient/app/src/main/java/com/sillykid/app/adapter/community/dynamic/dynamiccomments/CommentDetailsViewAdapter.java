package com.sillykid.app.adapter.community.dynamic.dynamiccomments;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.CommentDetailsBean.DataBean.ReplyListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 单条动态评论详情
 */
public class CommentDetailsViewAdapter extends BGAAdapterViewAdapter<ReplyListBean> {

    public CommentDetailsViewAdapter(Context context) {
        super(context, R.layout.item_dynamicsdetailsreplie);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_nickName1);
        helper.setItemChildClickListener(R.id.tv_nickName2);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ReplyListBean model) {

        GlideImageLoader.glideLoader(mContext, model.getReply_face(), helper.getImageView(R.id.img_head), 0, R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_nickName1, model.getNickname());

        helper.setText(R.id.tv_content1, model.getReply_body());

        helper.setText(R.id.tv_nickName2, model.getReply_nickname());

        helper.setText(R.id.tv_time1, model.getReply_time());

    }
}
