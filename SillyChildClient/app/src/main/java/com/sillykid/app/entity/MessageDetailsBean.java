package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * 消息詳情
 * Created by Administrator on 2017/5/30.
 */

public class MessageDetailsBean extends BaseResult<MessageDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 1000
         * type : all
         * title : 系统消息
         * content : 系统消息 全体会员注意
         * isRead : 1
         * pushTime :
         */

        private int id;
        private String type;
        private String title;
        private String content;
        private int isRead;
        private String pushTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }
    }
}
