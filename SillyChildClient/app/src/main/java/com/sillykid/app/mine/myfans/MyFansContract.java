package com.sillykid.app.mine.myfans;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface MyFansContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取我的粉丝列表
         */
        void getMyFansList(int page);

        /**
         * 关注或取消关注
         */
        void postAddConcern(int user_id, int type_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


