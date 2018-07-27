package com.sillykid.app.community.dynamic.dialog;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface ReportBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 举报用户帖子
         */
        void postReport(int post_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


