package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public interface LineDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 精品线路 - 线路详情
         */
        void getRouteDetail(int product_id);


        /**
         * 给评论点赞
         */
        void postAddCommentLike(int id, int type);
    }

    interface View extends BaseView<Presenter, String> {
    }
}
