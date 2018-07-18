package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class SystemMessageBean extends BaseResult<SystemMessageBean.ResultBean> {


    public class ResultBean {
        /**
         * p : 1
         * pageSize : 20
         * totalRows : 1
         * totalPages : 1
         * list : [{"id":1,"title":"消息标题","message":"我123123","push_users":"1,","create_at":1508730051,"content":"&lt;p&gt;&lt;span style=&quot;color: rgb(255, 0, 0);&quot;&gt;13z2x4c56asd&lt;/span&gt;&lt;/p&gt;"}]
         */

        private int unread;
        private int totalPages;
        private List<ListBean> list;

        public int getUnread() {
            return unread;
        }

        public void setUnread(int unread) {
            this.unread = unread;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 1
             * title : 消息标题
             * message : 我123123
             * push_users : 1,
             * create_at : 1508730051
             * content : &lt;p&gt;&lt;span style=&quot;color: rgb(255, 0, 0);&quot;&gt;13z2x4c56asd&lt;/span&gt;&lt;/p&gt;
             */

            private int id;
            private String title;
            private int is_read;
            private String message;
            private String push_users;
            private String create_at;
            private String content;
            private String article_type;
            private String article_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getPush_users() {
                return push_users;
            }

            public void setPush_users(String push_users) {
                this.push_users = push_users;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getArticle_type() {
                return article_type;
            }

            public void setArticle_type(String article_type) {
                this.article_type = article_type;
            }

            public String getArticle_id() {
                return article_id;
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }
        }
    }
}
