package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class AfterSalesDetailsBean extends BaseResult<AfterSalesDetailsBean.DataBean> {


    public class DataBean {
        /**
         * tradestatus : 0
         * alltotal_pay :
         * store_name : food的商城
         * remark : 退款
         * apply_alltotal : 29
         * reason : 不喜欢
         * ordersn : DD152811705476-1
         * order_create_time : 1528117054
         * order_item_id : 67
         * num : 1
         * name : [送杯勺]福事多蜂蜜柚子茶柠檬茶1kg冲饮品韩国风果味茶水果
         */

        private int tradestatus;
        private String alltotal_pay;
        private String store_name;
        private String remark;
        private String apply_alltotal;
        private String reason;
        private String ordersn;
        private String order_create_time;
        private int order_item_id;
        private int num;
        private String name;

        public int getTradestatus() {
            return tradestatus;
        }

        public void setTradestatus(int tradestatus) {
            this.tradestatus = tradestatus;
        }

        public String getAlltotal_pay() {
            return alltotal_pay;
        }

        public void setAlltotal_pay(String alltotal_pay) {
            this.alltotal_pay = alltotal_pay;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getApply_alltotal() {
            return apply_alltotal;
        }

        public void setApply_alltotal(String apply_alltotal) {
            this.apply_alltotal = apply_alltotal;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getOrder_create_time() {
            return order_create_time;
        }

        public void setOrder_create_time(String order_create_time) {
            this.order_create_time = order_create_time;
        }

        public int getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(int order_item_id) {
            this.order_item_id = order_item_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
