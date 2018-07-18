package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class MallHomeCategoryBean extends BaseResult<MallHomeCategoryBean.ResultBean> {

    public class ResultBean {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * avatar : 2222
             * name : 2222
             * bonus : 2222
             */

            private int categoryicon;
            private String categoryname;

            public int getCategoryicon() {
                return categoryicon;
            }

            public void setCategoryicon(int categoryicon) {
                this.categoryicon = categoryicon;
            }

            public String getCategoryname() {
                return categoryname;
            }

            public void setCategoryname(String categoryname) {
                this.categoryname = categoryname;
            }
        }
    }


}
