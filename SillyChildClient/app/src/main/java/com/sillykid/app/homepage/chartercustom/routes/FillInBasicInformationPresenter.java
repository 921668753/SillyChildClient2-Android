package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class FillInBasicInformationPresenter implements FillInBasicInformationContract.Presenter {
    private FillInBasicInformationContract.View mView;

    public FillInBasicInformationPresenter(FillInBasicInformationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postQuickReservation(String name, String contactWay, String passportNum, String idNumber, String adult_num, String child_num, String origin, String whereToPlay, String destination, String serviceDate, String bags, String bags1, String bags2, String bags3, String remark) {
//        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            return;
//        }
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillYourName), 0);
            return;
        }
        if (StringUtils.isEmpty(contactWay)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillContactWay), 0);
            return;
        }
//        if (StringUtils.isEmpty(passportNum)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillPassportNum), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(idNumber)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillIdNumber), 0);
//            return;
//        }
//        if (!(idNumber.length() != 15 || idNumber.length() != 18)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillIdNumber1), 0);
//            return;
//        }
        if (StringUtils.toInt(adult_num, 0) <= 0 && StringUtils.toInt(child_num, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passengersNumber1), 0);
            return;
        }

//        if (StringUtils.isEmpty(origin)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectOrigin), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(whereToPlay)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectAdress), 0);
//            return;
//        }
//
//        if (StringUtils.isEmpty(destination)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.chooseReachCity), 0);
//            return;
//        }

        if (StringUtils.isEmpty(serviceDate)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.optionDate1), 0);
            return;
        }
        mView.getSuccess("", 0);
//
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("type", type);
////        httpParams.put("connect", phone);
////        httpParams.put("total_num", "");
////        httpParams.put("adult_num", adult_num);
////        httpParams.put("child_num", child_num);
////        httpParams.put("remark", remark);
////        httpParams.put("start_address", start_city + start_address);
////        httpParams.put("user_car_time", user_car_time + "");
//        RequestClient.postLogin(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }


    @Override
    public void isLogin() {
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
    }
}
