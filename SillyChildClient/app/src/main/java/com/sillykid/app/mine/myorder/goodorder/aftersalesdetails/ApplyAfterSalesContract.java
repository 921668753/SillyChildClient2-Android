package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ApplyAfterSalesContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取售后信息
         */
        void getOrderRefundList();

        /**
         * 获取售后退款金额（由退货数目获取退款金额）
         */
        void getOrderRefundMoney(String order_item_id,String num);

        /**
         * 提交售后信息
         */
        void postOrderRefund(String orderid, String typeCode, String reasonCode, String reasonDetail, int num);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


