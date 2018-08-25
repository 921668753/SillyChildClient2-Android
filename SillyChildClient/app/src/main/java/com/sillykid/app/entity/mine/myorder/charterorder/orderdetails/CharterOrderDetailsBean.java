package com.sillykid.app.entity.mine.myorder.charterorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class CharterOrderDetailsBean extends BaseResult<CharterOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * category_name : 包车
         * audit_number : 1
         * travel_start_time : 1542211200
         * order_number : SHZ20180824164236428865589
         * pay_amount : 0.15
         * delivery_location : ggg
         * baggage_number : 0
         * pay_status : 1
         * order_amount : 0.15
         * travel_end_time : 1543420800
         * dis_amount : 0.00
         * departure : 东京
         * children_number : 0
         * remarks : 暂无备注
         * status : 1
         */
        private int category;
        private String start_time;
        private String end_time;
        private String phone;
        private int order_id;
        private String service_director;
        private String rong_id;
        private String category_name;
        private int audit_number;
        private String travel_start_time;
        private String order_number;
        private String pay_amount;
        private String delivery_location;
        private int baggage_number;
        private int pay_status;
        private String order_amount;
        private String travel_end_time;
        private String dis_amount;
        private String departure;
        private int children_number;
        private String remarks;
        private int status;

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getService_director() {
            return service_director;
        }

        public void setService_director(String service_director) {
            this.service_director = service_director;
        }

        public String getRong_id() {
            return rong_id;
        }

        public void setRong_id(String rong_id) {
            this.rong_id = rong_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

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

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
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

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getTravel_end_time() {
            return travel_end_time;
        }

        public void setTravel_end_time(String travel_end_time) {
            this.travel_end_time = travel_end_time;
        }

        public String getDis_amount() {
            return dis_amount;
        }

        public void setDis_amount(String dis_amount) {
            this.dis_amount = dis_amount;
        }

        public String getDeparture() {
            return departure;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
