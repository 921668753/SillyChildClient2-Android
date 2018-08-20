package com.sillykid.app.entity.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.entity.BaseResult;

public class AirportPickupPayOrderBean extends BaseResult<AirportPickupPayOrderBean.DataBean> {


    public class DataBean {
        /**
         * audit_number : 1
         * flight_number : AZ123
         * order_number : SHZ20180820181332911389435
         * product_id : 1
         * contact : 土豆丝
         * delivery_location : 啦啦啦
         * children_number : 1
         * baggage_number : 1
         * contact_number : 17051335257
         * remarks :
         * flight_arrival_time : 2018-08-20 20:12
         */

        private int audit_number;
        private String flight_number;
        private String order_number;
        private int product_id;
        private String contact;
        private String delivery_location;
        private int children_number;
        private int baggage_number;
        private String contact_number;
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

        public String getDelivery_location() {
            return delivery_location;
        }

        public void setDelivery_location(String delivery_location) {
            this.delivery_location = delivery_location;
        }

        public int getChildren_number() {
            return children_number;
        }

        public void setChildren_number(int children_number) {
            this.children_number = children_number;
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
