package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class MyOrderPicturesBean extends BaseResult<MyOrderPicturesBean.ResultBean > {


    public static class ResultBean {
        /**
         * orderString : alipay_sdk=alipay-sdk-php-20161101&app_id=2016081600256238&biz_content=%7B%22body%22%3A%22%5Cu6211%5Cu662f%5Cu6d4b%5Cu8bd5%5Cu6570%5Cu636e%22%2C%22subject%22%3A%22App%5Cu652f%5Cu4ed8%5Cu6d4b%5Cu8bd5%22%2C%22out_trade_no%22%3A%2220170125test01%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwztx.shp.api.ruitukeji.com%2FCallback%2Falipay_callback&sign_type=RSA2&timestamp=2017-07-28+13%3A28%3A16&version=1.0&sign=nRA2aIMjLpCW%2BfyFFmKPcq0AkBBUNYdYGCYBJgaskHl4ChZoWN5oG0TgDEHrcnWxqw1NE4FaWexB5HnezsMtj1ZgTlePLVGT6KtZFUL0XYrQnkGck3CILURvBggPGClFDhFKDCk9%2FGUk8F5dTRRChWHC%2Bj4zj7EAZF3sbQQ9MAOkKiD1o0pFrjkrAzMJx6ipoHpXRSOqoCyczK33kETjYyrrIPeGvlu2xVBpYshsxS4fgjKAswS6I%2BvudSMEDZMd1SjaPitujzhlSIoswVc%2Bx5EX1X6McLApab6bcnhRiKwHyAJVb%2FXUtGPaeVXOBHiVKDUznQhl6eXjMPaRXTluqQ%3D%3D
         * isUseSandbox : 1
         */

        private String orderString;
        private String isUseSandbox;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }

        public String getIsUseSandbox() {
            return isUseSandbox;
        }

        public void setIsUseSandbox(String isUseSandbox) {
            this.isUseSandbox = isUseSandbox;
        }
    }
}
