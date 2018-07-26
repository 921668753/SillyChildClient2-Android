package com.sillykid.app.community.search;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SearchPeoplePresenter implements SearchPeopleContract.Presenter {
    private SearchPeopleContract.View mView;

    public SearchPeoplePresenter(SearchPeopleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getMemberList(String name, int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (!StringUtils.isEmpty(name)) {
            httpParams.put("name", name);
        }
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 10);
        RequestClient.getMemberList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
