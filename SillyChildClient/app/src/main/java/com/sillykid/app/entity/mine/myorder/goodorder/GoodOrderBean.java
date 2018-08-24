package com.sillykid.app.entity.mine.myorder.goodorder;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodOrderBean extends BaseResult<GoodOrderBean.DataBean> {


    public class DataBean {
        /**
         * result : [{"orderId":205,"paymoney":0,"sn":"DD152812329579-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":80,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":205}],"itemsCount":1,"status":1},{"orderId":203,"paymoney":0,"sn":"DD152812321571-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":79,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":203}],"itemsCount":1,"status":1}]
         * pageSize : 20
         * totalPageCount : 1
         * draw : 0
         * totalCount : 2
         * currentPageNo : 1
         */

        private int pageSize;
        private int totalPageCount;
        private int draw;
        private int totalCount;
        private int currentPageNo;
        @SerializedName("result")
        private List<ResultBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * orderId : 205
             * paymoney : 0
             * sn : DD152812329579-1
             * orderItems : [{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":80,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":205}]
             * itemsCount : 1
             * status : 1
             */

            private int orderId;
            private int commented;
            private String paymoney;
            private String sn;
            private String create_time;
            private String system_time;
            private String need_pay_money;
            private String last_time;
            private int itemsCount;
            private int status;

            public int getCommented() {
                return commented;
            }

            public void setCommented(int commented) {
                this.commented = commented;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getSystem_time() {
                return system_time;
            }

            public void setSystem_time(String system_time) {
                this.system_time = system_time;
            }

            private List<OrderItemsBean> orderItems;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(String paymoney) {
                this.paymoney = paymoney;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getLast_time() {
                return last_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public int getItemsCount() {
                return itemsCount;
            }

            public void setItemsCount(int itemsCount) {
                this.itemsCount = itemsCount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<OrderItemsBean> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<OrderItemsBean> orderItems) {
                this.orderItems = orderItems;
            }

            public String getNeed_pay_money() {
                return need_pay_money;
            }

            public void setNeed_pay_money(String need_pay_money) {
                this.need_pay_money = need_pay_money;
            }

            public class OrderItemsBean {
                /**
                 * image : http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg
                 * item_id : 80
                 * snapshot_id : 21
                 * num : 1
                 * goods_id : 157
                 * gainedpoint : 0
                 * ship_num : 0
                 * unit :
                 * price : 2250
                 * product_id : 157
                 * cat_id : 164
                 * name : 正品女表时尚手表真皮镶钻石英表
                 * goods_type : 0
                 * sn : 00051
                 * state : 0
                 * fields : {}
                 * order_id : 205
                 */

                private String image;
                private int item_id;
                private int snapshot_id;
                private int num;
                private int goods_id;
                private int gainedpoint;
                private int ship_num;
                private String unit;
                private String price;
                private int product_id;
                private int cat_id;
                private String name;
                private int goods_type;
                private String sn;
                private int state;
                private int sellback_state;
                private FieldsBean fields;
                private int order_id;
                private String specs;

                public int getSellback_state() {
                    return sellback_state;
                }

                public void setSellback_state(int sellback_state) {
                    this.sellback_state = sellback_state;
                }

                public String getSpecs() {
                    return specs;
                }

                public void setSpecs(String specs) {
                    this.specs = specs;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public int getSnapshot_id() {
                    return snapshot_id;
                }

                public void setSnapshot_id(int snapshot_id) {
                    this.snapshot_id = snapshot_id;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getGainedpoint() {
                    return gainedpoint;
                }

                public void setGainedpoint(int gainedpoint) {
                    this.gainedpoint = gainedpoint;
                }

                public int getShip_num() {
                    return ship_num;
                }

                public void setShip_num(int ship_num) {
                    this.ship_num = ship_num;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public int getCat_id() {
                    return cat_id;
                }

                public void setCat_id(int cat_id) {
                    this.cat_id = cat_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(int goods_type) {
                    this.goods_type = goods_type;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public FieldsBean getFields() {
                    return fields;
                }

                public void setFields(FieldsBean fields) {
                    this.fields = fields;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }

                public class FieldsBean {
                }
            }
        }
    }
}
