package com.sillykid.app.homepage.chartercustom.singletransport;

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
 * Created by ruitu on 2017/9/24.
 */

public class SingleTransportPresenter implements SingleTransportContract.Presenter {
    private SingleTransportContract.View mView;

    public SingleTransportPresenter(SingleTransportContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postSingleTransport(String type, long vehicleTime, String fewAdults, String severalChildren, int selectModels, String whereDoYouStart, String whereAreGoing, String workNumber, String tv_bags, String tv_bags1, String tv_bags2, String tv_bags3, String name, String contactWay, String remark, String con_car_seat_num, String car_level) {
        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelCity2), 0);
            return;
        }
        String country = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCountry", "");
        if (vehicleTime <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectVehicleTime1), 0);
            return;
        }

        if (StringUtils.toInt(fewAdults, 0) + StringUtils.toInt(severalChildren, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passengersNumber1), 0);
            return;
        }

        if (StringUtils.isEmpty(con_car_seat_num)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelectModel), 0);
            return;
        }
        if (StringUtils.isEmpty(whereDoYouStart)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.whereDoYouStart1), 0);
            return;
        }
        if (StringUtils.isEmpty(whereAreGoing)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.whereAreYouGoing1), 0);
            return;
        }
//行李

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
        httpParams.put("car_type_id", selectModels);
        httpParams.put("user_name", name);
        httpParams.put("car_level", car_level);
        httpParams.put("car_seat_num", con_car_seat_num);
        httpParams.put("connect", contactWay);
        httpParams.put("city", selectCity);
        httpParams.put("country", country);
        httpParams.put("drv_code", workNumber);
        int is_have_pack = 0;
        if (StringUtils.toInt(tv_bags, 0) + StringUtils.toInt(tv_bags1, 0) + StringUtils.toInt(tv_bags2, 0) + StringUtils.toInt(tv_bags3, 0) > 0) {
            is_have_pack = 1;
        } else {
            is_have_pack = 0;
        }
        httpParams.put("is_have_pack", is_have_pack);
        //      httpParams.put("total_num", StringUtils.toInt(fewAdults, 0) + StringUtils.toInt(severalChildren, 0));
        httpParams.put("adult_num", StringUtils.toInt(fewAdults, 0));
        httpParams.put("child_num", StringUtils.toInt(severalChildren, 0));
        httpParams.put("remark", remark);
        httpParams.put("start_address", whereDoYouStart);
        httpParams.put("end_address", whereAreGoing);
        httpParams.put("user_car_time", String.valueOf(vehicleTime));
        httpParams.put("twenty_four", tv_bags3);
        httpParams.put("twenty_six", tv_bags2);
        httpParams.put("twenty_eight", tv_bags1);
        httpParams.put("thirty", tv_bags);
        RequestClient.postOncePickup(httpParams, new ResponseListener<String>() {
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

    @Override
    public void getCarBrand() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getCarList(httpParams, new ResponseListener<String>() {
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
    public void getCarWhere() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarWhere(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void getUnsubscribeCost(int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getUnsubscribeCost(httpParams, type, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
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
