package com.sillykid.app.homepage.privatecustom;

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

public class PrivateCustomPresenter implements PrivateCustomContract.Presenter {

    private PrivateCustomContract.View mView;

    public PrivateCustomPresenter(PrivateCustomContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getVideoList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pagesize", 10);
        RequestClient.getVideoList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddCustomized(long travel_time, String destination, String play_days, int travel_preference, int repast_preference, int stay_preference, String remark) {

        if (travel_time <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectTravelTime1), 1);
            return;
        }

        if (StringUtils.isEmpty(destination)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillDestination), 1);
            return;
        }

        if (StringUtils.toInt(play_days, 0) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.playNumberDays1), 1);
            return;
        }
        if (travel_preference <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + KJActivityStack.create().topActivity().getString(R.string.travelPreferences), 1);
            return;
        }
        if (repast_preference <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + KJActivityStack.create().topActivity().getString(R.string.recommendRestaurant), 1);
            return;
        }
        if (stay_preference <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + KJActivityStack.create().topActivity().getString(R.string.recommendedAccommodation), 1);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("travel_time", String.valueOf(travel_time));
        httpParams.put("destination", destination);
        httpParams.put("play_days", play_days);
        httpParams.put("travel_preference", travel_preference);
        httpParams.put("repast_preference", repast_preference);
        httpParams.put("stay_preference", stay_preference);
        httpParams.put("remark", remark);
        RequestClient.postAddCustomized(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
