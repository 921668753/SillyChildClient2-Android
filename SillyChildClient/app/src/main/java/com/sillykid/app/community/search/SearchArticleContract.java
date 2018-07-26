package com.sillykid.app.community.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface SearchArticleContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取帖子列表
         */
        void getPostList(String name, int pageno);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


