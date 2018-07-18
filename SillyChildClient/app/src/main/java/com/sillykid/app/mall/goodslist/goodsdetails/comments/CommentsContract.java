package com.sillykid.app.mall.goodslist.goodsdetails.comments;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CommentsContract {


    interface Presenter extends BasePresenter {

        /**
         * 获取评论列表
         */
        void getCommentList(Context context, int goodsid, int onlyimage, int page);

    }

    interface View extends BaseView<Presenter, String> {

    }

}


