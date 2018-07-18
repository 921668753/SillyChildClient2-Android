package com.sillykid.app.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/29.
 */

public class ActivitiesBean extends BaseResult<ActivitiesBean.DataBean> {


    public class DataBean {
        private List<SpecialBean> special;
        private List<MonthHotBean> monthHot;

        public List<SpecialBean> getSpecial() {
            return special;
        }

        public void setSpecial(List<SpecialBean> special) {
            this.special = special;
        }

        public List<MonthHotBean> getMonthHot() {
            return monthHot;
        }

        public void setMonthHot(List<MonthHotBean> monthHot) {
            this.monthHot = monthHot;
        }

        public class SpecialBean {
            /**
             * goods_id : 133
             * name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
             * thumbnail : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg
             * price : 1298
             * mktprice : 1698
             * view_count : null
             * buy_count : null
             * store : null
             * sn : null
             * big : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_big.jpg
             * small : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_small.jpg
             * original : null
             * commentCount : null
             * commentPercent : null
             * brief : null
             * store_name :
             * brand_name :
             * goods_tag : 特价商品
             */

            private int goods_id;
            private String name;
            private String thumbnail;
            private String price;
            private String mktprice;
            private String view_count;
            private String buy_count;
            private String store;
            private String sn;
            private String big;
            private String small;
            private String original;
            private String commentCount;
            private String commentPercent;
            private String brief;
            private String store_name;
            private String brand_name;
            private String goods_tag;
            private String status;

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

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMktprice() {
                return mktprice;
            }

            public void setMktprice(String mktprice) {
                this.mktprice = mktprice;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getCommentPercent() {
                return commentPercent;
            }

            public void setCommentPercent(String commentPercent) {
                this.commentPercent = commentPercent;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_tag() {
                return goods_tag;
            }

            public void setGoods_tag(String goods_tag) {
                this.goods_tag = goods_tag;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public class MonthHotBean {
            /**
             * goods_id : 134
             * name : NPKM2017春夏季新款韩版低帮系带单鞋平跟舒适帆布鞋百搭
             * thumbnail : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//52541637_thumbnail.jpg
             * price : 1189
             * mktprice : 2945
             * view_count : null
             * buy_count : null
             * store : null
             * sn : null
             * big : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//52541637_big.jpg
             * small : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//52541637_small.jpg
             * original : null
             * commentCount : null
             * commentPercent : null
             * brief : null
             * store_name :
             * brand_name :
             * goods_tag : 特价商品
             */

            private int goods_id;
            private String name;
            private String thumbnail;
            private String price;
            private String mktprice;
            private String view_count;
            private String buy_count;
            private String store;
            private String sn;
            private String big;
            private String small;
            private String original;
            private String commentCount;
            private String commentPercent;
            private String brief;
            private String store_name;
            private String brand_name;
            private String goods_tag;
            private int width;
            private int height;


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

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMktprice() {
                return mktprice;
            }

            public void setMktprice(String mktprice) {
                this.mktprice = mktprice;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getCommentPercent() {
                return commentPercent;
            }

            public void setCommentPercent(String commentPercent) {
                this.commentPercent = commentPercent;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_tag() {
                return goods_tag;
            }

            public void setGoods_tag(String goods_tag) {
                this.goods_tag = goods_tag;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}




