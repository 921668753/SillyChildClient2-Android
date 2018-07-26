package com.sillykid.app.entity.mine.mycollection;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ShopBean extends BaseResult<List<ShopBean.DataBean>> {


    public class DataBean {
        /**
         * store_id : 2
         * store_name : 小明美妆专卖
         * store_logo : null
         * favorite_id : 75
         * type_id : 2
         * score_point : 4.8
         */

        private int store_id;
        private String store_name;
        private String store_logo;
        private int favorite_id;
        private int type_id;
        private String score_point;
        private String collect_number;

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

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public int getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(int favorite_id) {
            this.favorite_id = favorite_id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getScore_point() {
            return score_point;
        }

        public void setScore_point(String score_point) {
            this.score_point = score_point;
        }

        public String getCollect_number() {
            return collect_number;
        }

        public void setCollect_number(String collect_number) {
            this.collect_number = collect_number;
        }
    }
}
