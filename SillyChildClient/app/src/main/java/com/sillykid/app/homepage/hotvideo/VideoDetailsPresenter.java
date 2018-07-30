package com.sillykid.app.homepage.hotvideo;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class VideoDetailsPresenter implements VideoDetailsContract.Presenter {

    private VideoDetailsContract.View mView;

    public VideoDetailsPresenter(VideoDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getVideoDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("video_id", id);
        RequestClient.getVideoDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
