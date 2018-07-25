package com.sillykid.app.community.dynamic.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface RevertBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 添加评论
         */
        void postAddComment(Context context, String body, int post_id, int reply_comment_id, int reply_member_id, int type);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


