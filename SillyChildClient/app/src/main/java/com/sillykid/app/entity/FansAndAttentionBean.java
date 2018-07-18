package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class FansAndAttentionBean extends BaseResult<FansAndAttentionBean.ResultBean> {


    public static class ResultBean {
        /**
         * p : 1
         * pageSize : 30
         * totalRows : 1
         * totalPages : 1
         * list : [{"user_id":26,"attention_num":0,"nickname":"cynthiabx","head_pic":"http://img.shahaizi.cn/6efb1992e0b25bd2e6292ba66daf54a0.jpg"}]
         */

        private int p;
        private String pageSize;
        private int totalRows;
        private int totalPages;
        private List<ListBean> list;

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
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

        public static class ListBean {
            /**
             * user_id : 26
             * attention_num : 0
             * nickname : cynthiabx
             * head_pic : http://img.shahaizi.cn/6efb1992e0b25bd2e6292ba66daf54a0.jpg
             * is_attention :  0:未关注；1：已关注
             */

            private int user_id;
            private String attention_num;
            private String nickname;
            private String head_pic;
            private int is_attention;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getAttention_num() {
                return attention_num;
            }

            public void setAttention_num(String attention_num) {
                this.attention_num = attention_num;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public int getIs_attention() {
                return is_attention;
            }

            public void setIs_attention(int is_attention) {
                this.is_attention = is_attention;
            }
        }
    }
}
