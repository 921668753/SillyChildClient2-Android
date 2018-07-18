package com.sillykid.app.entity;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by Admin on 2017/9/11.
 */

public class InlandHotCityBean extends BaseIndexPinyinBean {


    //悬停ItemDecoration显示的Tag
    private String suspensionTag;
    /**
     * status : 1
     * msg : 成功
     * result : [{"id":3103,"name":"太原市","level":2,"parent_id":3102,"is_hot":1,"country_id":7},{"id":10809,"name":"南京市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":10960,"name":"无锡市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":11067,"name":"徐州市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":11245,"name":"常州市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":11348,"name":"苏州市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":11786,"name":"淮安市","level":2,"parent_id":10808,"is_hot":1,"country_id":7},{"id":16278,"name":"厦门市","level":2,"parent_id":16068,"is_hot":1,"country_id":7},{"id":25580,"name":"长沙市","level":2,"parent_id":25579,"is_hot":1,"country_id":7}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

    public InlandHotCityBean() {
    }

    public InlandHotCityBean(List<ResultBean> cityList, String suspensionTag, String indexBarTag) {
        this.result = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<ResultBean> getCityList() {
        return result;
    }

    public InlandHotCityBean setCityList(List<ResultBean> cityList) {
        this.result = cityList;
        return this;
    }

    public InlandHotCityBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


    public static class ResultBean {
        /**
         * id : 3103
         * name : 太原市
         * level : 2
         * parent_id : 3102
         * is_hot : 1
         * country_id : 7
         */

        private int id;
        private String name;
        private int level;
        private int parent_id;
        private int is_hot;
        private int country_id;
        private String country;

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

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
