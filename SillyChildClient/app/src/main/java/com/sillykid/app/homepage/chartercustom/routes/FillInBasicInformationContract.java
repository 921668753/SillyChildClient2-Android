package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface FillInBasicInformationContract {
    interface Presenter extends BasePresenter {
        /**
         * 填写基本信息
         */
        void postQuickReservation(String name, String contactWay, String passportNum, String idNumber,
                                  String adult_num, String child_num, String origin, String whereToPlay,
                                  String destination, String serviceDate, String bags, String bags1, String bags2, String bags3, String remark);


        /**
         * 判断是否登录
         */
        void isLogin();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


