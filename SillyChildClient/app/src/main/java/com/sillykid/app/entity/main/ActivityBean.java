package com.sillykid.app.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ActivityBean extends BaseResult<ActivityBean.DataBean> {


    public class DataBean {
        private List<ActivityListBean> activity_list;
        private List<BannerListBean> banner_list;

        public List<ActivityListBean> getActivity_list() {
            return activity_list;
        }

        public void setActivity_list(List<ActivityListBean> activity_list) {
            this.activity_list = activity_list;
        }

        public List<BannerListBean> getBanner_list() {
            return banner_list;
        }

        public void setBanner_list(List<BannerListBean> banner_list) {
            this.banner_list = banner_list;
        }

        public class ActivityListBean {
            /**
             * id : 1
             * main_picture : http://www.shahaizhi.com
             * title : 第一个活动
             * subtitle : 活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介
             * start_time : 1536841800
             * end_time : 1537360200
             */

            private int id;
            private int activity_state;
            private String main_picture;
            private String title;
            private String subtitle;
            private String start_time;
            private String end_time;
            private String service_id;
            private String service_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getActivity_state() {
                return activity_state;
            }

            public void setActivity_state(int activity_state) {
                this.activity_state = activity_state;
            }

            public String getMain_picture() {
                return main_picture;
            }

            public void setMain_picture(String main_picture) {
                this.main_picture = main_picture;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }
        }

        public class BannerListBean {
            /**
             * id : 1
             * picture : http://img.shahaizhi.com/cbd.jpg
             * picture_name : 轮播一
             * link_address : http://www.shahaizhi.com
             */

            private int id;
            private String picture;
            private String picture_name;
            private String link_address;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPicture_name() {
                return picture_name;
            }

            public void setPicture_name(String picture_name) {
                this.picture_name = picture_name;
            }

            public String getLink_address() {
                return link_address;
            }

            public void setLink_address(String link_address) {
                this.link_address = link_address;
            }
        }
    }
}
