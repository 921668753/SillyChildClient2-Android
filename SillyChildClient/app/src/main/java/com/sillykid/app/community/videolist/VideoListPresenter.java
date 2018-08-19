package com.sillykid.app.community.videolist;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

public class VideoListPresenter implements VideoListContract.Presenter {

    private VideoListContract.View mView;

    public VideoListPresenter(VideoListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getPostList(Context context, String post_title, String nickname, int classification_id, int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (!StringUtils.isEmpty(post_title)) {
            httpParams.put("post_title", post_title);
        }
        if (!StringUtils.isEmpty(nickname)) {
            httpParams.put("nickname", nickname);
        }
        if (classification_id > 0) {
            httpParams.put("classification_id", classification_id);
        }
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 1);
        RequestClient.getPostList(context, httpParams, new ResponseListener<String>() {
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
