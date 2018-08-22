package com.sillykid.app.entity.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.entity.BaseResult;

public class CreateTravelOrderBean extends BaseResult<CreateTravelOrderBean.DataBean> {


    public class DataBean {
        /**
         * start_time : 1534917573
         * pay_amount : 0.01
         * end_time : 1534920573
         * order_id : 67
         */

        private String start_time;
        private String pay_amount;
        private String end_time;
        private int order_id;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}
