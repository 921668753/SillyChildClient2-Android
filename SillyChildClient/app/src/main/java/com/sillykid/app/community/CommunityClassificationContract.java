package com.sillykid.app.community;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public interface CommunityClassificationContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取帖子列表
         */
        void getPostList(Context context, String post_title, String nickname, int classification_id, int pageno);

    }

    interface View extends BaseView<Presenter, String> {
    }
}
