package com.sillykid.app.community.dynamic.dynamiccomments;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface CommentDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取某一个评论的详细信息
         */
        void getCommentDetails(int id);

        /**
         * 关注或取消关注
         */
        void postAddConcern(int user_id, int type_id);

        /**
         * 点赞和取消
         */
        void postAddLike(int id);

        /**
         * 添加评论
         */
        void postAddComment(String body, int post_id, int reply_comment_id, int reply_member_id, int type);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


