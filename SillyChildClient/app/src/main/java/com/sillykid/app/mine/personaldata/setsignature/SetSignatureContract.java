package com.sillykid.app.mine.personaldata.setsignature;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SetSignatureContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取用户信息
         */
        void setSignature(String personalized_signature);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


