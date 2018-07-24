package com.sillykid.app.mine.myfocus;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface FocusContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取我关注的用户列表
         */
        void getMyConcernList(int page, int type_id);

        /**
         * 关注或取消关注
         */
        void postAddConcern(int user_id, int type_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


