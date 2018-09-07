package com.sillykid.app.loginregister;

import android.content.Context;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.utils.GetJsonDataUtil;

/**
 * Created by ruitu on 2017/8/24.
 */

public class SelectCountryCodePresenter implements SelectCountryCodeContract.Presenter {

    private SelectCountryCodeContract.View mView;

    public SelectCountryCodePresenter(SelectCountryCodeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCountryNumber(Context context) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getCountryNumber(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
        String JsonData = new GetJsonDataUtil().getJson(context, "countrycode.json");//获取assets目录下的json文件数据
        if (StringUtils.isEmpty(JsonData)) {
            mView.errorMsg(context.getString(R.string.jsonError), 0);
            return;
        }
        mView.getSuccess(JsonData, 0);
    }
}
