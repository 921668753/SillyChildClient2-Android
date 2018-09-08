package com.sillykid.app.entity.homepage.boutiqueline;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class LineDetailsBean extends BaseResult<LineDetailsBean.DataBean> {


    public class DataBean {
        /**
         * id : 4
         * main_picture : http://img.shahaizhi.com/%28null%291530269095
         * product_name : 大阪3天2夜精选路线
         * subtitle : 体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点 体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点
         * recommended : 4.8
         * product_description : <p style="text-align: center;"><span style="font-size: 30px;">第一天</span></p><p><span style="font-size: 20px;">线路摘要&nbsp;&nbsp;&nbsp;&nbsp;</span>体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮</p><p></br</p><p style="text-align: center;"><span style="font-size: 20px;">第一站</span></br></p><p style="text-align: center;"><img src="http://img.shahaizhi.com/%28null%291530254045" width="686" height="293"></p>
         * price : 0.01
         * review_count : 1
         * review_list : [{"id":7,"content":"图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张","satisfaction_level":4,"create_time":"1535357219","nickname":"天若有情","face":"http://img.shahaizhi.com/FleRaZdDvarW8yR3IOxH0Ti-LqZ2","like_number":0,"is_like":false}]
         */

        private int id;
        private int baggage_number;
        private int passenger_number;
        private String main_picture;
        private String product_name;
        private String subtitle;
        private String recommended;
        private String product_description;
        private String service_policy_content;
        private String service_policy_title;
        private String price;
        private int review_count;
        private List<ReviewListBean> review_list;

        public int getBaggage_number() {
            return baggage_number;
        }

        public void setBaggage_number(int baggage_number) {
            this.baggage_number = baggage_number;
        }

        public int getPassenger_number() {
            return passenger_number;
        }

        public void setPassenger_number(int passenger_number) {
            this.passenger_number = passenger_number;
        }

        public String getService_policy_content() {
            return service_policy_content;
        }

        public void setService_policy_content(String service_policy_content) {
            this.service_policy_content = service_policy_content;
        }

        public String getService_policy_title() {
            return service_policy_title;
        }

        public void setService_policy_title(String service_policy_title) {
            this.service_policy_title = service_policy_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain_picture() {
            return main_picture;
        }

        public void setMain_picture(String main_picture) {
            this.main_picture = main_picture;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
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

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public List<ReviewListBean> getReview_list() {
            return review_list;
        }

        public void setReview_list(List<ReviewListBean> review_list) {
            this.review_list = review_list;
        }

        public class ReviewListBean {
            /**
             * id : 7
             * content : 图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张
             * satisfaction_level : 4
             * create_time : 1535357219
             * nickname : 天若有情
             * face : http://img.shahaizhi.com/FleRaZdDvarW8yR3IOxH0Ti-LqZ2
             * like_number : 0
             * is_like : false
             */

            private int id;
            private String content;
            private int satisfaction_level;
            private String create_time;
            private String nickname;
            private String face;
            private int like_number;
            private boolean is_like;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getSatisfaction_level() {
                return satisfaction_level;
            }

            public void setSatisfaction_level(int satisfaction_level) {
                this.satisfaction_level = satisfaction_level;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getLike_number() {
                return like_number;
            }

            public void setLike_number(int like_number) {
                this.like_number = like_number;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }
        }
    }
}
