package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface MyDynamicContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取用户发布的帖子
         */
        void getUserPost(int page);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


