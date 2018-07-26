package com.sillykid.app.entity.mall.goodslist.shop;

import com.common.cklibrary.entity.BaseResult;

public class ShopBean extends BaseResult<ShopBean.DataBean> {


    public class DataBean {
        /**
         * result : 1
         * message : 已收藏店铺
         */

        private int result;
        private String message;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
