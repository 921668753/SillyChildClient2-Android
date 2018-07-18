package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/17.
 */

public class ConfirmOrderBean extends BaseResult<ConfirmOrderBean.ResultBean> {


    public class ResultBean {
        /**
         * real_price : 99.00
         * discount_id : 0
         * air_id : 59
         */

        private String real_price;
        private String real_price_fmt;
        private String discount_id;
        private String air_id;

        public String getReal_price() {
            return real_price;
        }

        public void setReal_price(String real_price) {
            this.real_price = real_price;
        }

        public String getReal_price_fmt() {
            return real_price_fmt;
        }

        public void setReal_price_fmt(String real_price_fmt) {
            this.real_price_fmt = real_price_fmt;
        }

        public String getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(String discount_id) {
            this.discount_id = discount_id;
        }

        public String getAir_id() {
            return air_id;
        }

        public void setAir_id(String air_id) {
            this.air_id = air_id;
        }
    }
}
