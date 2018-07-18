package com.sillykid.app.entity.mine.mywallet.recharge;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

public class WeChatPayBean extends BaseResult<WeChatPayBean.DataBean> {

    public class DataBean {
        /**
         * package : Sign=WXPay
         * appid : wx444bb74a6d803478
         * sign : 2C054FA97B0D7489F51E52F047CD63E1
         * partnerid : 1489687802
         * prepayid : wx04192835816077b37390bdf93950555317
         * noncestr : 86117916841827264213741712652316
         * timestamp : 1528111715
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
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

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
