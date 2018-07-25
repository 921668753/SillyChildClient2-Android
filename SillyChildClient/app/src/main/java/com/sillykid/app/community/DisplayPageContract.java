package com.sillykid.app.community;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface DisplayPageContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取其他用户信息
         */
        void getOtherUserInfo(int member_id);

        /**
         * 获取用户帖子列表
         */
        void getOtherUserPost(int member_id, int pageno);

        /**
         * 关注或取消关注
         */
        void postAddConcern(int user_id, int type_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


