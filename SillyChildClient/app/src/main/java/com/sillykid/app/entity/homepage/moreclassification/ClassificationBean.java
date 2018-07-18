package com.sillykid.app.entity.homepage.moreclassification;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ClassificationBean extends BaseResult<List<ClassificationBean.DataBean>> {

    public class DataBean {
        /**
         * cat_id : 2
         * name : 休闲零食
         * parent_id : 1
         * image :
         * level : 2
         */

        private int cat_id;
        private String name;
        private int parent_id;
        private String image;
        private int level;

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
