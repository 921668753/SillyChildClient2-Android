package com.sillykid.app.entity.homepage.bythedaycharter;

import com.common.cklibrary.entity.BaseResult;

public class ByTheDayCharterPayOrderBean extends BaseResult<ByTheDayCharterPayOrderBean.DataBean> {


    public class DataBean {
        /**
         * audit_number : 1
         * travel_start_time : 1535019920
         * pay_amount : 0.01
         * order_number : SHZ20180823171629288429598
         * main_picture : http://img.shahaizhi.com/%28null%291530269095
         * title : 东京包车景点简介
         * delivery_location : fff
         * baggage_number : 0
         * contact_number : 17051335257
         * schedule_description : 预定说明
         * price : 0.01
         * product_id : 3
         * contact : hhg
         * days : 1
         * bonus_number : 1
         * travel_end_time : 1535119920
         * price_description : 价格说明
         * product_description : 这个是包车简单的描述信息
         * children_number : 0
         * remarks : 暂无备注
         */

        private int audit_number;
        private String travel_start_time;
        private String pay_amount;
        private String order_number;
        private String main_picture;
        private String title;
        private String delivery_location;
        private int baggage_number;
        private String contact_number;
        private String schedule_description;
        private String price;
        private int product_id;
        private String contact;
        private String days;
        private int bonus_number;
        private String travel_end_time;
        private String price_description;
        private String product_description;
        private int children_number;
        private String remarks;

        public int getAudit_number() {
            return audit_number;
        }

        public void setAudit_number(int audit_number) {
            this.audit_number = audit_number;
        }

        public String getTravel_start_time() {
            return travel_start_time;
        }

        public void setTravel_start_time(String travel_start_time) {
            this.travel_start_time = travel_start_time;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getMain_picture() {
            return main_picture;
        }

        public void setMain_picture(String main_picture) {
            this.main_picture = main_picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDelivery_location() {
            return delivery_location;
        }

        public void setDelivery_location(String delivery_location) {
            this.delivery_location = delivery_location;
        }

        public int getBaggage_number() {
            return baggage_number;
        }

        public void setBaggage_number(int baggage_number) {
            this.baggage_number = baggage_number;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getSchedule_description() {
            return schedule_description;
        }

        public void setSchedule_description(String schedule_description) {
            this.schedule_description = schedule_description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public int getBonus_number() {
            return bonus_number;
        }

        public void setBonus_number(int bonus_number) {
            this.bonus_number = bonus_number;
        }

        public String getTravel_end_time() {
            return travel_end_time;
        }

        public void setTravel_end_time(String travel_end_time) {
            this.travel_end_time = travel_end_time;
        }

        public String getPrice_description() {
            return price_description;
        }

        public void setPrice_description(String price_description) {
            this.price_description = price_description;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public int getChildren_number() {
            return children_number;
        }

        public void setChildren_number(int children_number) {
            this.children_number = children_number;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
