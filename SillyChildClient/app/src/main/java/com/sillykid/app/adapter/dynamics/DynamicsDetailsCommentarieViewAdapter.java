package com.sillykid.app.adapter.dynamics;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.myview.ChildListView;
import com.sillykid.app.R;
import com.sillykid.app.entity.DynamicsDetailsBean.ResultBean.CommentsBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页----动态详情---评论-一级
 * Created by Admin on 2017/9/8.
 */

public class DynamicsDetailsCommentarieViewAdapter extends BGAAdapterViewAdapter<CommentsBean> {


    private boolean first = false;
    private boolean two = false;

    public DynamicsDetailsCommentarieViewAdapter(Context context, boolean first, boolean two) {
        super(context, R.layout.item_dynamicsdetails);
        this.first = first;
        this.two = two;

    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_revert1);
        helper.setItemChildClickListener(R.id.img_favor);
        helper.setItemChildClickListener(R.id.ll_revertNum);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, CommentsBean model) {

        /**
         * 头像
         */
        GlideImageLoader.glideLoader(mContext, model.getOwner().getAvatar(), (ImageView) helper.getView(R.id.img_avatar), 0, R.mipmap.avatar_default);

        /**
         * 姓名
         */
        helper.setText(R.id.tv_name, model.getOwner().getNickname());

        /**
         * 内容
         */
        helper.setText(R.id.tv_content, model.getContent());

        /**
         * 时间
         */
        helper.setText(R.id.tv_time, model.getCreateTimeFmt());

        if (model.getIsPraise() == 1) {
            helper.setImageResource(R.id.img_favor, R.mipmap.btn_favor_pressed);
        } else {
            helper.setImageResource(R.id.img_favor, R.mipmap.btn_favor_normal);
        }

        /**
         * 回复评论
         */
        helper.setVisibility(R.id.ll_replies, View.GONE);
        if (model.getReplies() != null && model.getReplies().size() > 0) {
            helper.setVisibility(R.id.ll_replies, View.VISIBLE);
            ChildListView lv_replies = (ChildListView) helper.getView(R.id.lv_replies);
            DynamicsDetailsCommentarie1ViewAdapter dynamicsDetailsCommentarie1ViewAdapter = new DynamicsDetailsCommentarie1ViewAdapter(mContext);
            lv_replies.setAdapter(dynamicsDetailsCommentarie1ViewAdapter);
            dynamicsDetailsCommentarie1ViewAdapter.addNewData(model.getReplies());
            if (position == 0 && first || position == 1 && two) {
                helper.setVisibility(R.id.lv_replies, View.VISIBLE);
                helper.setVisibility(R.id.ll_revertNum, View.GONE);
                helper.setVisibility(R.id.ll_dynamicsdetailsreplies, View.GONE);
            } else {
                helper.setVisibility(R.id.lv_replies, View.GONE);
                helper.setVisibility(R.id.ll_dynamicsdetailsreplies, View.VISIBLE);
                helper.setVisibility(R.id.ll_revertNum, View.VISIBLE);
                helper.setText(R.id.tv_replie, model.getReplies().get(0).getOwner().getNickname() + "：" + model.getReplies().get(0).getContent());
                helper.setText(R.id.tv_revertNum, model.getReplies().size() + "");
            }
        }
//        LinearLayout ll_revertNum = (LinearLayout) helper.getView(R.id.ll_revertNum);
//        LinearLayout ll_replies = (LinearLayout) helper.getView(R.id.ll_replies);
//        LinearLayout ll_dynamicsdetailsreplies = (LinearLayout) helper.getView(R.id.ll_dynamicsdetailsreplies);
//        ll_revertNum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                helper.setVisibility(R.id.lv_replies, View.VISIBLE);
//                ll_replies.removeView(ll_dynamicsdetailsreplies);
//                ll_replies.removeView(ll_revertNum);
////                ((LinearLayout) ll_revertNum.getParent()).removeView(tv_replie);
////                ((LinearLayout) ll_revertNum.getParent()).removeView(ll_revertNum);
////                helper.setVisibility(R.id.ll_revertNum, View.GONE);
//            }
//        });
    }
}
