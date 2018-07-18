package com.sillykid.app.entity.mine.mycollection;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyCollectionBean extends BaseResult<List<MyCollectionBean.DataBean>> {


    public class DataBean {
        /**
         * goods_id : 138
         * name : Miss Sixty2017新女春装天使系列天使之翼牛仔短裤
         * sn : 00034
         * brief : null
         * price : 1490
         * mktprice : 1490
         * specs : [{"cost":1490,"enable_store":0,"goodsLvPrices":[],"goods_id":38,"name":"Miss Sixty2017新女春装天使系列天使之翼牛仔短裤","price":1490,"product_id":38,"sn":"00034","specList":[],"specs":"","specsvIdJson":"[]","store":0,"weight":230}]
         * have_spec : 0
         * thumbnail : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//05100692_thumbnail.jpg
         * big : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//05100692_big.jpg
         * small : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//05100692_small.jpg
         * original : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//05100692.jpg
         * store_id : 5
         * store_name : 时尚女装/搭配
         * favorite_id : 7
         */

        private int goods_id;
        private String name;
        private String sn;
        private String brief;
        private String price;
        private String mktprice;
        private String specs;
        private int have_spec;
        private String thumbnail;
        private String big;
        private String small;
        private String original;
        private int store_id;
        private String store_name;
        private int favorite_id;
        private int product_id;
        private String store;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMktprice() {
            return mktprice;
        }

        public void setMktprice(String mktprice) {
            this.mktprice = mktprice;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public int getHave_spec() {
            return have_spec;
        }

        public void setHave_spec(int have_spec) {
            this.have_spec = have_spec;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getBig() {
            return big;
        }

        public void setBig(String big) {
            this.big = big;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(int favorite_id) {
            this.favorite_id = favorite_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }
    }
}
