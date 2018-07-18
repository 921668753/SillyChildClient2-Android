package com.sillykid.app.entity;


import com.common.cklibrary.entity.BaseResult;

import java.util.List;
import com.sillykid.app.entity.FeedBackTypeBean.ResultBean;

/**
 * Created by Admin on 2017/7/27.
 */

public class FeedBackTypeBean extends BaseResult<ResultBean> {


    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * name : 体验问题
             * selector : 区别是否选中
             */

            private int id;
            private String name;
            private boolean selector;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isSelector() {
                return selector;
            }

            public void setSelector(boolean selector) {
                this.selector = selector;
            }
        }
    }
}
