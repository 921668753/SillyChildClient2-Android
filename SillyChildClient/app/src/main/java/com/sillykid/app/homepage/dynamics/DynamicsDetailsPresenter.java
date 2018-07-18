package com.sillykid.app.homepage.dynamics;

import android.content.Intent;
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

public class DynamicsDetailsPresenter implements DynamicsDetailsContract.Presenter {
    private DynamicsDetailsContract.View mView;

    public DynamicsDetailsPresenter(DynamicsDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getReadMessage(String id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getReadMessage(httpParams, id, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 10);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getDynamicsDetails(String id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getDynamicsDetails(httpParams, id, new ResponseListener<String>() {
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
    public void getAttention(String id, int isAttention) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAttention(httpParams, id, isAttention, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isAttention == 0) {
                    mView.getSuccess(response, 1);
                } else {
                    mView.getSuccess(response, 2);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void praiseDynamicsDetails(String id, int isPraise) {

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.praiseDynamicsDetails(httpParams, id, isPraise, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isPraise == 0) {
                    mView.getSuccess(response, 3);
                } else {
                    mView.getSuccess(response, 4);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });

    }

    @Override
    public void collectDynamic(String id, int isCollectDynamic) {

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.collectDynamic(httpParams, id, isCollectDynamic, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isCollectDynamic == 0) {
                    mView.getSuccess(response, 5);
                } else {
                    mView.getSuccess(response, 6);
                }
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
                mView.getSuccess(response, 7);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });

    }

    @Override
    public void praiseDynamicsDetailsComment(String id, int isPraiseComment) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.praiseDynamicsDetailsComment(httpParams, id, isPraiseComment, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isPraiseComment == 0) {
                    mView.getSuccess(response, 8);
                } else {
                    mView.getSuccess(response, 9);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    @Override
    public void changeInputView(EditText editText, TextView textView, int isComment) {
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
                        if (isComment == 0) {
                            textView.setText(KJActivityStack.create().topActivity().getString(R.string.send));
                        } else {
                            textView.setText(KJActivityStack.create().topActivity().getString(R.string.published));
                        }
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
    public void toDynamicComments(TextView textView, String article_id, String publish_id, DynamicsDetailsActivity activity) {
        if (StringUtils.toInt(textView.getText().toString(), 0) > 0) {
            Intent intent = new Intent(activity, DynamicsCommentariesActivity.class);
            intent.putExtra("article_id", article_id);
            intent.putExtra("publish_id", publish_id);
            activity.showActivity(activity, intent);
        }
    }
}
