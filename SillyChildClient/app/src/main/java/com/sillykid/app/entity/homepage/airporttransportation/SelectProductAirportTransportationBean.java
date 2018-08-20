package com.sillykid.app.entity.homepage.airporttransportation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;


public class SelectProductAirportTransportationBean extends BaseResult<List<SelectProductAirportTransportationBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * main_picture : http://img.shahaizhi.com/%28null%291530269095
         * model : 丰田阿尔法
         * order_number : 暂未预定
         * passenger_number : 最多可乘坐6人
         * product_name : 成田机场接机
         */

        private int id;
        private String main_picture;
        private String model;
        private String order_number;
        private String passenger_number;
        private String product_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain_picture() {
            return main_picture;
        }

        public void setMain_picture(String main_picture) {
            this.main_picture = main_picture;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getPassenger_number() {
            return passenger_number;
        }

        public void setPassenger_number(String passenger_number) {
            this.passenger_number = passenger_number;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }
    }
}
