package com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog;

import com.common.cklibrary.entity.BaseResult;

public class SpecificationsBean extends BaseResult<SpecificationsBean.DataBean> {


    public class DataBean {
        /**
         * goods_id : 135
         * product_id : 548
         * sn : 00031-2
         * enable_store : 100
         * price : 48
         * specs : 黄色、XL
         * name : Bzbz新款平底防滑沙滩鞋厚底凉拖鞋女夏时尚韩版外穿
         */

        private int goods_id;
        private int product_id;
        private String sn;
        private String enable_store;
        private String price;
        private String specs;
        private String name;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getEnable_store() {
            return enable_store;
        }

        public void setEnable_store(String enable_store) {
            this.enable_store = enable_store;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
