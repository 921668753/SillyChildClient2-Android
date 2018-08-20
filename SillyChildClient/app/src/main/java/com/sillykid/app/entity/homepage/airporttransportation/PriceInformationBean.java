package com.sillykid.app.entity.homepage.airporttransportation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;


public class PriceInformationBean extends BaseResult<PriceInformationBean.DataBean> {


    public class DataBean {
        /**
         * service_description : 服务说明
         * passenger : 5人2行李
         * price : 2400
         * passenger_number : 5
         * service : [{"is_show":1,"name":"小费"},{"is_show":1,"name":"燃油费"},{"is_show":1,"name":"过路费"},{"is_show":1,"name":"停车费"},{"is_show":1,"name":"举牌"},{"is_show":1,"name":"车载WIFI"},{"is_show":1,"name":"雨伞"},{"is_show":1,"name":"其他"}]
         * product_id : 1
         * model : 丰田阿尔法
         * title : 日本东京成田机场接机
         * baggage_number : 2
         * picture : ["http://img.shahaizhi.com/FunhyNhzfv8WldU1JGnTD3XVebkD","http://img.shahaizhi.com/FrWs95yWS4yejCaO3BJTCasCfj-0","http://img.shahaizhi.com/FmUzSKFwhIY9aJwMiLIvqXRsEQ75","http://img.shahaizhi.com/FqfG2lnm_E-fZ_jWlBeR81bt7lXn","http://img.shahaizhi.com/Fhz-ISZdjqQ617mdjJTyoO6HweAi","http://img.shahaizhi.com/FvYRAD-2HjShs9DvUQ2Kzodo9P51","http://img.shahaizhi.com/Fu72EZOO_V_I2XbFRQfUrkJo88PZ","http://img.shahaizhi.com/FtGnTVeBKP_4YRt4Con8a0IHGGD1","http://img.shahaizhi.com/FkYFLE6amYzTqsaAFHukeOQnAKw7"]
         * service_note : 服务备注
         */

        private String service_description;
        private String passenger;
        private String price;
        private int passenger_number;
        private int product_id;
        private String model;
        private String title;
        private int baggage_number;
        private String service_note;
        private List<ServiceBean> service;
        private List<String> picture;

        public String getService_description() {
            return service_description;
        }

        public void setService_description(String service_description) {
            this.service_description = service_description;
        }

        public String getPassenger() {
            return passenger;
        }

        public void setPassenger(String passenger) {
            this.passenger = passenger;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPassenger_number() {
            return passenger_number;
        }

        public void setPassenger_number(int passenger_number) {
            this.passenger_number = passenger_number;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getBaggage_number() {
            return baggage_number;
        }

        public void setBaggage_number(int baggage_number) {
            this.baggage_number = baggage_number;
        }

        public String getService_note() {
            return service_note;
        }

        public void setService_note(String service_note) {
            this.service_note = service_note;
        }

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }

        public class ServiceBean {
            /**
             * is_show : 1
             * name : 小费
             */

            private int is_show;
            private String name;

            public int getIs_show() {
                return is_show;
            }

            public void setIs_show(int is_show) {
                this.is_show = is_show;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
