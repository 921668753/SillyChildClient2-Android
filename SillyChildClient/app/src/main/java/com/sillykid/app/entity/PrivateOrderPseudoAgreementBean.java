package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/26.
 */

public class PrivateOrderPseudoAgreementBean extends BaseResult<PrivateOrderPseudoAgreementBean.ResultBean> {


    public class ResultBean {
        /**
         * air_id : 129
         * order_sn : 201711030921038827
         * order_type_str : 私人定制订单
         * seller_id : 0
         * total_price : 10.00
         * real_price : 10.00
         * coupon_price : 10.00
         * customer_name : cdfss
         * customer_phone : 137534
         * use_car_adult : 1
         * use_car_children : 0
         * is_pay : 0
         * title : null
         * dest_address : fdx
         * remark :
         * work_at : 2017-11-03 12:19
         */

        private int air_id;
        private String order_sn;
        private String order_type_str;
        private int seller_id;
        private String total_price;
        private String real_price;
        private String coupon_price;
        private String customer_name;
        private String customer_phone;
        private String use_car_adult;
        private String use_car_children;
        private int is_pay;
        private String title;
        private String dest_address;
        private String remark;
        private String work_at;

        public int getAir_id() {
            return air_id;
        }

        public void setAir_id(int air_id) {
            this.air_id = air_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_type_str() {
            return order_type_str;
        }

        public void setOrder_type_str(String order_type_str) {
            this.order_type_str = order_type_str;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getReal_price() {
            return real_price;
        }

        public void setReal_price(String real_price) {
            this.real_price = real_price;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public String getUse_car_adult() {
            return use_car_adult;
        }

        public void setUse_car_adult(String use_car_adult) {
            this.use_car_adult = use_car_adult;
        }

        public String getUse_car_children() {
            return use_car_children;
        }

        public void setUse_car_children(String use_car_children) {
            this.use_car_children = use_car_children;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDest_address() {
            return dest_address;
        }

        public void setDest_address(String dest_address) {
            this.dest_address = dest_address;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWork_at() {
            return work_at;
        }

        public void setWork_at(String work_at) {
            this.work_at = work_at;
        }
    }
}
