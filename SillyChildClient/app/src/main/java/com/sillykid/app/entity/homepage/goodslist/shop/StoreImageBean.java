package com.sillykid.app.entity.homepage.goodslist.shop;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class StoreImageBean extends BaseResult<List<StoreImageBean.DataBean>> {


    public class DataBean {
        /**
         * silde_id : 6
         * store_id : 2
         * silde_url : “”
         * img : fs:/images/s_side.jpg
         * sildeImg : “”
         */

        private int silde_id;
        private int store_id;
        private String silde_url;
        private String img;
        private String sildeImg;

        public int getSilde_id() {
            return silde_id;
        }

        public void setSilde_id(int silde_id) {
            this.silde_id = silde_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getSilde_url() {
            return silde_url;
        }

        public void setSilde_url(String silde_url) {
            this.silde_url = silde_url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSildeImg() {
            return sildeImg;
        }

        public void setSildeImg(String sildeImg) {
            this.sildeImg = sildeImg;
        }
    }
}
