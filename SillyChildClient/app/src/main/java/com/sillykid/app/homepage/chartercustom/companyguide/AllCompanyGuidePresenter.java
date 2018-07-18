package com.sillykid.app.homepage.chartercustom.companyguide;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

import java.text.SimpleDateFormat;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AllCompanyGuidePresenter implements AllCompanyGuideContract.Presenter {
    private AllCompanyGuideContract.View mView;

    public AllCompanyGuidePresenter(AllCompanyGuideContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAllCompanyGuide(int page, String time, String city, String travelNumber) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (city.equals(KJActivityStack.create().bottomActivity().getString(R.string.allAeservationNumber)) || city.equals(KJActivityStack.create().bottomActivity().getString(R.string.destination))) {
            city = "";
        }
        if (time.equals(KJActivityStack.create().bottomActivity().getString(R.string.date))) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            // time = df.format(new Date());
            time = "";
        }
        if (travelNumber.equals(KJActivityStack.create().bottomActivity().getString(R.string.travelNumber))) {
            travelNumber = "";
        }
        RequestClient.getAllCompanyGuide(httpParams, page, time, city, travelNumber, new ResponseListener<String>() {
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
    public void getCarInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarInfo(httpParams, new ResponseListener<String>() {
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
