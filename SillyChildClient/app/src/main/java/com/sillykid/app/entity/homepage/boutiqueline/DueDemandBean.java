package com.sillykid.app.entity.homepage.boutiqueline;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DueDemandBean extends BaseResult<DueDemandBean.DataBean> {


    public class DataBean {
        /**
         * service_description : 服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明服务说明
         * passenger_number : 5
         * review_count : 0
         * title : 日本东京
         * baggage_number : 2
         * picture : ["http://img.shahaizhi.com/FunhyNhzfv8WldU1JGnTD3XVebkD","http://img.shahaizhi.com/FrWs95yWS4yejCaO3BJTCasCfj-0","http://img.shahaizhi.com/FmUzSKFwhIY9aJwMiLIvqXRsEQ75","http://img.shahaizhi.com/FqfG2lnm_E-fZ_jWlBeR81bt7lXn","http://img.shahaizhi.com/Fhz-ISZdjqQ617mdjJTyoO6HweAi","http://img.shahaizhi.com/FvYRAD-2HjShs9DvUQ2Kzodo9P51","http://img.shahaizhi.com/Fu72EZOO_V_I2XbFRQfUrkJo88PZ","http://img.shahaizhi.com/FtGnTVeBKP_4YRt4Con8a0IHGGD1","http://img.shahaizhi.com/FkYFLE6amYzTqsaAFHukeOQnAKw7"]
         * review_list : {"currentPageNo":1,"draw":0,"pageSize":2,"result":[],"totalCount":0,"totalPageCount":0}
         * service_note : 服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注服务备注
         * passenger : 5人2行李
         * price : 0.01
         * service : [{"is_show":1,"name":"小费"},{"is_show":1,"name":"燃油费"},{"is_show":1,"name":"过路费"},{"is_show":1,"name":"停车费"},{"is_show":1,"name":"举牌"},{"is_show":0,"name":"车载WIFI"},{"is_show":1,"name":"雨伞"},{"is_show":0,"name":"其他"}]
         * product_id : 4
         * model : 丰田阿尔法
         */

        private String service_description;
        private int passenger_number;
        private int review_count;
        private String title;
        private int baggage_number;
        private ReviewListBean review_list;
        private String service_note;
        private String passenger;
        private String price;
        private int product_id;
        private String model;
        private List<String> picture;
        private List<ServiceBean> service;

        public String getService_description() {
            return service_description;
        }

        public void setService_description(String service_description) {
            this.service_description = service_description;
        }

        public int getPassenger_number() {
            return passenger_number;
        }

        public void setPassenger_number(int passenger_number) {
            this.passenger_number = passenger_number;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
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

        public ReviewListBean getReview_list() {
            return review_list;
        }

        public void setReview_list(ReviewListBean review_list) {
            this.review_list = review_list;
        }

        public String getService_note() {
            return service_note;
        }

        public void setService_note(String service_note) {
            this.service_note = service_note;
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

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public class ReviewListBean {
            /**
             * currentPageNo : 1
             * draw : 0
             * pageSize : 2
             * result : []
             * totalCount : 0
             * totalPageCount : 0
             */

            private int currentPageNo;
            private int draw;
            private int pageSize;
            private int totalCount;
            private int totalPageCount;
            @SerializedName("result")
            private List<?> resultX;

            public int getCurrentPageNo() {
                return currentPageNo;
            }

            public void setCurrentPageNo(int currentPageNo) {
                this.currentPageNo = currentPageNo;
            }

            public int getDraw() {
                return draw;
            }

            public void setDraw(int draw) {
                this.draw = draw;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getTotalPageCount() {
                return totalPageCount;
            }

            public void setTotalPageCount(int totalPageCount) {
                this.totalPageCount = totalPageCount;
            }

            public List<?> getResultX() {
                return resultX;
            }

            public void setResultX(List<?> resultX) {
                this.resultX = resultX;
            }
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
