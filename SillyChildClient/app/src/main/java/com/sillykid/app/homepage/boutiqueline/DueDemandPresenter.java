package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

public class DueDemandPresenter implements DueDemandContract.Presenter {

    private DueDemandContract.View mView;

    public DueDemandPresenter(DueDemandContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProductDetails(int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        RequestClient.getProductLineDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddRequirements(int product_id, String flight_arrival_time, String contact, String contact_number,
                                    String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1) {
        if (StringUtils.toLong(flight_arrival_time) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectTravelDate1), 0);
            return;
        }
        if (StringUtils.toLong(flight_arrival_time) < System.currentTimeMillis() / 1000) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectEstimatedArrivalTimeFlight1), 0);
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
        if (StringUtils.toInt(audit_number, 0) > 0 && StringUtils.toInt(audit_number, 0) + StringUtils.toInt(children_number, 0) > passenger_number1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.adult4), 0);
            return;
        }
        if (StringUtils.toInt(baggage_number, 0) > baggage_number1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.luggage2), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        httpParams.put("flight_number", "");
        httpParams.put("delivery_location", "");
        httpParams.put("flight_arrival_time", flight_arrival_time);
        httpParams.put("travel_start_time", flight_arrival_time);
        httpParams.put("contact", contact);
        httpParams.put("contact_number", contact_number);
        httpParams.put("audit_number", audit_number);
        httpParams.put("children_number", children_number);
        httpParams.put("baggage_number", baggage_number);
        httpParams.put("remarks", remarks);
        RequestClient.postAddRouteRequirements(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
