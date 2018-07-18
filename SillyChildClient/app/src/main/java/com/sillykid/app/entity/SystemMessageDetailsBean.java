package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/10/27.
 */

public class SystemMessageDetailsBean extends BaseResult<SystemMessageDetailsBean.ResultBean> {

    public static class ResultBean {
        /**
         * id : 1
         * title : 消息标题
         * message : 我123123
         * push_users : 1,
         * create_at : 2017-10-23 11:40:51
         * content : <p><span style="color: rgb(255, 0, 0);">13z2x4c56asd</span></p>
         */

        private int id;
        private String title;
        private String message;
        private String push_users;
        private String create_at;
        private String content;

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
    }
}
