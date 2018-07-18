package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class ServiceDetailsBean extends BaseResult<ServiceDetailsBean.DataBean> {


    public class DataBean {
        /**
         * SHZ : {"refund_time":1528341338,"refund_status":null,"refund_remark":null,"store_logo":"http://ovwiqces1.bkt.clouddn.com/301527474546"}
         * member : {"member_id":48,"remark":"退货","reason":"","regtime":1528338147,"apply_alltotal":1346,"face":""}
         * store : {"create_time":1528341314,"create_status":null,"create_remark":null,"store_logo":"http://static.b2b2cv2.javamall.com.cn/attachment/storeLogo/201504071505250925.jpg"}
         */

        private SHZBean SHZ;
        private MemberBean member;
        private StoreBean store;

        public SHZBean getSHZ() {
            return SHZ;
        }

        public void setSHZ(SHZBean SHZ) {
            this.SHZ = SHZ;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public class SHZBean {
            /**
             * refund_time : 1528341338
             * refund_status : null
             * refund_remark : null
             * store_logo : http://ovwiqces1.bkt.clouddn.com/301527474546
             */

            private String refund_time;
            private String refund_status;
            private String refund_remark;
            private String store_logo;

            public String getRefund_time() {
                return refund_time;
            }

            public void setRefund_time(String refund_time) {
                this.refund_time = refund_time;
            }

            public String getRefund_status() {
                return refund_status;
            }

            public void setRefund_status(String refund_status) {
                this.refund_status = refund_status;
            }

            public String getRefund_remark() {
                return refund_remark;
            }

            public void setRefund_remark(String refund_remark) {
                this.refund_remark = refund_remark;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }

        public class MemberBean {
            /**
             * member_id : 48
             * remark : 退货
             * reason :
             * regtime : 1528338147
             * apply_alltotal : 1346
             * face :
             */

            private int member_id;
            private String remark;
            private String reason;
            private String regtime;
            private String reason_detail;
            private String apply_alltotal;
            private String face;

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getRegtime() {
                return regtime;
            }

            public void setRegtime(String regtime) {
                this.regtime = regtime;
            }

            public String getApply_alltotal() {
                return apply_alltotal;
            }

            public void setApply_alltotal(String apply_alltotal) {
                this.apply_alltotal = apply_alltotal;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getReason_detail() {
                return reason_detail;
            }

            public void setReason_detail(String reason_detail) {
                this.reason_detail = reason_detail;
            }
        }

        public class StoreBean {
            /**
             * create_time : 1528341314
             * create_status : null
             * create_remark : null
             * store_logo : http://static.b2b2cv2.javamall.com.cn/attachment/storeLogo/201504071505250925.jpg
             */

            private String create_time;
            private String create_status;
            private String create_remark;
            private String store_logo;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCreate_status() {
                return create_status;
            }

            public void setCreate_status(String create_status) {
                this.create_status = create_status;
            }

            public String getCreate_remark() {
                return create_remark;
            }

            public void setCreate_remark(String create_remark) {
                this.create_remark = create_remark;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }
    }
}
