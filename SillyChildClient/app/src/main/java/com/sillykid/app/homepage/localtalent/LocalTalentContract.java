package com.sillykid.app.homepage.localtalent;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface LocalTalentContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取当地达人信息
         */
        void getLocalTalent(int page, String city);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


