package com.sillykid.app.entity.mall.goodslist.goodsdetails;

import com.common.cklibrary.entity.BaseResult;

public class GoodsDetailsBean extends BaseResult<GoodsDetailsBean.DataBean> {

    public class DataBean {
        /**
         * brief : null
         * small : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//55481895_small.jpg
         * store_id : 5
         * comment_count : 0
         * big : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//55481895_big.jpg
         * goods_id : 135
         * brand_name :
         * store : 10000
         * brand_id : 0
         * goods_tag : 特价商品
         * price : 48.0
         * name : Bzbz新款平底防滑沙滩鞋厚底凉拖鞋女夏时尚韩版外穿
         * store_name :
         * have_spec : 0
         * comment : null
         * class : class com.enation.app.shop.mobile.model.Goods
         * mktprice : 39.0
         */

        private String brief;
        private String small;
        private int store_id;
        private boolean favorited;
        private int product_id;
        private int comment_count;
        private String big;
        private int goods_id;
        private String brand_name;
        private String store;
        private int brand_id;
        private String goods_tag;
        private String price;
        private String name;
        private String store_name;
        private String have_spec;
        private String rongYun;
        private String rongYunStore;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getBig() {
            return big;
        }

        public void setBig(String big) {
            this.big = big;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getGoods_tag() {
            return goods_tag;
        }

        public void setGoods_tag(String goods_tag) {
            this.goods_tag = goods_tag;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getHave_spec() {
            return have_spec;
        }

        public void setHave_spec(String have_spec) {
            this.have_spec = have_spec;
        }

        public String getRongYun() {
            return rongYun;
        }

        public void setRongYun(String rongYun) {
            this.rongYun = rongYun;
        }

        public String getRongYunStore() {
            return rongYunStore;
        }

        public void setRongYunStore(String rongYunStore) {
            this.rongYunStore = rongYunStore;
        }
    }
}
