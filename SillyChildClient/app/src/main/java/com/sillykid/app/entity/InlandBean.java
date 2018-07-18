package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by Admin on 2017/9/11.
 */

public class InlandBean extends BaseResult<List<InlandBean.ResultBean>> {


    public class ResultBean extends BaseIndexPinyinBean {
        /**
         * id : 1
         * name : 北京市
         * level : 1
         * parent_id : 0
         */

        private int id;
        private String name;
        private int level;
        private int parent_id;
        private int country_id;

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        @Override
        public String getTarget() {
            return name;
        }
    }
}
