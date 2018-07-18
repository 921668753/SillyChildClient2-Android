package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class CharterOrderBean  extends BaseResult<CharterOrderBean.ResultBean>{


    public static class ResultBean {
        /**
         * totalPages : 3
         * list : [{"air_id":44,"order_sn":"201709231853257747","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"冲神","type":3,"customer_name":"sjjhb","drv_name":null,"create_at":"2017-09-23 18:53:25","drv_phone":null,"total_price":56456,"real_price":"56456.00"},{"air_id":43,"order_sn":"201709231828585366","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"线路订单","type":3,"customer_name":"发个机会","drv_name":null,"create_at":"2017-09-23 18:28:58","drv_phone":null,"total_price":56456,"real_price":null},{"air_id":42,"order_sn":"201709231824482717","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"按天包车游","type":6,"customer_name":"效果很好","drv_name":null,"create_at":"2017-09-23 18:24:48","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":41,"order_sn":"201709231820459124","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"按天包车游","type":6,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 18:20:45","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":40,"order_sn":"201709231753218024","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"线路订单","type":3,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 17:53:21","drv_phone":null,"total_price":56456,"real_price":null},{"air_id":39,"order_sn":"201709231749348089","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"按天包车游","type":6,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 17:49:34","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":38,"order_sn":"201709231744189586","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"私人订制","type":5,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 17:44:18","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":37,"order_sn":"201709231738514913","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"私人订制","type":5,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 17:38:51","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":36,"order_sn":"201709231732482959","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"私人订制","type":5,"customer_name":"齐银楠","drv_name":null,"create_at":"2017-09-23 17:32:48","drv_phone":null,"total_price":0,"real_price":"0.00"},{"air_id":35,"order_sn":"201709231728568544","seller_id":null,"hx_user_name":null,"nickname":null,"avatar":null,"status":0,"title":"单次接送","type":4,"customer_name":"齐银楠","drv_name":null,"create_at":null,"drv_phone":null,"total_price":0,"real_price":"0.00"}]
         */
        private int totalPages;
        private List<ListBean> list;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * air_id : 44
             * order_sn : 201709231853257747
             * seller_id : 司导id
             * hx_user_name : null
             * nickname : null
             * avatar : null
             * status : 0
             * title : 冲神
             * type : 3
             * customer_name : sjjhb
             * drv_name : null
             * create_at : 2017-09-23 18:53:25
             * drv_phone : null
             * total_price : 56456
             * real_price : 56456.00
             * total_price_fmt : 56456
             * real_price_fmt : 56456.00
             * seller_order_status ：司导  0未评价，1已评价
             * user_order_status : 用户  0未评价，1已评价
             * line_id  :线路id
             * user_confirm :   0：用户未确认结束；1：用户已确认结束
             */

            private String air_id;
            private String order_sn;
            private String seller_id;
            private String hx_user_name;
            private String nickname;
            private String avatar;
            @SerializedName("status")
            private int statusX;
            private String title;
            private int type;
            private String customer_name;
            private String drv_name;
            private String create_at;
            private String drv_phone;
            private String total_price;
            private String real_price;
            private String total_price_fmt;
            private String real_price_fmt;
            private String seller_order_status;
            private String user_order_status;
            private String line_id;
            private String user_confirm;

            public String getAir_id() {
                return air_id;
            }

            public void setAir_id(String air_id) {
                this.air_id = air_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public String getHx_user_name() {
                return hx_user_name;
            }

            public void setHx_user_name(String hx_user_name) {
                this.hx_user_name = hx_user_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getStatusX() {
                return statusX;
            }

            public void setStatusX(int statusX) {
                this.statusX = statusX;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;
            }

            public String getDrv_name() {
                return drv_name;
            }

            public void setDrv_name(String drv_name) {
                this.drv_name = drv_name;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getDrv_phone() {
                return drv_phone;
            }

            public void setDrv_phone(String drv_phone) {
                this.drv_phone = drv_phone;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getReal_price() {
                return real_price;
            }

            public void setReal_price(String real_price) {
                this.real_price = real_price;
            }

            public String getTotal_price_fmt() {
                return total_price_fmt;
            }

            public void setTotal_price_fmt(String total_price_fmt) {
                this.total_price_fmt = total_price_fmt;
            }

            public String getReal_price_fmt() {
                return real_price_fmt;
            }

            public void setReal_price_fmt(String real_price_fmt) {
                this.real_price_fmt = real_price_fmt;
            }

            public String getSeller_order_status() {
                return seller_order_status;
            }

            public void setSeller_order_status(String seller_order_status) {
                this.seller_order_status = seller_order_status;
            }

            public String getUser_order_status() {
                return user_order_status;
            }

            public void setUser_order_status(String user_order_status) {
                this.user_order_status = user_order_status;
            }

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
            }

            public String getUser_confirm() {
                return user_confirm;
            }

            public void setUser_confirm(String user_confirm) {
                this.user_confirm = user_confirm;
            }
        }
    }
}
