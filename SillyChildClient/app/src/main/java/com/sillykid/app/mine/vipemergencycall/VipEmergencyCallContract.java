package com.sillykid.app.mine.vipemergencycall;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface VipEmergencyCallContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取电话信息
         */
        void getVIPServicePhone();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


