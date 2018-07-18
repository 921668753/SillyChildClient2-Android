package com.sillykid.app.entity.mine.mywallet.coupons;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class CouponsBean extends BaseResult<List<CouponsBean.DataBean>> {


    public class DataBean {
        /**
         * type_id : 1
         * type_money : 100
         * type_name : 真正的优惠券
         * min_goods_amount : 1000
         * used_time : null
         * bonus_id : 13
         * order_id : null
         * create_time : 1527908816
         * platform :
         * limit_days : 3
         * use_scope :
         * uname : 18550875927
         */

        private int type_id;
        private String type_money;
        private String type_name;
        private String min_goods_amount;
        private String used_time;
        private int bonus_id;
        private String order_id;
        private String create_time;
        private String platform;
        private int limit_days;
        private String use_scope;
        private String uname;

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_money() {
            return type_money;
        }

        public void setType_money(String type_money) {
            this.type_money = type_money;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getMin_goods_amount() {
            return min_goods_amount;
        }

        public void setMin_goods_amount(String min_goods_amount) {
            this.min_goods_amount = min_goods_amount;
        }

        public String getUsed_time() {
            return used_time;
        }

        public void setUsed_time(String used_time) {
            this.used_time = used_time;
        }

        public int getBonus_id() {
            return bonus_id;
        }

        public void setBonus_id(int bonus_id) {
            this.bonus_id = bonus_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public int getLimit_days() {
            return limit_days;
        }

        public void setLimit_days(int limit_days) {
            this.limit_days = limit_days;
        }

        public String getUse_scope() {
            return use_scope;
        }

        public void setUse_scope(String use_scope) {
            this.use_scope = use_scope;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }
    }
}
