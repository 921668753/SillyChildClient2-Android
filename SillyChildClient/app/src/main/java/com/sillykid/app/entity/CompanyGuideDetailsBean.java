package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/10/24.
 */

public class CompanyGuideDetailsBean extends BaseResult<CompanyGuideDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * preson_info : {"seller_id":3,"cover_img":"http://img.shahaizi.cn/085a24fa7f010bf534b3ae10c58e027c.jpeg","drv_id":3,"drv_code":"20171020-3","head_pic":"http://img.shahaizi.cn/e54375962f5b1110896f1f6e6b191921.jpeg","seller_name":"","content":"啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦","country":null,"putonghua":null,"language":"匈牙利","type_info":"司导"}
         * comment_info : null
         * photo_type : []
         * my_story : []
         * my_line : []
         * my_car : []
         */

        private PresonInfoBean preson_info;
        private Object comment_info;
        private List<?> photo_type;
        private List<?> my_story;
        private List<?> my_line;
        private List<?> my_car;

        public PresonInfoBean getPreson_info() {
            return preson_info;
        }

        public void setPreson_info(PresonInfoBean preson_info) {
            this.preson_info = preson_info;
        }

        public Object getComment_info() {
            return comment_info;
        }

        public void setComment_info(Object comment_info) {
            this.comment_info = comment_info;
        }

        public List<?> getPhoto_type() {
            return photo_type;
        }

        public void setPhoto_type(List<?> photo_type) {
            this.photo_type = photo_type;
        }

        public List<?> getMy_story() {
            return my_story;
        }

        public void setMy_story(List<?> my_story) {
            this.my_story = my_story;
        }

        public List<?> getMy_line() {
            return my_line;
        }

        public void setMy_line(List<?> my_line) {
            this.my_line = my_line;
        }

        public List<?> getMy_car() {
            return my_car;
        }

        public void setMy_car(List<?> my_car) {
            this.my_car = my_car;
        }

        public class PresonInfoBean {
            /**
             * seller_id : 3
             * cover_img : http://img.shahaizi.cn/085a24fa7f010bf534b3ae10c58e027c.jpeg
             * drv_id : 3
             * drv_code : 20171020-3
             * head_pic : http://img.shahaizi.cn/e54375962f5b1110896f1f6e6b191921.jpeg
             * seller_name :
             * content : 啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦
             * country : null
             * putonghua : null
             * language : 匈牙利
             * type_info : 司导
             */

            private int seller_id;
            private String cover_img;
            private int drv_id;
            private String drv_code;
            private String head_pic;
            private String seller_name;
            private String content;
            private Object country;
            private Object putonghua;
            private String language;
            private String type_info;

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public int getDrv_id() {
                return drv_id;
            }

            public void setDrv_id(int drv_id) {
                this.drv_id = drv_id;
            }

            public String getDrv_code() {
                return drv_code;
            }

            public void setDrv_code(String drv_code) {
                this.drv_code = drv_code;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getSeller_name() {
                return seller_name;
            }

            public void setSeller_name(String seller_name) {
                this.seller_name = seller_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getPutonghua() {
                return putonghua;
            }

            public void setPutonghua(Object putonghua) {
                this.putonghua = putonghua;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getType_info() {
                return type_info;
            }

            public void setType_info(String type_info) {
                this.type_info = type_info;
            }
        }
    }
}
