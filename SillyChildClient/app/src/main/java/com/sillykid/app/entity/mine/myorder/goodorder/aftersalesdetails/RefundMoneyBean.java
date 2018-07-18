package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class RefundMoneyBean extends BaseResult<RefundMoneyBean.DataBean> {


    public class DataBean {
        /**
         * order_item_id : 67
         * apply_alltotal : 29
         * alltotal : 29.8
         * num : 1
         */

        private int order_item_id;
        private String apply_alltotal;
        private String alltotal;
        private int num;

        public int getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(int order_item_id) {
            this.order_item_id = order_item_id;
        }

        public String getApply_alltotal() {
            return apply_alltotal;
        }

        public void setApply_alltotal(String apply_alltotal) {
            this.apply_alltotal = apply_alltotal;
        }

        public String getAlltotal() {
            return alltotal;
        }

        public void setAlltotal(String alltotal) {
            this.alltotal = alltotal;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
