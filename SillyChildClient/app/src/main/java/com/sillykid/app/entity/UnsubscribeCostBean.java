package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/11/9.
 */

public class UnsubscribeCostBean extends BaseResult<UnsubscribeCostBean.ResultBean> {


    public class ResultBean {
        /**
         * policy : {"title":"单次接送严格退订政策","content":"<p><img src=\"http://img.shahaizi.cn/2e1cb201711090900555650.png\" title=\"\" alt=\"\"/><\/p>"}
         * explain : {"title":"单次接送费用说明","content":"<p>这里是单次接送费用说明<\/p>"}
         */

        private PolicyBean policy;
        private ExplainBean explain;

        public PolicyBean getPolicy() {
            return policy;
        }

        public void setPolicy(PolicyBean policy) {
            this.policy = policy;
        }

        public ExplainBean getExplain() {
            return explain;
        }

        public void setExplain(ExplainBean explain) {
            this.explain = explain;
        }

        public class PolicyBean {
            /**
             * title : 单次接送严格退订政策
             * content : <p><img src="http://img.shahaizi.cn/2e1cb201711090900555650.png" title="" alt=""/></p>
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

        public class ExplainBean {
            /**
             * title : 单次接送费用说明
             * content : <p>这里是单次接送费用说明</p>
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
}
