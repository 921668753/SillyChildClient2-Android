package com.sillykid.app.homepage.hotvideo;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class HotVideoPresenter implements HotVideoContract.Presenter {

    private HotVideoContract.View mView;

    public HotVideoPresenter(HotVideoContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getVideoList(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageno", page);
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
}
