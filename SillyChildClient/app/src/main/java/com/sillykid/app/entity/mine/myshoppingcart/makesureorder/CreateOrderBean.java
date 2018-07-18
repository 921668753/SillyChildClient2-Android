package com.sillykid.app.entity.mine.myshoppingcart.makesureorder;

import com.common.cklibrary.entity.BaseResult;

public class CreateOrderBean extends BaseResult<CreateOrderBean.DataBean> {


    public class DataBean {
        /**
         * balance : 100000.0
         * last_time : 1528100169
         * order_id : 171
         */

        private String balance;
        private String last_time;
        private String now_time;
        private String order_id;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
