package com.sillykid.app.entity.mine.myshoppingcart;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;
import java.util.List;

public class MyShoppingCartBean extends BaseResult<MyShoppingCartBean.DataBean> {


    public class DataBean {
        /**
         * total : 2596
         * countNum : 2
         * count : 1
         * storelist : [{"store_id":5,"store_name":"时尚女装/搭配","goodslist":[{"id":56,"product_id":133,"goods_id":133,"name":"ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋","mktprice":1698,"price":1298,"coupPrice":1298,"subtotal":2596,"num":2,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg","point":0,"itemtype":0,"sn":"00029","addon":null,"specs":null,"catid":112,"others":{},"exchange":null,"unit":"","goods_type":0,"pmtList":null,"weight":599,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":5,"store_name":"时尚女装/搭配","goods_transfee_charge":1,"activity_type":0}]}]
         */

        private String total;
        private int countNum;
        private int count;
        private List<StorelistBean> storelist;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getCountNum() {
            return countNum;
        }

        public void setCountNum(int countNum) {
            this.countNum = countNum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<StorelistBean> getStorelist() {
            return storelist;
        }

        public void setStorelist(List<StorelistBean> storelist) {
            this.storelist = storelist;
        }

        public class StorelistBean {
            /**
             * store_id : 5
             * store_name : 时尚女装/搭配
             * goodslist : [{"id":56,"product_id":133,"goods_id":133,"name":"ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋","mktprice":1698,"price":1298,"coupPrice":1298,"subtotal":2596,"num":2,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg","point":0,"itemtype":0,"sn":"00029","addon":null,"specs":null,"catid":112,"others":{},"exchange":null,"unit":"","goods_type":0,"pmtList":null,"weight":599,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":5,"store_name":"时尚女装/搭配","goods_transfee_charge":1,"activity_type":0}]
             */

            private int store_id;
            private String store_name;
            private List<GoodslistBean> goodslist;

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public List<GoodslistBean> getGoodslist() {
                return goodslist;
            }

            public void setGoodslist(List<GoodslistBean> goodslist) {
                this.goodslist = goodslist;
            }

            public class GoodslistBean implements Serializable {
                /**
                 * id : 56
                 * product_id : 133
                 * goods_id : 133
                 * name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
                 * mktprice : 1698
                 * price : 1298
                 * coupPrice : 1298
                 * subtotal : 2596
                 * num : 2
                 * limitnum : null
                 * image_default : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg
                 * point : 0
                 * itemtype : 0
                 * sn : 00029
                 * addon : null
                 * specs : null
                 * catid : 112
                 * others : {}
                 * exchange : null
                 * unit :
                 * goods_type : 0
                 * pmtList : null
                 * weight : 599
                 * activity_id : null
                 * is_check : 1
                 * snapshot_id : null
                 * store_id : 5
                 * store_name : 时尚女装/搭配
                 * goods_transfee_charge : 1
                 * activity_type : 0
                 */

                private int id;
                private int product_id;
                private int goods_id;
                private String name;
                private String mktprice;
                private String price;
                private String coupPrice;
                private String subtotal;
                private int num;
                private String image_default;
                private String sn;
                private String specs;
                private int catid;
                private int is_check;
                private int store_id;
                private String store_name;
                private int isSelected;
                private int isEdit;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMktprice() {
                    return mktprice;
                }

                public void setMktprice(String mktprice) {
                    this.mktprice = mktprice;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getCoupPrice() {
                    return coupPrice;
                }

                public void setCoupPrice(String coupPrice) {
                    this.coupPrice = coupPrice;
                }

                public String getSubtotal() {
                    return subtotal;
                }

                public void setSubtotal(String subtotal) {
                    this.subtotal = subtotal;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public String getSpecs() {
                    return specs;
                }

                public void setSpecs(String specs) {
                    this.specs = specs;
                }

                public int getCatid() {
                    return catid;
                }

                public void setCatid(int catid) {
                    this.catid = catid;
                }

                public int getIs_check() {
                    return is_check;
                }

                public void setIs_check(int is_check) {
                    this.is_check = is_check;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public int getIsSelected() {
                    return isSelected;
                }

                public void setIsSelected(int isSelected) {
                    this.isSelected = isSelected;
                }

                public int getIsEdit() {
                    return isEdit;
                }

                public void setIsEdit(int isEdit) {
                    this.isEdit = isEdit;
                }

                public class OthersBean implements Serializable {
                }
            }
        }
    }
}
