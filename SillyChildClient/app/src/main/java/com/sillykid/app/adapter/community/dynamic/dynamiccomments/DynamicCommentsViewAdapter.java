package com.sillykid.app.adapter.community.dynamic.dynamiccomments;


import android.content.Context;
import android.view.View;

import com.sillykid.app.R;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.DynamicCommentsBean.DataBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 动态评论
 */
public class DynamicCommentsViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public DynamicCommentsViewAdapter(Context context) {
        super(context, R.layout.item_dynamicdetails);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.ll_giveLike);
        helper.setItemChildClickListener(R.id.tv_revert);
        helper.setItemChildClickListener(R.id.ll_revertNum);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {

        GlideImageLoader.glideLoader(mContext, model.getFace(), helper.getImageView(R.id.img_avatar), 0, R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_nickName, model.getNickname());

        helper.setText(R.id.tv_content, model.getBody());

        helper.setText(R.id.tv_time, model.getCreate_time());

        if (model.getReplyList() == null || model.getReplyList().size() <= 0) {
            helper.setVisibility(R.id.ll_revert, View.GONE);
        } else {
            helper.setVisibility(R.id.ll_revert, View.VISIBLE);

            GlideImageLoader.glideLoader(mContext, model.getReplyList().get(0).getReply_face(), helper.getImageView(R.id.img_head), 0, R.mipmap.placeholderfigure);

            helper.setText(R.id.tv_nickName1, model.getReplyList().get(0).getNickname());

            helper.setText(R.id.tv_time1, model.getReplyList().get(0).getReply_time());

            helper.setText(R.id.tv_nickName2, model.getReplyList().get(0).getReply_nickname());

            helper.setText(R.id.tv_content1, model.getReplyList().get(0).getReply_body());

            if (model.getReplyList().size() > 1) {
                helper.setVisibility(R.id.ll_revertNum, View.VISIBLE);
                helper.setText(R.id.tv_revertNum, model.getReplyList().size() + "");
            } else {
                helper.setVisibility(R.id.ll_revertNum, View.GONE);
            }
        }
    }
}
