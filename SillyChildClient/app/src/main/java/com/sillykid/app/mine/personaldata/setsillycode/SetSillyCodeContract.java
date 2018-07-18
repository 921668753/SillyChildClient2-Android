package com.sillykid.app.mine.personaldata.setsillycode;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SetSillyCodeContract {
    interface Presenter extends BasePresenter {
        /**
         *更改傻孩子账号
         */
        void changeShzCode(String shz_code);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


