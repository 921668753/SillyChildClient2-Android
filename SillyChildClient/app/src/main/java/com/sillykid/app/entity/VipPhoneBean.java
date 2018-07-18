package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class VipPhoneBean extends BaseResult<VipPhoneBean.ResultBean > {


    public static class ResultBean {
        /**
         * id : 2
         * telephone : 1201
         * content : 120救援电话
         * created_at : 1506395417
         * order_desc : 2
         * is_show : 1
         */

        private int id;
        private String telephone;
        private String content;
        private int created_at;
        private int order_desc;
        private int is_show;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public int getOrder_desc() {
            return order_desc;
        }

        public void setOrder_desc(int order_desc) {
            this.order_desc = order_desc;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }
    }
}
