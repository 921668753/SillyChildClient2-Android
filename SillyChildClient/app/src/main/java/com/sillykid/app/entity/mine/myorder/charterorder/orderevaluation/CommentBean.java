package com.sillykid.app.entity.mine.myorder.charterorder.orderevaluation;

import com.common.cklibrary.entity.BaseResult;

public class CommentBean extends BaseResult<CommentBean.DataBean> {

    public class DataBean {
        /**
         * pay_amount : 0.01
         * product_name : 成田机场送机
         * main_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private String pay_amount;
        private String product_name;
        private String main_picture;

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getMain_picture() {
            return main_picture;
        }

        public void setMain_picture(String main_picture) {
            this.main_picture = main_picture;
        }
    }
}
