package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 2017/7/27.
 */

public class AlipayBean extends BaseResult<AlipayBean.ResultBean > {

    public static class ResultBean {
        /**
         * aliPayParams : app_id=2017052207306091&biz_content=%7B%22subject%22%3A%22TODO%5Cu777f%5Cu9014%5Cu79d1%5Cu6280%5C%2F%5Cu6d41%5Cu91cf%5Cu8fbe%5Cu4eba%22%2C%22out_trade_no%22%3A%22RC6319892017091414514151080%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2290m%22%2C%22total_amount%22%3A%221989%22%2C%22body%22%3A%5B%7B%22userId%22%3A63%2C%22amount%22%3A%221989%22%2C%22orderSn%22%3A%22RC6319892017091414514151080%22%7D%5D%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fshz.api.user.ruitukeji.dev%2Findex.php%2FApi%2FCallback%2Falipay&prod_code=&sign=iPiIDB6Uy3aYwIXyhCNFxvt3fWDrA52V%2B614pSqad3FX7uFraLvPBzz9r%2BAta5nKStvOJWUWDID6dlSCNPs3at7kuPOHtGQjWQV9rdp1%2FnI%2FMjNsbJogIi31imQe07P9lIl93WXfBPnl%2FwdP44d8gueMgcZM8lCXgo6njz1bKkQ%3D&sign_type=RSA&timestamp=2017-09-14+14%3A51%3A41&version=1.0
         */

        private String aliPayParams;
        private String realPrice;//支付的金额
        /**
         * wxPayParams : {"appid":"wxd8d4b0dc3305513a","noncestr":"FOylukKq9knth3Pu","package":"Sign=WXPay","partnerid":"1480848402","prepayid":"wx201709141507312e02bb88bf0986708106","timestamp":1505372851,"sign":"52F9C44B2AB1F25FE0DC0C85947CB780","jsConfig":{"appId":"wxd8d4b0dc3305513a","nonceStr":"d4xwft7qene3qz0mqdrgx8vo4gza8y5c","package":"prepay_id=wx201709141507312e02bb88bf0986708106","signType":"MD5","timeStamp":"1505372851","sign":"2FD9846FF5C194884DBCAE04AD2A0616","paySign":"2FD9846FF5C194884DBCAE04AD2A0616"}}
         */

        private WxPayParamsBean wxPayParams;

        public WxPayParamsBean getWxPayParams() {
            return wxPayParams;
        }

        public void setWxPayParams(WxPayParamsBean wxPayParams) {
            this.wxPayParams = wxPayParams;
        }

        public static class WxPayParamsBean {
            /**
             * appid : wxd8d4b0dc3305513a
             * noncestr : FOylukKq9knth3Pu
             * package : Sign=WXPay
             * partnerid : 1480848402
             * prepayid : wx201709141507312e02bb88bf0986708106
             * timestamp : 1505372851
             * sign : 52F9C44B2AB1F25FE0DC0C85947CB780
             * jsConfig : {"appId":"wxd8d4b0dc3305513a","nonceStr":"d4xwft7qene3qz0mqdrgx8vo4gza8y5c","package":"prepay_id=wx201709141507312e02bb88bf0986708106","signType":"MD5","timeStamp":"1505372851","sign":"2FD9846FF5C194884DBCAE04AD2A0616","paySign":"2FD9846FF5C194884DBCAE04AD2A0616"}
             */

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private String timestamp;
            private String sign;
            private JsConfigBean jsConfig;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public JsConfigBean getJsConfig() {
                return jsConfig;
            }

            public void setJsConfig(JsConfigBean jsConfig) {
                this.jsConfig = jsConfig;
            }

            public static class JsConfigBean {
                /**
                 * appId : wxd8d4b0dc3305513a
                 * nonceStr : d4xwft7qene3qz0mqdrgx8vo4gza8y5c
                 * package : prepay_id=wx201709141507312e02bb88bf0986708106
                 * signType : MD5
                 * timeStamp : 1505372851
                 * sign : 2FD9846FF5C194884DBCAE04AD2A0616
                 * paySign : 2FD9846FF5C194884DBCAE04AD2A0616
                 */

                private String appId;
                private String nonceStr;
                @SerializedName("package")
                private String packageX;
                private String signType;
                private String timeStamp;
                private String sign;
                private String paySign;

                public String getAppId() {
                    return appId;
                }

                public void setAppId(String appId) {
                    this.appId = appId;
                }

                public String getNonceStr() {
                    return nonceStr;
                }

                public void setNonceStr(String nonceStr) {
                    this.nonceStr = nonceStr;
                }

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }

                public String getSignType() {
                    return signType;
                }

                public void setSignType(String signType) {
                    this.signType = signType;
                }

                public String getTimeStamp() {
                    return timeStamp;
                }

                public void setTimeStamp(String timeStamp) {
                    this.timeStamp = timeStamp;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getPaySign() {
                    return paySign;
                }

                public void setPaySign(String paySign) {
                    this.paySign = paySign;
                }
            }
        }


        public String getAliPayParams() {
            return aliPayParams;
        }

        public void setAliPayParams(String aliPayParams) {
            this.aliPayParams = aliPayParams;
        }

        public String getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }
    }
}
