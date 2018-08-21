package com.sillykid.app.entity.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.entity.BaseResult;

public class AirportPickupPayOrderBean extends BaseResult<AirportPickupPayOrderBean.DataBean> {


    public class DataBean {
        /**
         * audit_number : 1
         * flight_number : AA123
         * order_number : SHZ20180821092104560785905
         * main_picture : http://img.shahaizhi.com/%28null%291530269095
         * title : 东京成田机场接机
         * delivery_location : 上海
         * baggage_number : 1
         * contact_number : 17051335257
         * schedule_description : 预定说明
         * price : 2400.00
         * product_id : 1
         * contact : CK
         * price_description : 价格说明
         * subtitle_title : 接机人数<=1    行李件数1
         * children_number : 1
         * remarks : 你好
         * flight_arrival_time : 2018-08-24 09:19
         */

        private int audit_number;
        private String flight_number;
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
        private String price_description;
        private String subtitle_title;
        private int children_number;
        private String remarks;
        private String flight_arrival_time;

        public int getAudit_number() {
            return audit_number;
        }

        public void setAudit_number(int audit_number) {
            this.audit_number = audit_number;
        }

        public String getFlight_number() {
            return flight_number;
        }

        public void setFlight_number(String flight_number) {
            this.flight_number = flight_number;
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

        public String getPrice_description() {
            return price_description;
        }

        public void setPrice_description(String price_description) {
            this.price_description = price_description;
        }

        public String getSubtitle_title() {
            return subtitle_title;
        }

        public void setSubtitle_title(String subtitle_title) {
            this.subtitle_title = subtitle_title;
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

        public String getFlight_arrival_time() {
            return flight_arrival_time;
        }

        public void setFlight_arrival_time(String flight_arrival_time) {
            this.flight_arrival_time = flight_arrival_time;
        }
    }
}
