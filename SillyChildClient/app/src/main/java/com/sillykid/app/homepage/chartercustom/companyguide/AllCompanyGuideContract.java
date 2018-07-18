package com.sillykid.app.homepage.chartercustom.companyguide;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AllCompanyGuideContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取司导列表信息
         */
        void getAllCompanyGuide(int page, String time, String city, String travelNumber);


        /**
         * 获得车型
         */
        void getCarInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


