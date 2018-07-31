package com.sillykid.app.homepage.hotvideo;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface VideoDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取视频详情
         */
        void getVideoDetails(int id);

        /**
         * 关注或取消关注
         */
        void postAddConcern(int user_id, int type_id);

        /**
         * 取消收藏
         */
        void postUnfavorite(int id);

        /**
         * 收藏
         */
        void postAddFavorite(int id);

        /**
         * 点赞和取消
         */
        void postAddLike(int id, int type);

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


