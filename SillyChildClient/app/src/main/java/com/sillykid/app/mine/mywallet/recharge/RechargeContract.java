package com.sillykid.app.mine.mywallet.recharge;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface RechargeContract {
    interface Presenter extends BasePresenter {
        /**
         * 我的 我的钱包 充值
         */
        void doRecharge(int payWay, double rechargeMoney);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


