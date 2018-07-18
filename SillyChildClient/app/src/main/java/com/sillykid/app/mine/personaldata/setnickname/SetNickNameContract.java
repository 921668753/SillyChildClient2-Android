package com.sillykid.app.mine.personaldata.setnickname;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SetNickNameContract {
    interface Presenter extends BasePresenter {
        /**
         * 更改用户信息
         */
        void saveInfo(String newnickname);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


