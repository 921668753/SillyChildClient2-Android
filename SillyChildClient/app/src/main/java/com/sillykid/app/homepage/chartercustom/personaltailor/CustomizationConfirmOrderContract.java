package com.sillykid.app.homepage.chartercustom.personaltailor;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Admin on 2017/11/15.
 */

public class CustomizationConfirmOrderContract {

    interface Presenter extends BasePresenter {
        /**
         * 提交私人订制
         */
        void postSaveUserPrivate(String air_id, String customer_name, String customer_phone, String user_passport, String user_identity, String bags, String bags1, String bags2, String bags3, String remark);

//        /**
//         * 获得退订政策|费用说明done
//         */
//        void getUnsubscribeCost(int type);


    }

    interface View extends BaseView<CustomizationConfirmOrderContract.Presenter, String> {
    }
}
