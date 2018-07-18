package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class ApplyAfterSalesBean extends BaseResult<ApplyAfterSalesBean.DataBean> {


    public class DataBean {
        private List<RefundTypeBean> refund_type;
        private List<RefundReasonBean> refund_reason;

        public List<RefundTypeBean> getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(List<RefundTypeBean> refund_type) {
            this.refund_type = refund_type;
        }

        public List<RefundReasonBean> getRefund_reason() {
            return refund_reason;
        }

        public void setRefund_reason(List<RefundReasonBean> refund_reason) {
            this.refund_reason = refund_reason;
        }

        public class RefundTypeBean implements IPickerViewData {
            /**
             * code : 11
             * cfg_value : 不喜欢
             */

            private String code;
            private String cfg_value;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCfg_value() {
                return cfg_value;
            }

            public void setCfg_value(String cfg_value) {
                this.cfg_value = cfg_value;
            }

            @Override
            public String getPickerViewText() {
                return cfg_value;
            }
        }

        public class RefundReasonBean implements IPickerViewData{
            /**
             * code : 21
             * cfg_value : 颜色丑，尺寸不合适
             */

            private String code;
            private String cfg_value;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCfg_value() {
                return cfg_value;
            }

            public void setCfg_value(String cfg_value) {
                this.cfg_value = cfg_value;
            }

            @Override
            public String getPickerViewText() {
                return cfg_value;
            }
        }
    }
}
