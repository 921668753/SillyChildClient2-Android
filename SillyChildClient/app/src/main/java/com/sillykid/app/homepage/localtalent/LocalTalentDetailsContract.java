package com.sillykid.app.homepage.localtalent;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;


/**
 * Created by ruitu on 2016/9/24.
 */
public interface LocalTalentDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取当地达人详情信息
         */
        void getLocalTalentDetails(String talent_id);

        /**
         * 当地达人点赞
         */
        void postPraise(String talent_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


