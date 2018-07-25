package com.sillykid.app.community.dynamic.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface ReportBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 举报
         */
        void postAddConcern(Context context, int user_id, int type_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


