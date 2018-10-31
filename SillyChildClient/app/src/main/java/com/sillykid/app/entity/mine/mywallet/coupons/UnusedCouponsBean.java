package com.sillykid.app.entity.mine.mywallet.coupons;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnusedCouponsBean extends BaseResult<UnusedCouponsBean.DataBean> {

    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 1
         * currentPageNo : 1
         * draw : 0
         * result : [{"validity_period":"2018-11-06","limit_content":"无限制使用","remark":"仅限接机使用","coupon_name":"全场通用","denomination":"5.00"}]
         * totalPageCount : 1
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

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

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * validity_period : 2018-11-06
             * limit_content : 无限制使用
             * remark : 仅限接机使用
             * coupon_name : 全场通用
             * denomination : 5.00
             */

            private String validity_period;
            private String limit_content;
            private String remark;
            private String coupon_name;
            private String denomination;
            private int coupon_id;

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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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
