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

            private int is_size;
            private String product_name;
            private String main_picture;
            private int product_id;
            private String subtitle;
            private String recommended;

            public int getIs_size() {
                return is_size;
            }

            public void setIs_size(int is_size) {
                this.is_size = is_size;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getMain_picture() {
                return main_picture;
            }

            public void setMain_picture(String main_picture) {
                this.main_picture = main_picture;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getRecommended() {
                return recommended;
            }

            public void setRecommended(String recommended) {
                this.recommended = recommended;
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