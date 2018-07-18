package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Administrator on 2017/9/28.
 */

public class CompensationChangeBackBean extends BaseResult<CompensationChangeBackBean.ResultBean> {


    /**
     * status : 1
     * msg : 返回成功
     * result : {"title":"费用补偿","content":"这里是费用补偿<\/p>"}
     */

    public class ResultBean {
        /**
         * title : 费用补偿
         * content : 这里是费用补偿</p>
         */

        private String title;
        private String content;

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
    }
}
