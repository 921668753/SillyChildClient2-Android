package com.sillykid.app.homepage.chartercustom.bytheday;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ByTheDayPresenter implements ByTheDayContract.Presenter {
    private ByTheDayContract.View mView;

    public ByTheDayPresenter(ByTheDayContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postByTheDay(String type, String origin, String destination, String selectStartEndDateCar, String selectModels, String workNumber, String adultNum, String childrenNum, String tv_bags, String tv_bags1, String tv_bags2, String tv_bags3, String name, String contactWay, String remark, String con_car_seat_num, int pcpid) {
        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            return;
//        }
        if (StringUtils.isEmpty(origin)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.whereDoYouStart1), 0);
            return;
        }

        if (StringUtils.isEmpty(destination)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.whereToGo1), 0);
            return;
        }

        if (StringUtils.isEmpty(selectStartEndDateCar)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectStartEndDateCar1), 0);
            return;
        }

//
//        if (selectModels <= 0) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelectModel), 0);
//            return;
//        }

        if (StringUtils.toInt(adultNum, 0) <= 0 && StringUtils.toInt(childrenNum, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.choiceTravel1), 0);
            return;
        }

        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.yourName1), 0);
            return;
        }

        if (StringUtils.isEmpty(contactWay)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWay1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        httpParams.put("city", selectCity);
        httpParams.put("start_address", origin);
        httpParams.put("user_name", name);
        httpParams.put("pcpid", pcpid);
        httpParams.put("car_seat_num", con_car_seat_num);
//        httpParams.put("car_type_id", selectModels);
        httpParams.put("connect", contactWay);
        httpParams.put("drv_code", workNumber);
        int is_have_pack = 0;
        if (StringUtils.toInt(tv_bags, 0) + StringUtils.toInt(tv_bags1, 0) + StringUtils.toInt(tv_bags2, 0) + StringUtils.toInt(tv_bags3, 0) > 0) {
            is_have_pack = 1;
        } else {
            is_have_pack = 0;
        }
        httpParams.put("is_have_pack", is_have_pack);
        //   httpParams.put("total_num", StringUtils.toInt(adultNum, 0) + StringUtils.toInt(childrenNum, 0));
        httpParams.put("adult_num", StringUtils.toInt(adultNum, 0));
        httpParams.put("child_num", StringUtils.toInt(childrenNum, 0));
        httpParams.put("remark", remark);
        httpParams.put("end_address", destination);
        httpParams.put("pack_time", selectStartEndDateCar);
        httpParams.put("twenty_four", tv_bags3);
        httpParams.put("twenty_six", tv_bags2);
        httpParams.put("twenty_eight", tv_bags1);
        httpParams.put("thirty", tv_bags);
        RequestClient.postRentCarByDay(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    /**
     * 得到车辆品牌列表
     */
    @Override
    public void getCarBrand() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getCarBrand(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }


    @Override
    public void isLogin() {
//        RequestClient.isLogin(new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }


}
