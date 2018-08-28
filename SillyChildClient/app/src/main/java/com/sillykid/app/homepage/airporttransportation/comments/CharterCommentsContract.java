package com.sillykid.app.homepage.airporttransportation.comments;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterCommentsContract {


    interface Presenter extends BasePresenter {

        /**
         * 获取评论列表
         */
        void getCommentList(Context context, int product_id, int onlyimage, int page);

        /**
         * 给评论点赞
         */
        void postAddCommentLike(int id, int type);

    }

    interface View extends BaseView<Presenter, String> {

    }

}


