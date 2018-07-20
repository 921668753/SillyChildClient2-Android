package com.sillykid.app.main;

import android.app.Activity;

import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CommunityContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取分类信息列表
         */
        void getClassificationList();

        /**
         * 获取帖子列表
         */
        void getPostList(String post_title, String nickname, int classification_id, int pageno);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


