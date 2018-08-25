package com.sillykid.app.entity.mine.myorder.charterorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

public class PrivateCustomOrderDetailsBean extends BaseResult<PrivateCustomOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * order_id : 154
         * member_id : 0
         * status : 1
         * category : 4
         * product_set_name : 私人定制
         * pay_status : 1
         * order_amount : 0.01
         * pay_amount : 0.01
         * dis_amount : 0.00
         * create_time : 2018-08-25 15:37:54
         * id : 1
         * order_number : SHZ20180825153754648084000
         * travel_time : 1538379447
         * destination : 45
         * play_days : 11
         * travel_preference : 出行1
         * repast_preference : 餐饮3
         * stay_preference : 住宿3
         * remark :
         * phone : 18550875927
         * service_director : 傻孩志
         * rong_id : G14
         */

        private int order_id;
        private int member_id;
        private String start_time;
        private String end_time;
        private int status;
        private int category;
        private String product_set_name;
        private int pay_status;
        private String order_amount;
        private String pay_amount;
        private String dis_amount;
        private String create_time;
        private int id;
        private String order_number;
        private String travel_time;
        private String destination;
        private int play_days;
        private String travel_preference;
        private String repast_preference;
        private String stay_preference;
        private String remark;
        private String phone;
        private String service_director;
        private String rong_id;

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

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getProduct_set_name() {
            return product_set_name;
        }

        public void setProduct_set_name(String product_set_name) {
            this.product_set_name = product_set_name;
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

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getDis_amount() {
            return dis_amount;
        }

        public void setDis_amount(String dis_amount) {
            this.dis_amount = dis_amount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getTravel_time() {
            return travel_time;
        }

        public void setTravel_time(String travel_time) {
            this.travel_time = travel_time;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public int getPlay_days() {
            return play_days;
        }

        public void setPlay_days(int play_days) {
            this.play_days = play_days;
        }

        public String getTravel_preference() {
            return travel_preference;
        }

        public void setTravel_preference(String travel_preference) {
            this.travel_preference = travel_preference;
        }

        public String getRepast_preference() {
            return repast_preference;
        }

        public void setRepast_preference(String repast_preference) {
            this.repast_preference = repast_preference;
        }

        public String getStay_preference() {
            return stay_preference;
        }

        public void setStay_preference(String stay_preference) {
            this.stay_preference = stay_preference;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
    }
}
