package com.sillykid.app.homepage.chartercustom.chartercommon;

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

public class CharterListPresenter implements CharterListContract.Presenter {
    private CharterListContract.View mView;

    public CharterListPresenter(CharterListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    /**
     * 获得包车列表
     */
    @Override
    public void getPackCarProduct(String city, int page, int type, String car_level, String car_seat_num, String order_times) {
        String country = "";
        if (StringUtils.isEmpty(city) || city.equals(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            city = "";
            country = "";
        } else {
            country = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCountry", "");
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("p", page);
        httpParams.put("city", city);
        httpParams.put("country", country);
        httpParams.put("car_level", car_level);
        httpParams.put("car_seat_num", car_seat_num);
        httpParams.put("order_times", order_times);
        httpParams.put("pageSize", 10);
        httpParams.put("type", type);
        RequestClient.getPackCarProduct(httpParams, new ResponseListener<String>() {
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
    public void getCarWhere() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarWhere(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }


//    @Override
//    public void isLogin() {
//        RequestClient.isLogin(new ResponseListener<String>() {
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
//    }


}
