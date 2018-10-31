package com.sillykid.app.entity.mine.mywallet.coupons;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class CouponRedemptionCentreBean extends BaseResult<CouponRedemptionCentreBean.DataBean> {


    public class DataBean {
        /**
         * coupon_list : [{"coupon_id":1,"validity_period":1569749762,"limit_content":"满100.00元可用","coupon_name":"满100减20优惠券","denomination":"20.00"}]
         * picture : http://ovwiqces1.bkt.clouddn.com/FmDy-A7IqzfD72FmvS1ez2EK0NGM?imageMogr2/auto-orient/thumbnail/!20p
         */

        private String picture;
        private List<CouponListBean> coupon_list;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public class CouponListBean {
            /**
             * coupon_id : 1
             * validity_period : 1569749762
             * limit_content : 满100.00元可用
             * coupon_name : 满100减20优惠券
             * denomination : 20.00
             */

            private int coupon_id;
            private String validity_period;
            private String limit_content;
            private String coupon_name;
            private String denomination;

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getValidity_period() {
                return validity_period;
            }

            public void setValidity_period(String validity_period) {
                this.validity_period = validity_period;
            }

            public String getLimit_content() {
                return limit_content;
            }

            public void setLimit_content(String limit_content) {
                this.limit_content = limit_content;
            }

            public String getCoupon_name() {
                return coupon_name;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }

            public String getDenomination() {
                return denomination;
            }

            public void setDenomination(String denomination) {
                this.denomination = denomination;
            }
        }
    }
}
