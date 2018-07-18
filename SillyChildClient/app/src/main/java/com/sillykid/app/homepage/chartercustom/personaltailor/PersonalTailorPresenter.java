package com.sillykid.app.homepage.chartercustom.personaltailor;

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

public class PersonalTailorPresenter implements PersonalTailorContract.Presenter {
    private PersonalTailorContract.View mView;

    public PersonalTailorPresenter(PersonalTailorContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postPersonalTailor(String type, long travelTime, String origin, String destination, String playNumberDays, String fewAdults, String severalChildren, String travelPreferences, String recommendRestaurant, String recommendedAccommodation, String bags, String bags1, String bags2, String bags3, String name, String contactWay, String remark, String workNumber) {
        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelCity2), 0);
            return;
        }
        String country = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCountry", "");

        if (travelTime <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectTravelTime1), 0);
            return;
        }

        if (StringUtils.isEmpty(origin)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.origin1), 0);
            return;
        }

        if (StringUtils.isEmpty(destination)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillDestination), 0);
            return;
        }

        if (StringUtils.isEmpty(playNumberDays)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.playNumberDays1), 0);
            return;
        }


        if (StringUtils.toInt(fewAdults, 0) + StringUtils.toInt(severalChildren, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passengersNumber1), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        httpParams.put("city", selectCity);
        httpParams.put("country", country);
        httpParams.put("user_name", name);
        httpParams.put("start_address", origin);
        httpParams.put("connect", contactWay);
        httpParams.put("is_have_pack", "0");
        //   httpParams.put("total_num", "");
        httpParams.put("adult_num", fewAdults);
        httpParams.put("child_num", severalChildren);

        httpParams.put("drv_code", workNumber);
        httpParams.put("tour_time", String.valueOf(travelTime));
        httpParams.put("end_address", destination);
        httpParams.put("tour_days", playNumberDays);
        //  httpParams.put("tour_person_num", touristNumber);
        httpParams.put("tour_favorite", travelPreferences);
        httpParams.put("recommend_diner", recommendRestaurant);
        httpParams.put("recommend_sleep", recommendedAccommodation);
        httpParams.put("remark", remark);
        httpParams.put("twenty_four", bags3);
        httpParams.put("twenty_six", bags2);
        httpParams.put("twenty_eight", bags1);
        httpParams.put("thirty", bags);
        RequestClient.postPrivateMake(httpParams, new ResponseListener<String>() {
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
    public void getDriverPackConfig() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getDriverPackConfig(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
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
