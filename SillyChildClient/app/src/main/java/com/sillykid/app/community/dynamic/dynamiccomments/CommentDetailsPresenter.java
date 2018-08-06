package com.sillykid.app.community.dynamic.dynamiccomments;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class CommentDetailsPresenter implements CommentDetailsContract.Presenter {

    private CommentDetailsContract.View mView;

    public CommentDetailsPresenter(CommentDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCommentDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getCommentDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddCommentLike(int id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("comment_id", id);
        httpParams.put("type", type);
        RequestClient.postAddCommentLike(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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

    @Override
    public void postAddComment(String body, int post_id, int reply_comment_id, int reply_member_id, int type) {
        if (StringUtils.toInt(UserUtil.getRcId(KJActivityStack.create().topActivity()).substring(1), 0) == reply_member_id) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.notRespondYourComments), 2);
            return;
        }
        if (StringUtils.isEmpty(body)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.writeComment1), 2);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("body", body);
        httpParams.put("post_id", post_id);
        httpParams.put("reply_comment_id", reply_comment_id);
        httpParams.put("reply_member_id", reply_member_id);
        httpParams.put("type", type);
        RequestClient.postAddComment(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });

    }

    @Override
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }


}
