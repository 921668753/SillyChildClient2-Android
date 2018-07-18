package com.sillykid.app.entity.mine.myshoppingcart.makesureorder;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MakeSureOrderBean extends BaseResult<MakeSureOrderBean.DataBean> {


    public class DataBean {
        /**
         * address : {"addr_id":8,"member_id":37,"name":"沉了","country":null,"province_id":2,"city_id":2815,"region_id":51975,"town_id":0,"region":"城区","city":"长宁区","province":"上海","town":"","addr":"1301好","zip":"","tel":"","mobile":"170****5257","def_addr":1,"isDel":0,"shipAddressName":null}
         * orderInfo : {"ship_account":0,"activity_account":0,"bonus_account":4}
         * goods : [{"goods_name":"ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋","goods_img":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_big.jpg","specs":null,"price":1298,"num":2,"activity_money":0,"ship_money":0},{"goods_name":"Bzbz新款平底防滑沙滩鞋厚底凉拖鞋女夏时尚韩版外穿","goods_img":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//55481895_big.jpg","specs":null,"price":48,"num":1,"activity_money":0,"ship_money":0}]
         */

        private AddressBean address;
        private OrderInfoBean orderInfo;
        private List<GoodsBean> goods;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public OrderInfoBean getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoBean orderInfo) {
            this.orderInfo = orderInfo;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public class AddressBean {
            /**
             * addr_id : 8
             * member_id : 37
             * name : 沉了
             * country : null
             * province_id : 2
             * city_id : 2815
             * region_id : 51975
             * town_id : 0
             * region : 城区
             * city : 长宁区
             * province : 上海
             * town :
             * addr : 1301好
             * zip :
             * tel :
             * mobile : 170****5257
             * def_addr : 1
             * isDel : 0
             * shipAddressName : null
             */

            private int addr_id;
            private int member_id;
            private String name;
            private String country;
            private int province_id;
            private int city_id;
            private int region_id;
            private int town_id;
            private String region;
            private String city;
            private String province;
            private String town;
            private String addr;
            private String zip;
            private String tel;
            private String mobile;
            private int def_addr;
            private int isDel;
            private String shipAddressName;

            public int getAddr_id() {
                return addr_id;
            }

            public void setAddr_id(int addr_id) {
                this.addr_id = addr_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getProvince_id() {
                return province_id;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public int getTown_id() {
                return town_id;
            }

            public void setTown_id(int town_id) {
                this.town_id = town_id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getDef_addr() {
                return def_addr;
            }

            public void setDef_addr(int def_addr) {
                this.def_addr = def_addr;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getShipAddressName() {
                return shipAddressName;
            }

            public void setShipAddressName(String shipAddressName) {
                this.shipAddressName = shipAddressName;
            }
        }

        public class OrderInfoBean {
            /**
             * ship_account : 0
             * activity_account : 0
             * bonus_account : 4
             */

            private String ship_account;
            private String activity_account;
            private String bonus_account;
            private String total_account;

            public String getShip_account() {
                return ship_account;
            }

            public void setShip_account(String ship_account) {
                this.ship_account = ship_account;
            }

            public String getActivity_account() {
                return activity_account;
            }

            public void setActivity_account(String activity_account) {
                this.activity_account = activity_account;
            }

            public String getBonus_account() {
                return bonus_account;
            }

            public void setBonus_account(String bonus_account) {
                this.bonus_account = bonus_account;
            }

            public String getTotal_account() {
                return total_account;
            }

            public void setTotal_account(String total_account) {
                this.total_account = total_account;
            }
        }

        public class GoodsBean {
            /**
             * goods_name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
             * goods_img : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_big.jpg
             * specs : null
             * price : 1298
             * num : 2
             * activity_money : 0
             * ship_money : 0
             */

            private String goods_name;
            private String goods_img;
            private String specs;
            private String price;
            private String num;
            private String activity_money;
            private String ship_money;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getActivity_money() {
                return activity_money;
            }

            public void setActivity_money(String activity_money) {
                this.activity_money = activity_money;
            }

            public String getShip_money() {
                return ship_money;
            }

            public void setShip_money(String ship_money) {
                this.ship_money = ship_money;
            }
        }
    }
}
