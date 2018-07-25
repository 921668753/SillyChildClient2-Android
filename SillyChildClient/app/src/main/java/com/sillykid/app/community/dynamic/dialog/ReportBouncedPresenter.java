package com.sillykid.app.community.dynamic.dialog;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.community.dynamic.DynamicDetailsContract;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class ReportBouncedPresenter implements ReportBouncedContract.Presenter {
    private ReportBouncedContract.View mView;

    public ReportBouncedPresenter(ReportBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postAddConcern(Context context, int user_id, int type_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("member_id", user_id);
        httpParams.put("type_id", type_id);
        RequestClient.postAddConcern(context, httpParams, new ResponseListener<String>() {
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
