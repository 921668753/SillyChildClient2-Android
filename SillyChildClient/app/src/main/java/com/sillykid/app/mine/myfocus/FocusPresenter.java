package com.sillykid.app.mine.myfocus;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class FocusPresenter implements FocusContract.Presenter {

    private FocusContract.View mView;

    public FocusPresenter(FocusContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyConcernList(int page, int type_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageno", page);
        httpParams.put("pagesize", 10);
        httpParams.put("type_id", 1);
        RequestClient.getMyConcernList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddConcern(int user_id, int type_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("member_id", user_id);
        httpParams.put("type_id", type_id);
        RequestClient.postAddConcern(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
