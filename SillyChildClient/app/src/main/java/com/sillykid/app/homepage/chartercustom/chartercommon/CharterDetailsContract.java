package com.sillykid.app.homepage.chartercustom.chartercommon;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获得包车产品详情
         */
        void getCharterDetails(int id);

        /**
         * 收藏产品/取消
         */
        void postCollectCharter(String line_id, int type);

        /**
         * 判断是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


