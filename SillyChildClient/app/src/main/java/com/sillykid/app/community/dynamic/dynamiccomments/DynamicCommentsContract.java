package com.sillykid.app.community.dynamic.dynamiccomments;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface DynamicCommentsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取帖子评论列表
         */
        void getPostComment(int post_id, int pageno, int type);

        /**
         * 点赞和取消
         */
        void postAddLike(int id);

        /**
         * 添加评论
         */
        void postAddComment(String body, int post_id, int reply_comment_id, int reply_member_id, int type);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


