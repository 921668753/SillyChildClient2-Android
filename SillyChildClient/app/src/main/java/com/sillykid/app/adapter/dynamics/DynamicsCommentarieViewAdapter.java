package com.sillykid.app.adapter.dynamics;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.myview.ChildListView;
import com.sillykid.app.R;
import com.sillykid.app.entity.DynamicsCommentariesBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页----动态详情---动态评论-一级
 * Created by Admin on 2017/9/8.
 */

public class DynamicsCommentarieViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public DynamicsCommentarieViewAdapter(Context context) {
        super(context, R.layout.item_dynamicsdetails);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_revert1);
        helper.setItemChildClickListener(R.id.img_favor);
        helper.setItemChildClickListener(R.id.ll_revertNum);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {

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
            DynamicsCommentarie1ViewAdapter dynamicsCommentarie1ViewAdapter = new DynamicsCommentarie1ViewAdapter(mContext);
            lv_replies.setAdapter(dynamicsCommentarie1ViewAdapter);
            dynamicsCommentarie1ViewAdapter.addNewData(model.getReplies());
//            helper.setVisibility(R.id.lv_replies, View.GONE);
//           helper.setText(R.id.tv_replie, model.getReplies().get(0).getOwner().getNickname() + "：" + model.getReplies().get(0).getContent());
         //   helper.setText(R.id.tv_revertNum, model.getReplies().size() + "");
        }
//        LinearLayout ll_revertNum = (LinearLayout) helper.getView(R.id.ll_revertNum);
//        ll_revertNum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                helper.setVisibility(R.id.lv_replies, View.VISIBLE);
////                ChildLiistView lv_replies = (ChildLiistView) helper.getView(R.id.lv_replies);
////                Utility.setListViewHeightBasedOnChildren(lv_replies);
        helper.setVisibility(R.id.tv_replie, View.GONE);
        helper.setVisibility(R.id.ll_revertNum, View.GONE);
//            }
//        });
    }
}
