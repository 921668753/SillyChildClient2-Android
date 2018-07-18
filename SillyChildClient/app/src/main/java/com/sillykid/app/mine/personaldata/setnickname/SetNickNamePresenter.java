package com.sillykid.app.mine.personaldata.setnickname;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SetNickNamePresenter implements SetNickNameContract.Presenter {
    private SetNickNameContract.View mView;

    public SetNickNamePresenter(SetNickNameContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void saveInfo(String newnickname) {
        if (TextUtils.isEmpty(newnickname)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.nicknamehint), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", newnickname);
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postSaveInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
