package com.sillykid.app.entity.mine.mywallet.withdrawal;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class WithdrawalCompleteBean extends BaseResult<WithdrawalCompleteBean.DataBean> {


    public class DataBean {
        /**
         * time : 2018-06-08 15:11:52
         */
        private String fee_amount;
        private String time;

        public String getFee_amount() {
            return fee_amount;
        }

        public void setFee_amount(String fee_amount) {
            this.fee_amount = fee_amount;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
