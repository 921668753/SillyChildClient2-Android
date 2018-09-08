package com.sillykid.app.homepage.boutiqueline.fragment;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

public class BoutiqueLinePresenter implements BoutiqueLineContract.Presenter {

    private BoutiqueLineContract.View mView;

    public BoutiqueLinePresenter(BoutiqueLineContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getRouteList(Context context, int region_id, int is_recommand, int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (region_id > 0) {
            httpParams.put("region_id", region_id);
        }
//        if (is_recommand == 1) {
//            httpParams.put("is_recommand", is_recommand);
//        }
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 10);
        RequestClient.getRouteList(context, httpParams, new ResponseListener<String>() {
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
