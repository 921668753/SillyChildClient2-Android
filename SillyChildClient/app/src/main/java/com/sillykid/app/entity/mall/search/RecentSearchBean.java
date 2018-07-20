package com.sillykid.app.entity.mall.search;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class RecentSearchBean extends BaseResult<List<RecentSearchBean.DataBean>> {

    public static class DataBean {
        /**
         * cat_id : 2
         * name : 休闲零食
         * parent_id : 1
         * image :
         * level : 2
         */
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
