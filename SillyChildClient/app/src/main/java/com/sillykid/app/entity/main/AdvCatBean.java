package com.sillykid.app.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class AdvCatBean extends BaseResult<List<AdvCatBean.DataBean>> {


    public class DataBean {
        /**
         * aid : 1
         * acid : 1
         * atype : 0
         * begintime : 1328025600000
         * endtime : 1614182400000
         * isclose : 0
         * attachment : null
         * atturl : http://static.b2b2cv2.javamall.com.cn/attachment/adv/2017/6/14/18//18424092.png
         * url : /search-cat-35.html
         * aname : 潮流季
         * clickcount : 0
         * disabled : false
         * linkman :
         * company :
         * contact :
         * cname :
         * httpAttUrl : http://static.b2b2cv2.javamall.com.cn/attachment/adv/2017/6/14/18//18424092.png
         */

        private int aid;
        private int acid;
        private int atype;
        private long begintime;
        private long endtime;
        private int isclose;
        private Object attachment;
        private String atturl;
        private String url;
        private String aname;
        private int clickcount;
        private String disabled;
        private String linkman;
        private String company;
        private String contact;
        private String cname;
        private String httpAttUrl;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getAcid() {
            return acid;
        }

        public void setAcid(int acid) {
            this.acid = acid;
        }

        public int getAtype() {
            return atype;
        }

        public void setAtype(int atype) {
            this.atype = atype;
        }

        public long getBegintime() {
            return begintime;
        }

        public void setBegintime(long begintime) {
            this.begintime = begintime;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public int getIsclose() {
            return isclose;
        }

        public void setIsclose(int isclose) {
            this.isclose = isclose;
        }

        public Object getAttachment() {
            return attachment;
        }

        public void setAttachment(Object attachment) {
            this.attachment = attachment;
        }

        public String getAtturl() {
            return atturl;
        }

        public void setAtturl(String atturl) {
            this.atturl = atturl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }

        public int getClickcount() {
            return clickcount;
        }

        public void setClickcount(int clickcount) {
            this.clickcount = clickcount;
        }

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getHttpAttUrl() {
            return httpAttUrl;
        }

        public void setHttpAttUrl(String httpAttUrl) {
            this.httpAttUrl = httpAttUrl;
        }
    }
}
