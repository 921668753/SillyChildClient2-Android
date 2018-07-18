package com.sillykid.app.adapter.dynamics;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.DynamicsDetailsBean.ResultBean.CommentsBean.RepliesBeanX;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页----动态详情---评论二级
 * Created by Admin on 2017/9/8.
 */

public class DynamicsDetailsCommentarie1ViewAdapter extends BGAAdapterViewAdapter<RepliesBeanX> {


    public DynamicsDetailsCommentarie1ViewAdapter(Context context) {
        super(context, R.layout.item_dynamicsdetailsreplies);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, RepliesBeanX model) {

        /**
         * 昵称
         */
        helper.setText(R.id.tv_replie, model.getOwner().getNickname() + "：" + model.getContent());
//        /**
//         * 内容
//         */
//        helper.setText(R.id.tv_changemoney, model.getChangeMoney());
    }
}
