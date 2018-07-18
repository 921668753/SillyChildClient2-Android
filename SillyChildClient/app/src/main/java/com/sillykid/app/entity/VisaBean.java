package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/10/23.
 */

public class VisaBean extends BaseResult<List<VisaBean.ResultBean>> {

    public class ResultBean {
        /**
         * id : 1
         * parent_id : 0
         * name : 亚洲
         * level : 1
         * area_code : null
         * is_hot : 0
         * list : [{"id":7,"parent_id":1,"name":"中国","level":2,"area_code":null,"is_hot":1},{"id":8,"parent_id":1,"name":"日本","level":2,"area_code":null,"is_hot":1},{"id":17,"parent_id":1,"name":"港澳台","level":2,"area_code":null,"is_hot":0},{"id":28,"parent_id":1,"name":"韩国","level":2,"area_code":null,"is_hot":1},{"id":29,"parent_id":1,"name":"缅甸","level":2,"area_code":null,"is_hot":0}]
         */

        private int id;
        private int parent_id;
        private String name;
        private int level;
        private Object area_code;
        private int is_hot;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
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

        public Object getArea_code() {
            return area_code;
        }

        public void setArea_code(Object area_code) {
            this.area_code = area_code;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 7
             * parent_id : 1
             * name : 中国
             * level : 2
             * area_code : null
             * is_hot : 1
             */

            private int id;
            private int parent_id;
            private String name;
            private int level;
            private Object area_code;
            private int is_hot;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
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

            public Object getArea_code() {
                return area_code;
            }

            public void setArea_code(Object area_code) {
                this.area_code = area_code;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }
        }
    }
}
