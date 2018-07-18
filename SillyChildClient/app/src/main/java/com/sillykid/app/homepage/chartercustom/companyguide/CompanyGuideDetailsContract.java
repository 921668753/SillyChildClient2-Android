package com.sillykid.app.homepage.chartercustom.companyguide;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CompanyGuideDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取司导详情信息
         */
        void getCompanyGuideDetails(String drv_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


