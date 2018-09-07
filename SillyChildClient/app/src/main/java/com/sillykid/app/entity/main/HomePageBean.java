package com.sillykid.app.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/9/7.
 */
public class HomePageBean extends BaseResult<HomePageBean.DataBean> {


    public class DataBean {
        private List<GoodsListBean> goods_list;
        private List<BannerListBean> banner_list;
        private List<VideoListBean> video_list;

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public List<BannerListBean> getBanner_list() {
            return banner_list;
        }

        public void setBanner_list(List<BannerListBean> banner_list) {
            this.banner_list = banner_list;
        }

        public List<VideoListBean> getVideo_list() {
            return video_list;
        }

        public void setVideo_list(List<VideoListBean> video_list) {
            this.video_list = video_list;
        }

        public class GoodsListBean {
            /**
             * small : http://img.shahaizhi.com//attachment//store/5/goods/2018/6/26/14//35413158.jpg
             * brief : 美丽的东南亚海岛风情，给你带来不一样的体验。
             * big : http://img.shahaizhi.com//attachment//store/5/goods/2018/6/26/14//35413158.jpg
             * thumbnail : http://img.shahaizhi.com/attachment//store/5/goods/2018/6/26/14//35413158.jpg
             * original : http://img.shahaizhi.com//attachment//store/5/goods/2018/6/26/14//35413158.jpg
             * buy_count : 3
             * goods_id : 30
             * brand_name :
             * is_size : 0
             * store : 100
             * goods_tag :
             * price : 4999
             * name : 曼芭七日游
             * store_name :
             * sn : 004
             * collect_number : 0
             * view_count : 0
             * mktprice : 5899
             */

            private String small;
            private String brief;
            private String big;
            private String thumbnail;
            private String original;
            private int buy_count;
            private int goods_id;
            private String brand_name;
            private int is_size;
            private int store;
            private String goods_tag;
            private String price;
            private String name;
            private String store_name;
            private String sn;
            private String collect_number;
            private int view_count;
            private String mktprice;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public int getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(int buy_count) {
                this.buy_count = buy_count;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public int getIs_size() {
                return is_size;
            }

            public void setIs_size(int is_size) {
                this.is_size = is_size;
            }

            public int getStore() {
                return store;
            }

            public void setStore(int store) {
                this.store = store;
            }

            public String getGoods_tag() {
                return goods_tag;
            }

            public void setGoods_tag(String goods_tag) {
                this.goods_tag = goods_tag;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getCollect_number() {
                return collect_number;
            }

            public void setCollect_number(String collect_number) {
                this.collect_number = collect_number;
            }

            public int getView_count() {
                return view_count;
            }

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }

            public String getMktprice() {
                return mktprice;
            }

            public void setMktprice(String mktprice) {
                this.mktprice = mktprice;
            }
        }

        public class BannerListBean {
            /**
             * id : 1
             * image_url : http://img.shahaizhi.com/FtErx-MvIjJRgzlmY8CMuhQfazIs?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             * image_link_url : http://www.shahaizhi.com
             * create_time : 2018-07-30 11:47:30
             * create_name : admin
             * is_show : 1
             */

            private int id;
            private String image_url;
            private String image_link_url;
            private String create_time;
            private String create_name;
            private int is_show;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getImage_link_url() {
                return image_link_url;
            }

            public void setImage_link_url(String image_link_url) {
                this.image_link_url = image_link_url;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCreate_name() {
                return create_name;
            }

            public void setCreate_name(String create_name) {
                this.create_name = create_name;
            }

            public int getIs_show() {
                return is_show;
            }

            public void setIs_show(int is_show) {
                this.is_show = is_show;
            }
        }

        public class VideoListBean {
            /**
             * id : 4
             * video_url : http://img.shahaizhi.com/390eea494f2aee4e7a2b0318ff80cae2.mp4
             * video_image : http://img.shahaizhi.com/390eea494f2aee4e7a2b0318ff80cae2.mp4?vframe/jpg/offset/0
             * create_time : 2018-08-29 10:03:20
             * video_title : 傻孩志
             * video_description : 傻孩志在摩洛哥
             * is_recommend : 1
             */

            private int id;
            private String video_url;
            private String video_image;
            private String create_time;
            private String video_title;
            private String statusL;
            private String video_description;
            private int is_recommend;

            public String getStatusL() {
                return statusL;
            }

            public void setStatusL(String statusL) {
                this.statusL = statusL;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public String getVideo_image() {
                return video_image;
            }

            public void setVideo_image(String video_image) {
                this.video_image = video_image;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getVideo_title() {
                return video_title;
            }

            public void setVideo_title(String video_title) {
                this.video_title = video_title;
            }

            public String getVideo_description() {
                return video_description;
            }

            public void setVideo_description(String video_description) {
                this.video_description = video_description;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }
        }
    }
}