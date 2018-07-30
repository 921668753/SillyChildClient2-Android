package com.sillykid.app.entity.homepage.hotvideo;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class HotVideoBean extends BaseResult<HotVideoBean.DataBean> {


    public class DataBean {
        /**
         * result : [{"id":1,"video_title":"傻孩志在土耳其","video_description":"本视频内容由樱蓝公司提供","video_image":"http://ovwiqces1.bkt.clouddn.com/FmmFKBC8Jrrlt0TUNoAtpQ-ZoORa","video_url":"http://ovwiqces1.bkt.clouddn.com/lg80xQZKfudCwqqbpVDW8QVFdPqN","create_time":"2018-07-08 13:18:14","watch_number":100},{"id":2,"video_title":"傻孩志在日本","video_description":"是一个日本视频下","video_image":"http://ovwiqces1.bkt.clouddn.com/FtErx-MvIjJRgzlmY8CMuhQfazIs","video_url":"http://ovwiqces1.bkt.clouddn.com/lvwS4magXtUlIR48OS-JdBS159xw","create_time":"2018-07-08 13:18:14","watch_number":101},{"id":3,"video_title":"傻孩志在摩洛哥","video_description":"摩洛哥夜市","video_image":"http://ovwiqces1.bkt.clouddn.com/FmDy-A7IqzfD72FmvS1ez2EK0NGM","video_url":"http://ovwiqces1.bkt.clouddn.com/ltGRKvaWVSMlXQu0hYqNGS7xzqHm","create_time":"2018-07-08 13:18:14","watch_number":104}]
         * pageno : 1
         * totalPages : 1
         * pagesize : 10
         */

        private int pageno;
        private int totalPages;
        private int pagesize;
        private List<ResultBean> result;

        public int getPageno() {
            return pageno;
        }

        public void setPageno(int pageno) {
            this.pageno = pageno;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public class ResultBean {
            /**
             * id : 1
             * video_title : 傻孩志在土耳其
             * video_description : 本视频内容由樱蓝公司提供
             * video_image : http://ovwiqces1.bkt.clouddn.com/FmmFKBC8Jrrlt0TUNoAtpQ-ZoORa
             * video_url : http://ovwiqces1.bkt.clouddn.com/lg80xQZKfudCwqqbpVDW8QVFdPqN
             * create_time : 2018-07-08 13:18:14
             * watch_number : 100
             */

            private int id;
            private String video_title;
            private String video_description;
            private String video_image;
            private String video_url;
            private String create_time;
            private String watch_number;
            private String collect_number;
            private String concern_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getVideo_image() {
                return video_image;
            }

            public void setVideo_image(String video_image) {
                this.video_image = video_image;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getWatch_number() {
                return watch_number;
            }

            public void setWatch_number(String watch_number) {
                this.watch_number = watch_number;
            }

            public String getCollect_number() {
                return collect_number;
            }

            public void setCollect_number(String collect_number) {
                this.collect_number = collect_number;
            }

            public String getConcern_number() {
                return concern_number;
            }

            public void setConcern_number(String concern_number) {
                this.concern_number = concern_number;
            }
        }
    }
}
