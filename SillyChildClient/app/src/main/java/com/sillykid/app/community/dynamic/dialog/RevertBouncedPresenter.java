package com.sillykid.app.community.dynamic.dialog;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class RevertBouncedPresenter implements RevertBouncedContract.Presenter {

    private RevertBouncedContract.View mView;

    public RevertBouncedPresenter(RevertBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postAddComment(Context context, String body, int post_id, int reply_comment_id, int reply_member_id, int type) {
        if (StringUtils.isEmpty(body)) {
            mView.errorMsg(context.getString(R.string.writeComment1), 5);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("body", body);
        httpParams.put("post_id", post_id);
        httpParams.put("reply_comment_id", reply_comment_id);
        httpParams.put("reply_member_id", reply_member_id);
        httpParams.put("type", type);
        RequestClient.postAddComment(context, httpParams, new ResponseListener<String>() {
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
