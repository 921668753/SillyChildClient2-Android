package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

public class LineDetailsPresenter implements LineDetailsContract.Presenter {

    private LineDetailsContract.View mView;

    public LineDetailsPresenter(LineDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getRouteDetail(int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        RequestClient.getRouteDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddCommentLike(int id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("comment_id", id);
        httpParams.put("type", type);
        RequestClient.postAddCommentLike(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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


    @Override
    public void postAddRequirements(int product_id, String flight_arrival_time, String contact, String contact_number,
                                    String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1) {
        if (StringUtils.toLong(flight_arrival_time) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectTravelDate1), 2);
            return;
        }
        if (StringUtils.toLong(flight_arrival_time) < System.currentTimeMillis() / 1000) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectEstimatedArrivalTimeFlight1), 2);
            return;
        }
        if (StringUtils.isEmpty(contact)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.ContactName1), 2);
            return;
        }
        if (StringUtils.isEmpty(contact_number)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.ContactNumber1), 2);
            return;
        }
        if (StringUtils.toInt(audit_number, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.adult3), 2);
            return;
        }
        if (StringUtils.toInt(audit_number, 0) > 0 && StringUtils.toInt(audit_number, 0) + StringUtils.toInt(children_number, 0) > passenger_number1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.adult4), 2);
            return;
        }
        if (StringUtils.toInt(baggage_number, 0) > baggage_number1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.luggage2), 2);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
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
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }
}
