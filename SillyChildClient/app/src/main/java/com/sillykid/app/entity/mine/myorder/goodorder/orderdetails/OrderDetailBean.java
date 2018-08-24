package com.sillykid.app.entity.mine.myorder.goodorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class OrderDetailBean extends BaseResult<OrderDetailBean.DataBeanX> {


    public class DataBeanX {
        /**
         * order_id : 638
         * status : 3
         * shipInfo : {"message":"ok","nu":"889966941641165886","ischeck":"1","condition":"F00","com":"yuantong","status":"200","state":"3","data":[{"time":"2018-06-05 13:05:27","ftime":"2018-06-05 13:05:27","context":"客户 签收人: 陈媛 已签收  感谢使用圆通速递，期待再次为您服务"},{"time":"2018-06-05 08:58:08","ftime":"2018-06-05 08:58:08","context":"河南省南阳市新野县公司(点击查询电话)王** 派件中 派件员电话13721843967"},{"time":"2018-06-05 08:43:03","ftime":"2018-06-05 08:43:03","context":"河南省南阳市新野县公司 已收入"},{"time":"2018-06-05 08:23:25","ftime":"2018-06-05 08:23:25","context":"河南省南阳市新野县公司 已收入"},{"time":"2018-06-05 04:19:56","ftime":"2018-06-05 04:19:56","context":"漯河转运中心 已发出,下一站"},{"time":"2018-06-04 04:45:56","ftime":"2018-06-04 04:45:56","context":"漯河转运中心 已发出,下一站 河南省南阳市新野县"},{"time":"2018-06-04 02:28:22","ftime":"2018-06-04 02:28:22","context":"漯河转运中心 已收入"},{"time":"2018-06-04 01:04:46","ftime":"2018-06-04 01:04:46","context":"漯河转运中心 已收入"},{"time":"2018-06-03 05:51:29","ftime":"2018-06-03 05:51:29","context":"广州转运中心 已发出,下一站"},{"time":"2018-06-03 00:24:02","ftime":"2018-06-03 00:24:02","context":"广州转运中心 已发出,下一站 漯河转运中心"},{"time":"2018-06-03 00:12:24","ftime":"2018-06-03 00:12:24","context":"广州转运中心 已收入"},{"time":"2018-06-02 23:22:08","ftime":"2018-06-02 23:22:08","context":"广州转运中心 已收入"},{"time":"2018-06-02 21:46:17","ftime":"2018-06-02 21:46:17","context":"广东省广州市新增城市公司 已发出,下一站"},{"time":"2018-06-02 21:43:13","ftime":"2018-06-02 21:43:13","context":"广东省广州市新增城市公司 已发出,下一站 广州转运中心"},{"time":"2018-06-02 21:33:14","ftime":"2018-06-02 21:33:14","context":"广东省广州市新增城市公司 已打包"},{"time":"2018-06-02 21:26:43","ftime":"2018-06-02 21:26:43","context":"广东省广州市新增城市公司(点击查询电话) 已揽收"},{"time":"2018-06-02 18:44:22","ftime":"2018-06-02 18:44:22","context":"广东省广州市新增城市公司 取件人: 曾树洋 已收件"}]}
         * itemList : [{"item_id":246,"order_id":638,"goods_id":218,"product_id":590,"name":"名称","specs":"","num":1,"sn":"G20180614183053500-1","price":20,"image":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_183006.jpg"}]
         * ship_name : Liu
         * ship_mobile : 15545678901
         * shipping_area : 北京-密云区-城区
         * ship_addr : haha
         * order_amount : 20
         * ship_money :
         * ship_no : 889966941641165886
         * bouns_money :
         * activity :
         * sn : DD152897244214-1
         * create_time : 2018-06-14 18:34:06
         * payment_type : qianbai
         * paymoney : 20
         * pay_time :
         * allocation_time :
         * reason :
         * backMoney :
         * total :
         * backTime :
         * lastTime :
         */

        private int order_id;
        private int status;
        private ShipInfoBean shipInfo;
        private String ship_name;
        private String ship_mobile;
        private String shipping_area;
        private String ship_addr;
        private String order_amount;
        private String ship_money;
        private String ship_no;
        private String bouns_money;
        private String activity;
        private String sn;
        private String create_time;
        private String payment_type;
        private String paymoney;
        private String pay_time;
        private String allocation_time;
        private String reason;
        private String backMoney;
        private String total;
        private String backTime;
        private String lastTime;
        private String need_pay_money;
        private String nowTime;
        private int commented;
        private List<ItemListBean> itemList;

        public String getNeed_pay_money() {
            return need_pay_money;
        }

        public void setNeed_pay_money(String need_pay_money) {
            this.need_pay_money = need_pay_money;
        }

        public String getNowTime() {
            return nowTime;
        }

        public void setNowTime(String nowTime) {
            this.nowTime = nowTime;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ShipInfoBean getShipInfo() {
            return shipInfo;
        }

        public void setShipInfo(ShipInfoBean shipInfo) {
            this.shipInfo = shipInfo;
        }

        public String getShip_name() {
            return ship_name;
        }

        public void setShip_name(String ship_name) {
            this.ship_name = ship_name;
        }

        public String getShip_mobile() {
            return ship_mobile;
        }

        public void setShip_mobile(String ship_mobile) {
            this.ship_mobile = ship_mobile;
        }

        public String getShipping_area() {
            return shipping_area;
        }

        public void setShipping_area(String shipping_area) {
            this.shipping_area = shipping_area;
        }

        public String getShip_addr() {
            return ship_addr;
        }

        public void setShip_addr(String ship_addr) {
            this.ship_addr = ship_addr;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getShip_money() {
            return ship_money;
        }

        public void setShip_money(String ship_money) {
            this.ship_money = ship_money;
        }

        public String getShip_no() {
            return ship_no;
        }

        public void setShip_no(String ship_no) {
            this.ship_no = ship_no;
        }

        public String getBouns_money() {
            return bouns_money;
        }

        public void setBouns_money(String bouns_money) {
            this.bouns_money = bouns_money;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(String paymoney) {
            this.paymoney = paymoney;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getAllocation_time() {
            return allocation_time;
        }

        public void setAllocation_time(String allocation_time) {
            this.allocation_time = allocation_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getBackMoney() {
            return backMoney;
        }

        public void setBackMoney(String backMoney) {
            this.backMoney = backMoney;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getBackTime() {
            return backTime;
        }

        public void setBackTime(String backTime) {
            this.backTime = backTime;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getCommented() {
            return commented;
        }

        public void setCommented(int commented) {
            this.commented = commented;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public class ShipInfoBean {
            /**
             * message : ok
             * nu : 889966941641165886
             * ischeck : 1
             * condition : F00
             * com : yuantong
             * status : 200
             * state : 3
             * data : [{"time":"2018-06-05 13:05:27","ftime":"2018-06-05 13:05:27","context":"客户 签收人: 陈媛 已签收  感谢使用圆通速递，期待再次为您服务"},{"time":"2018-06-05 08:58:08","ftime":"2018-06-05 08:58:08","context":"河南省南阳市新野县公司(点击查询电话)王** 派件中 派件员电话13721843967"},{"time":"2018-06-05 08:43:03","ftime":"2018-06-05 08:43:03","context":"河南省南阳市新野县公司 已收入"},{"time":"2018-06-05 08:23:25","ftime":"2018-06-05 08:23:25","context":"河南省南阳市新野县公司 已收入"},{"time":"2018-06-05 04:19:56","ftime":"2018-06-05 04:19:56","context":"漯河转运中心 已发出,下一站"},{"time":"2018-06-04 04:45:56","ftime":"2018-06-04 04:45:56","context":"漯河转运中心 已发出,下一站 河南省南阳市新野县"},{"time":"2018-06-04 02:28:22","ftime":"2018-06-04 02:28:22","context":"漯河转运中心 已收入"},{"time":"2018-06-04 01:04:46","ftime":"2018-06-04 01:04:46","context":"漯河转运中心 已收入"},{"time":"2018-06-03 05:51:29","ftime":"2018-06-03 05:51:29","context":"广州转运中心 已发出,下一站"},{"time":"2018-06-03 00:24:02","ftime":"2018-06-03 00:24:02","context":"广州转运中心 已发出,下一站 漯河转运中心"},{"time":"2018-06-03 00:12:24","ftime":"2018-06-03 00:12:24","context":"广州转运中心 已收入"},{"time":"2018-06-02 23:22:08","ftime":"2018-06-02 23:22:08","context":"广州转运中心 已收入"},{"time":"2018-06-02 21:46:17","ftime":"2018-06-02 21:46:17","context":"广东省广州市新增城市公司 已发出,下一站"},{"time":"2018-06-02 21:43:13","ftime":"2018-06-02 21:43:13","context":"广东省广州市新增城市公司 已发出,下一站 广州转运中心"},{"time":"2018-06-02 21:33:14","ftime":"2018-06-02 21:33:14","context":"广东省广州市新增城市公司 已打包"},{"time":"2018-06-02 21:26:43","ftime":"2018-06-02 21:26:43","context":"广东省广州市新增城市公司(点击查询电话) 已揽收"},{"time":"2018-06-02 18:44:22","ftime":"2018-06-02 18:44:22","context":"广东省广州市新增城市公司 取件人: 曾树洋 已收件"}]
             */

            private String com;
            private String context;
            private String ftime;
            private String time;

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String contex) {
                this.context = contex;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public class ItemListBean {
            /**
             * item_id : 246
             * order_id : 638
             * goods_id : 218
             * product_id : 590
             * name : 名称
             * specs :
             * num : 1
             * sn : G20180614183053500-1
             * price : 20
             * image : http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_183006.jpg
             */

            private int item_id;
            private int order_id;
            private int goods_id;
            private int product_id;
            private int sellback_state;
            private int commented;
            private String name;
            private String specs;
            private int num;
            private String sn;
            private String price;
            private String image;

            public int getSellback_state() {
                return sellback_state;
            }

            public void setSellback_state(int sellback_state) {
                this.sellback_state = sellback_state;
            }

            public int getCommented() {
                return commented;
            }

            public void setCommented(int commented) {
                this.commented = commented;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
