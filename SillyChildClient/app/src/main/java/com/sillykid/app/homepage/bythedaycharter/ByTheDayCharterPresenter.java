package com.sillykid.app.homepage.bythedaycharter;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class ByTheDayCharterPresenter implements ByTheDayCharterContract.Presenter {
    private ByTheDayCharterContract.View mView;

    public ByTheDayCharterPresenter(ByTheDayCharterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postAddCarRequirements(int product_id, long travel_start_time, long travel_end_time, long day, String place_of_departure, String delivery_location, String contact, String contact_number,
                                       String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1) {
        if (travel_start_time <= 0 || travel_end_time <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelTime4), 0);
            return;
        }
        if (travel_start_time < System.currentTimeMillis() / 1000) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelTime5), 0);
            return;
        }

        if (StringUtils.isEmpty(place_of_departure)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.journeyBegins1), 0);
            return;
        }
        if (StringUtils.isEmpty(delivery_location)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.journeyEnds1), 0);
            return;
        }
        if (StringUtils.isEmpty(contact)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.ContactName1), 0);
            return;
        }
        if (StringUtils.isEmpty(contact_number)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.ContactNumber1), 0);
            return;
        }
        if (StringUtils.toInt(audit_number, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.adult3), 0);
            return;
        }
        if (StringUtils.toInt(audit_number, 0) > 0 && StringUtils.toInt(audit_number, 0) + StringUtils.toInt(children_number, 0) > passenger_number1 - 1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.adult4), 0);
            return;
        }
        if (StringUtils.toInt(baggage_number, 0) > baggage_number1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.luggage2), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        httpParams.put("travel_start_time", String.valueOf(travel_start_time));
        httpParams.put("travel_end_time", String.valueOf(travel_end_time));
        httpParams.put("place_of_departure", place_of_departure);
        httpParams.put("delivery_location", delivery_location);
        httpParams.put("contact", contact);
        httpParams.put("days", String.valueOf(day));
        httpParams.put("contact_number", contact_number);
        httpParams.put("audit_number", audit_number);
        httpParams.put("children_number", children_number);
        httpParams.put("baggage_number", baggage_number);
        httpParams.put("remarks", remarks);
        RequestClient.postAddCarRequirements(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}