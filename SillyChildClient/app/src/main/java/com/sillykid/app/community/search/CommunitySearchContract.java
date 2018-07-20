package com.sillykid.app.community.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CommunitySearchContract {

    interface Presenter extends BasePresenter {

        /**
         * 搜索发现
         */
        void getStrategy();

    }

    interface View extends BaseView<Presenter, String> {
    }

}

