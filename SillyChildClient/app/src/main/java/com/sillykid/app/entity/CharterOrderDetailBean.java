package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 2017/7/27.
 */

public class CharterOrderDetailBean extends BaseResult<CharterOrderDetailBean.ResultBean > {

    public static class ResultBean {
        /**
         * air_id : 44                          订单id
         * order_sn : 201709231853257747       订单编号
         * user_id : 68                        用户id
         * seller_id : null                    司导id
         * allot_seller_id :
         * customer_name : sjjhb                顾客名字
         * customer_phone : 13733190537         顾客电话
         * use_car_adult : 2                    游客人数（或者成人）
         * use_car_children : 2                 儿童数量
         * work_at : 2017-09-23/2017-09-25      包车时间
         * work_pointlng : null                 出发地经度
         * work_pointlat : null                 出发地纬度
         * work_address :                       出发地地址
         * dest_pointlng : null                 目的地经度
         * dest_pointlat : null                 目的地纬度
         * dest_address :                       目的地地址
         * status : 0                           订单状态
         * pay_way : null                       支付方式
         * total_price : 56456                  总金额
         * total_price_fmt                      总金额格式
         * coupon_price : 0.00                  优惠金额
         * coupon_price_fmt : 0.00              优惠金额格式
         * real_price : 56456.00                实付价格
         * real_price_fmt                       实付价格格式
         * is_pay : 0
         * pay_time : null
         * start_time : null                    开始时间
         * end_time : null                      结束时间
         * add_time_long : null
         * add_recharge : 0
         * add_reason : null
         * is_agree_add : 0
         * refuse_add_remark : null
         * is_free_add : 0
         * drv_name : null
         * drv_id : null
         * drv_code : null
         * req_car_id : null
         * req_car_type : 大众                 需求车型
         * req_car_seat_num                    需求车座位数
         * con_car_id : null
         * con_car_type : null                  分派车型
         * con_car_seat_num : null
         * line_id : 3
         * type : 3                             订单类型
         * flt_no : null                        航班号
         * mile_length : null                   公里数
         * discount_id : 0
         * is_checked : null
         * user_message : 比较好喝毫升
         * create_at : 1506164005
         * update_at : 1506164005
         * title : 冲神                        线路订单的标题
         * is_use_car : 1
         * remark : null                        备注信息
         * drv_phone : null                     司机电话
         * dest_region : null
         * user_passport : zz158556655          护照号
         * user_identity : 411328199906332222   身份证号
         * twenty_four : 1                      24寸行李数量
         * twenty_six : 1                       26寸行李数量
         * twenty_eight : 1                     28寸行李数量
         * thirty : 1                           30寸行李数量
         * order_day : null                     游玩天数
         * eating_ave : null                    推荐餐馆
         * stay_ave : null                      推荐住宿
         * tour_favorite : null                 出行偏好
         * is_callback : 0
         * con_car_type_name : 大众
         * hx_user_name :                       私聊用的名称
         * nickname : 15250215762               私聊昵称
         * avatar :                             私聊头像
         * seller_order_status ：司导  0未评价，1已评价
         * user_order_status : 用户  0未评价，1已评价
         * pack_start_time  按天包车游的时间
         * user_confirm    0:用户未确定完成；1：用户确认完成
         * costStatement//    费用说明.
         * costCompensation// 补偿改退.
         * costCompensationLevel//补偿改退的等级
         */

        private String air_id;
        private String order_sn;
        private String user_id;
        private String seller_id;
        private String allot_seller_id;
        private String customer_name;
        private String customer_phone;
        private String use_car_adult;
        private String use_car_children;
        private String work_at;
        private String work_pointlng;
        private String work_pointlat;
        private String work_address;
        private String dest_pointlng;
        private String dest_pointlat;
        private String dest_address;
        @SerializedName("status")
        private int statusX;
        private Object pay_way;
        private String total_price;
        private String total_price_fmt;
        private String coupon_price;
        private String coupon_price_fmt;
        private String real_price;
        private String real_price_fmt;
        private int is_pay;
        private String pay_time;
        private String start_time;
        private String end_time;
        private String add_time_long;
        private int add_recharge;
        private String add_reason;
        private int is_agree_add;
        private String refuse_add_remark;
        private int is_free_add;
        private String drv_name;
        private String drv_id;
        private String drv_code;
        private String req_car_id;
        private String req_car_type;
        private String req_car_seat_num;
        private String con_car_id;
        private String con_car_type;
        private String con_car_seat_num;
        private String line_id;
        private int type;
        private String flt_no;
        private String mile_length;
        private int discount_id;
        private Object is_checked;
        private String user_message;
        private String create_at;
        private String update_at;
        private String title;
        private int is_use_car;
        private String remark;
        private String drv_phone;
        private String dest_region;
        private String user_passport;
        private String user_identity;
        private String twenty_four;
        private String twenty_six;
        private String twenty_eight;
        private String thirty;
        private String order_day;
        private String eating_ave;
        private String stay_ave;
        private String tour_favorite;
        private int is_callback;
        private String con_car_type_name;
        private String hx_user_name;
        private String nickname;
        private String avatar;
        private String seller_order_status;
        private String user_order_status;
        private String[] pack_start_time;
        private String user_confirm;
        private String costStatement; //    费用说明.
        private String costCompensation; // 补偿改退.
        private String costCompensationLevel;//补偿改退的等级
        private String car_level_name;//车型

