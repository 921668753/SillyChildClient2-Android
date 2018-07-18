package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/10/12.
 */

public class LinePseudoAgreementBean extends BaseResult<LinePseudoAgreementBean.ResultBean> {


    public class ResultBean {
        /**
         * seller_id :
         * line_id : 5
         * line_buy_num : 1
         * line_title : 关东慢行｜修善寺→泰迪熊博物馆→城崎海岸→热海城→横滨 中文2日包车游，东京往返
         * cover_img : http://img.shahaizi.cn/fbe56201710121045569683.jpg
         * is_admin : 1
         * line_price : 3000.00
         */

        private String seller_id;
        private int line_id;
        private int line_buy_num;
        private String line_title;
        private String cover_img;
        private int is_admin;
        private String line_price;

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public int getLine_id() {
            return line_id;
        }

        public void setLine_id(int line_id) {
            this.line_id = line_id;
        }

        public int getLine_buy_num() {
            return line_buy_num;
        }

        public void setLine_buy_num(int line_buy_num) {
            this.line_buy_num = line_buy_num;
        }

        public String getLine_title() {
            return line_title;
        }

        public void setLine_title(String line_title) {
            this.line_title = line_title;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public int getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(int is_admin) {
            this.is_admin = is_admin;
        }

        public String getLine_price() {
            return line_price;
        }

        public void setLine_price(String line_price) {
            this.line_price = line_price;
        }
    }
}
