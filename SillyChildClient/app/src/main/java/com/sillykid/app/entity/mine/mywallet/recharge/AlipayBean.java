package com.sillykid.app.entity.mine.mywallet.recharge;

import com.common.cklibrary.entity.BaseResult;

public class AlipayBean extends BaseResult<AlipayBean.DataBean> {


    public class DataBean {
        /**
         * msg : 获取支付信息成功
         * orderString : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017101309278810&biz_content=%7B%22body%22%3A%22%E8%AE%A2%E5%8D%95%E7%BC%96%E5%8F%B7%3ACZ15279103023610%3B%E8%AE%A2%E5%8D%95%E9%87%91%E9%A2%9D%3A100.0%3B%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8F%3A1%22%2C%22out_trade_no%22%3A%22CZ15279103023610%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%82%BB%E5%AD%A9%E5%AD%90%E7%94%A8%E6%88%B7%E5%85%85%E5%80%BC%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22100.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.shahaizi.keiousoft.com%2Fapi%2Fpay%2Falipay%2Fresult.do&sign=NV4PyE5pFdvsGJsd8x%2Fz68xXcnLTmPoMYECmH%2FUx%2BM397wkR6aIXrY9UeEF2JvuF9JmntsSG2ilWGC8rHKwDDEmxEBFEFXZGm2%2FHCw8Nv%2BJkTT0RblvyGrb0IH1dJWbn9yPuexlD1FnqUPAAobopgLiS91x4DeFSXyXZlqXQdr8s9ctpa0qraycYzf1a5A%2FRv2ZY6eyicAD15R1L3pRP1oTf%2BfXVLURCeA%2FjcqkIACawenbGBHTCB7imna%2BEqgl0IZmKUknMjXyGlZa36Ynb16hsXMwXnsjlqbEk2j865YCHPvpnEl1Usq5AjKqYpQjfXsc4xzBjHvBHAgiPAOy5lw%3D%3D&sign_type=RSA2&timestamp=2018-06-02+11%3A31%3A42&version=1.0
         */

        private String msg;
        private String orderString;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }
}