        public String getAir_id() {
            return air_id;
        }

        public void setAir_id(String air_id) {
            this.air_id = air_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getAllot_seller_id() {
            return allot_seller_id;
        }

        public void setAllot_seller_id(String allot_seller_id) {
            this.allot_seller_id = allot_seller_id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public String getUse_car_adult() {
            return use_car_adult;
        }

        public void setUse_car_adult(String use_car_adult) {
            this.use_car_adult = use_car_adult;
        }

        public String getUse_car_children() {
            return use_car_children;
        }

        public void setUse_car_children(String use_car_children) {
            this.use_car_children = use_car_children;
        }

        public String getWork_at() {
            return work_at;
        }

        public void setWork_at(String work_at) {
            this.work_at = work_at;
        }

        public String getWork_pointlng() {
            return work_pointlng;
        }

        public void setWork_pointlng(String work_pointlng) {
            this.work_pointlng = work_pointlng;
        }

        public String getWork_pointlat() {
            return work_pointlat;
        }

        public void setWork_pointlat(String work_pointlat) {
            this.work_pointlat = work_pointlat;
        }

        public String getWork_address() {
            return work_address;
        }

        public void setWork_address(String work_address) {
            this.work_address = work_address;
        }

        public String getDest_pointlng() {
            return dest_pointlng;
        }

        public void setDest_pointlng(String dest_pointlng) {
            this.dest_pointlng = dest_pointlng;
        }

        public String getDest_pointlat() {
            return dest_pointlat;
        }

        public void setDest_pointlat(String dest_pointlat) {
            this.dest_pointlat = dest_pointlat;
        }

        public String getDest_address() {
            return dest_address;
        }

        public void setDest_address(String dest_address) {
            this.dest_address = dest_address;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public Object getPay_way() {
            return pay_way;
        }

        public void setPay_way(Object pay_way) {
            this.pay_way = pay_way;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getTotal_price_fmt() {
            return total_price_fmt;
        }

        public void setTotal_price_fmt(String total_price_fmt) {
            this.total_price_fmt = total_price_fmt;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getCoupon_price_fmt() {
            return coupon_price_fmt;
        }

        public void setCoupon_price_fmt(String coupon_price_fmt) {
            this.coupon_price_fmt = coupon_price_fmt;
        }

        public String getReal_price() {
            return real_price;
        }

        public void setReal_price(String real_price) {
            this.real_price = real_price;
        }

        public String getReal_price_fmt() {
            return real_price_fmt;
        }

        public void setReal_price_fmt(String real_price_fmt) {
            this.real_price_fmt = real_price_fmt;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAdd_time_long() {
            return add_time_long;
        }

        public void setAdd_time_long(String add_time_long) {
            this.add_time_long = add_time_long;
        }

        public int getAdd_recharge() {
            return add_recharge;
        }

        public void setAdd_recharge(int add_recharge) {
            this.add_recharge = add_recharge;
        }

        public String getAdd_reason() {
            return add_reason;
        }

        public void setAdd_reason(String add_reason) {
            this.add_reason = add_reason;
        }

        public int getIs_agree_add() {
            return is_agree_add;
        }

        public void setIs_agree_add(int is_agree_add) {
            this.is_agree_add = is_agree_add;
        }

        public String getRefuse_add_remark() {
            return refuse_add_remark;
        }

        public void setRefuse_add_remark(String refuse_add_remark) {
            this.refuse_add_remark = refuse_add_remark;
        }

        public int getIs_free_add() {
            return is_free_add;
        }

        public void setIs_free_add(int is_free_add) {
            this.is_free_add = is_free_add;
        }

        public String getDrv_name() {
            return drv_name;
        }

        public void setDrv_name(String drv_name) {
            this.drv_name = drv_name;
        }

        public String getDrv_id() {
            return drv_id;
        }

        public void setDrv_id(String drv_id) {
            this.drv_id = drv_id;
        }

        public String getDrv_code() {
            return drv_code;
        }

        public void setDrv_code(String drv_code) {
            this.drv_code = drv_code;
        }

        public String getReq_car_id() {
            return req_car_id;
        }

        public void setReq_car_id(String req_car_id) {
            this.req_car_id = req_car_id;
        }

        public String getReq_car_type() {
            return req_car_type;
        }

        public void setReq_car_type(String req_car_type) {
            this.req_car_type = req_car_type;
        }

        public String getReq_car_seat_num() {
            return req_car_seat_num;
        }

        public void setReq_car_seat_num(String req_car_seat_num) {
            this.req_car_seat_num = req_car_seat_num;
        }

        public String getCon_car_id() {
            return con_car_id;
        }

        public void setCon_car_id(String con_car_id) {
            this.con_car_id = con_car_id;
        }

        public String getCon_car_type() {
            return con_car_type;
        }

        public void setCon_car_type(String con_car_type) {
            this.con_car_type = con_car_type;
        }

        public String getCon_car_seat_num() {
            return con_car_seat_num;
        }

        public void setCon_car_seat_num(String con_car_seat_num) {
            this.con_car_seat_num = con_car_seat_num;
        }

        public String getLine_id() {
            return line_id;
        }

        public void setLine_id(String line_id) {
            this.line_id = line_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFlt_no() {
            return flt_no;
        }

        public void setFlt_no(String flt_no) {
            this.flt_no = flt_no;
        }

        public String getMile_length() {
            return mile_length;
        }

        public void setMile_length(String mile_length) {
            this.mile_length = mile_length;
        }

        public int getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(int discount_id) {
            this.discount_id = discount_id;
        }

        public Object getIs_checked() {
            return is_checked;
        }

        public void setIs_checked(Object is_checked) {
            this.is_checked = is_checked;
        }

        public String getUser_message() {
            return user_message;
        }

        public void setUser_message(String user_message) {
            this.user_message = user_message;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_use_car() {
            return is_use_car;
        }

        public void setIs_use_car(int is_use_car) {
            this.is_use_car = is_use_car;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDrv_phone() {
            return drv_phone;
        }

        public void setDrv_phone(String drv_phone) {
            this.drv_phone = drv_phone;
        }

        public String getDest_region() {
            return dest_region;
        }

        public void setDest_region(String dest_region) {
            this.dest_region = dest_region;
        }

        public String getUser_passport() {
            return user_passport;
        }

        public void setUser_passport(String user_passport) {
            this.user_passport = user_passport;
        }

        public String getUser_identity() {
            return user_identity;
        }

        public void setUser_identity(String user_identity) {
            this.user_identity = user_identity;
        }

        public String getTwenty_four() {
            return twenty_four;
        }

        public void setTwenty_four(String twenty_four) {
            this.twenty_four = twenty_four;
        }

        public String getTwenty_six() {
            return twenty_six;
        }

        public void setTwenty_six(String twenty_six) {
            this.twenty_six = twenty_six;
        }

        public String getTwenty_eight() {
            return twenty_eight;
        }

        public void setTwenty_eight(String twenty_eight) {
            this.twenty_eight = twenty_eight;
        }

        public String getThirty() {
            return thirty;
        }

        public void setThirty(String thirty) {
            this.thirty = thirty;
        }

        public String getOrder_day() {
            return order_day;
        }

        public void setOrder_day(String order_day) {
            this.order_day = order_day;
        }

        public String getEating_ave() {
            return eating_ave;
        }

        public void setEating_ave(String eating_ave) {
            this.eating_ave = eating_ave;
        }

        public String getStay_ave() {
            return stay_ave;
        }

        public void setStay_ave(String stay_ave) {
            this.stay_ave = stay_ave;
        }

        public String getTour_favorite() {
            return tour_favorite;
        }

        public void setTour_favorite(String tour_favorite) {
            this.tour_favorite = tour_favorite;
        }

        public int getIs_callback() {
            return is_callback;
        }

        public void setIs_callback(int is_callback) {
            this.is_callback = is_callback;
        }

        public String getCon_car_type_name() {
            return con_car_type_name;
        }

        public void setCon_car_type_name(String con_car_type_name) {
            this.con_car_type_name = con_car_type_name;
        }

        public String getHx_user_name() {
            return hx_user_name;
        }

        public void setHx_user_name(String hx_user_name) {
            this.hx_user_name = hx_user_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSeller_order_status() {
            return seller_order_status;
        }

        public void setSeller_order_status(String seller_order_status) {
            this.seller_order_status = seller_order_status;
        }

        public String getUser_order_status() {
            return user_order_status;
        }

        public void setUser_order_status(String user_order_status) {
            this.user_order_status = user_order_status;
        }

        public String[] getPack_start_time() {
            return pack_start_time;
        }

        public void setPack_start_time(String[] pack_start_time) {
            this.pack_start_time = pack_start_time;
        }

        public String getUser_confirm() {
            return user_confirm;
        }

        public void setUser_confirm(String user_confirm) {
            this.user_confirm = user_confirm;
        }

        public String getCostStatement() {
            return costStatement;
        }

        public void setCostStatement(String costStatement) {
            this.costStatement = costStatement;
        }

        public String getCostCompensation() {
            return costCompensation;
        }

        public void setCostCompensation(String costCompensation) {
            this.costCompensation = costCompensation;
        }

        public String getCostCompensationLevel() {
            return costCompensationLevel;
        }

        public void setCostCompensationLevel(String costCompensationLevel) {
            this.costCompensationLevel = costCompensationLevel;
        }

        public String getCar_level_name() {
            return car_level_name;
        }

        public void setCar_level_name(String car_level_name) {
            this.car_level_name = car_level_name;
        }
    }
}
