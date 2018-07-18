package com.sillykid.app.homepage.dynamics;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class DynamicsCommentariesPresenter implements DynamicsCommentariesContract.Presenter {
    private DynamicsCommentariesContract.View mView;

    public DynamicsCommentariesPresenter(DynamicsCommentariesContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getDynamicsCommentaries(int page, String article_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("p", page);
        httpParams.put("pageSize", 5);
        httpParams.put("article_id", article_id);
        RequestClient.getDynamicsCommentaries(httpParams, new ResponseListener<String>() {
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
    public void newActionComment(String article_id, String publish_id, String content, String parent_id) {
        if (StringUtils.isEmpty(content.trim())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.writeComment1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("article_id", article_id);
        httpParams.put("publish_id", publish_id);
        httpParams.put("content", content);
        httpParams.put("add_time", System.currentTimeMillis() + "");
        httpParams.put("is_anonymous", 0);
        httpParams.put("parent_id", parent_id);
        RequestClient.newActionComment(httpParams, new ResponseListener<String>() {
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

    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    @Override
    public void changeInputView(EditText editText, TextView textView) {
        textView.setText(KJActivityStack.create().topActivity().getString(R.string.cancel));
        textView.setTextColor(KJActivityStack.create().topActivity().getResources().getColor(R.color.tabColors));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 0) {
                    if (textView != null) {
                        textView.setText(KJActivityStack.create().topActivity().getString(R.string.send));
                        textView.setTextColor(KJActivityStack.create().topActivity().getResources().getColor(R.color.greenColors));
                    }
                } else {
                    if (textView != null) {
                        textView.setText(KJActivityStack.create().topActivity().getString(R.string.cancel));
                        textView.setTextColor(KJActivityStack.create().topActivity().getResources().getColor(R.color.tabColors));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void praiseDynamicsDetailsComment(String id, int isPraise) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.praiseDynamicsDetailsComment(httpParams, id, isPraise, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isPraise == 0) {
                    mView.getSuccess(response, 2);
                } else {
                    mView.getSuccess(response, 3);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }
}
