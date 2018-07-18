package com.sillykid.app.homepage.chartercustom.transfer;

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

public class AirportPickupPresenter implements AirportPickupContract.Presenter {
    private AirportPickupContract.View mView;

    public AirportPickupPresenter(AirportPickupContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postAirportPickup(String type, String flightNumber, String pickUpAtAirport, String deliveredSite, String selectModels, String workNumber, String bags, String bags1, String bags2, String bags3, String name, String contactWay, String fewAdults, String severalChildren, long departureTime, String remark, String con_car_seat_num, int pcpid) {

        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            return;
//        }
        if (StringUtils.isEmpty(flightNumber)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterFlightNumber), 0);
            return;
        }
        if (StringUtils.isEmpty(pickUpAtAirport)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputAirport1), 0);
            return;
        }
        if (StringUtils.isEmpty(deliveredSite)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseDeliveredSite), 0);
            return;
        }
//        if (StringUtils.isEmpty(deliveredSite)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelectModel), 0);
//            return;
//        }
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.yourName1), 0);
            return;
        }
        if (StringUtils.isEmpty(contactWay)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWay1), 0);
            return;
        }

        if (StringUtils.toInt(fewAdults, 0) + StringUtils.toInt(severalChildren, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passengersNumber1), 0);
            return;
        }


        if (departureTime <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelectDepartureTime), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        httpParams.put("city", selectCity);
        httpParams.put("user_name", name);
        httpParams.put("pcpid", pcpid);
     //   httpParams.put("car_type_id", selectModels);
        httpParams.put("car_seat_num", con_car_seat_num);
        httpParams.put("connect", contactWay);
        httpParams.put("drv_code", workNumber);
        int is_have_pack = 0;
        if (StringUtils.toInt(bags, 0) + StringUtils.toInt(bags1, 0) + StringUtils.toInt(bags2, 0) + StringUtils.toInt(bags3, 0) > 0) {
            is_have_pack = 1;
        } else {
            is_have_pack = 0;
        }
        httpParams.put("is_have_pack", is_have_pack);
        //   httpParams.put("total_num", number);
        httpParams.put("adult_num", fewAdults);
        httpParams.put("child_num", severalChildren);
        httpParams.put("remark", remark);
        httpParams.put("flt_no", flightNumber);
        httpParams.put("airport_name", pickUpAtAirport);
        httpParams.put("end_address", deliveredSite);
        httpParams.put("start_time", String.valueOf(departureTime));
        httpParams.put("twenty_four", bags3);
        httpParams.put("twenty_six", bags2);
        httpParams.put("twenty_eight", bags1);
        httpParams.put("thirty", bags);
        RequestClient.postReceiveAirport(httpParams, new ResponseListener<String>() {
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
        RequestClient.getCarBrand(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }
}
