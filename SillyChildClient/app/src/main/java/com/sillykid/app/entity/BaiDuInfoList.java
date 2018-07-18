package com.sillykid.app.entity;

import java.util.List;

public class BaiDuInfoList {


    /**
     * status : 0
     * message : OK
     * pois : [{"id":"","geotable_id":""},{"id":"","geotable_id":""}]
     */

    private int status;
    private String message;
    private int size;
    private List<PoisBean> pois;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PoisBean> getPois() {
        return pois;
    }

    public void setPois(List<PoisBean> pois) {
        this.pois = pois;
    }

    public class PoisBean {
        /**
         * id :
         * geotable_id :
         */

        private String id;
        private String geotable_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGeotable_id() {
            return geotable_id;
        }

        public void setGeotable_id(String geotable_id) {
            this.geotable_id = geotable_id;
        }
    }
}
