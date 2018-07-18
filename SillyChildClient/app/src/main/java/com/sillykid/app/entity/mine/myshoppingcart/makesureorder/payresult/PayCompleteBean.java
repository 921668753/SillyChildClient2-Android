package com.sillykid.app.entity.mine.myshoppingcart.makesureorder.payresult;

import com.common.cklibrary.entity.BaseResult;

public class PayCompleteBean  extends BaseResult<PayCompleteBean.DataBean> {

    public class DataBean {
        /**
         * balance : 100000.0
         * last_time : 1528100169
         * order_id : 171
         */

        private String sn;
        private String ship_name;
        private String ship_mobile;
        private String ship_area;
        private String ship_addr;
        private String need_pay_money;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getShip_name() {
            return ship_name;
        }

        public void setShip_name(String ship_name) {
            this.ship_name = ship_name;
        }

        public String getShip_mobile() {
            return ship_mobile;
        }

        public void setShip_mobile(String ship_mobile) {
            this.ship_mobile = ship_mobile;
        }

        public String getShip_area() {
            return ship_area;
        }

        public void setShip_area(String ship_area) {
            this.ship_area = ship_area;
        }

        public String getShip_addr() {
            return ship_addr;
        }

        public void setShip_addr(String ship_addr) {
            this.ship_addr = ship_addr;
        }

        public String getNeed_pay_money() {
            return need_pay_money;
        }

        public void setNeed_pay_money(String need_pay_money) {
            this.need_pay_money = need_pay_money;
        }
    }

}
