package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/11/17.
 */

public class ScheduleDetailsBean extends BaseResult<ScheduleDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * air_id : 292
         * total_price : 100.00
         * customer_name : ck
         * customer_phone : 17051335257
         * tour_time : 1511075820
         * work_address : 苏州
         * dest_address : 东京
         * tour_days : 2
         * use_car_adult : 1
         * use_car_children : 0
         * req_car_level : 0
         * req_car_seat_num : 2
         * cost_statement :  我是私人定制费用说明赶快阅读一下</p>
         * cost_compensation : cover_img_k###    宽松</span>的退订政策</span></p>        提前7天退订，退还您85%的费用</span></p>  </span>      提前3天以外7天以内退订，退还您35%的费用</span></p>        提前3天以内退订，费用不予退还</span></p>  </p>      </span> 扣除的费用用于商家补偿和平台服务费</span></span></p>
         */

        private String air_id;
        private String total_price;
        private String customer_name;
        private String customer_phone;
        private String tour_time;
        private String work_address;
        private String dest_address;
        private String tour_days;
        private String use_car_adult;
        private String use_car_children;
        private String req_car_level;
        private String req_car_seat_num;
        private String cost_statement;
        private String cost_compensation;

        public String getAir_id() {
            return air_id;
        }

        public void setAir_id(String air_id) {
            this.air_id = air_id;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
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

        public String getTour_time() {
            return tour_time;
        }

        public void setTour_time(String tour_time) {
            this.tour_time = tour_time;
        }

        public String getWork_address() {
            return work_address;
        }

        public void setWork_address(String work_address) {
            this.work_address = work_address;
        }

        public String getDest_address() {
            return dest_address;
        }

        public void setDest_address(String dest_address) {
            this.dest_address = dest_address;
        }

        public String getTour_days() {
            return tour_days;
        }

        public void setTour_days(String tour_days) {
            this.tour_days = tour_days;
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

        public String getReq_car_level() {
            return req_car_level;
        }

        public void setReq_car_level(String req_car_level) {
            this.req_car_level = req_car_level;
        }

        public String getReq_car_seat_num() {
            return req_car_seat_num;
        }

        public void setReq_car_seat_num(String req_car_seat_num) {
            this.req_car_seat_num = req_car_seat_num;
        }

        public String getCost_statement() {
            return cost_statement;
        }

        public void setCost_statement(String cost_statement) {
            this.cost_statement = cost_statement;
        }

        public String getCost_compensation() {
            return cost_compensation;
        }

        public void setCost_compensation(String cost_compensation) {
            this.cost_compensation = cost_compensation;
        }
    }
}
