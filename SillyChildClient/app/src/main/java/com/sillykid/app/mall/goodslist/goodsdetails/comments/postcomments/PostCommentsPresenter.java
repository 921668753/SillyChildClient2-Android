package com.sillykid.app.mall.goodslist.goodsdetails.comments.postcomments;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.mall.goodslist.goodsdetails.comments.postcomments.PostCommentsContract;
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class PostCommentsPresenter implements PostCommentsContract.Presenter {

    private PostCommentsContract.View mView;

    public PostCommentsPresenter(PostCommentsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void upPictures(String paramname, File voule, int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put(paramname, voule);
//        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), httpParams, 0, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, resultsource);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, resultsource);
//            }
//        });
    }

    @Override
    public void postEvaluation(String orderid, int ordertype, int guidestarnum, int starnum, String urls, int anonymity, String textcontent, int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (TextUtils.isEmpty(textcontent)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.fillWords), resultsource);
            return;
        } else {
            httpParams.put("content", textcontent);
        }
        httpParams.put("orderId", orderid);
        if (ordertype != 4 && ordertype != 5) {
            httpParams.put("score", starnum);
            httpParams.put("drv_rank", guidestarnum);
        } else {
            httpParams.put("score", guidestarnum);
        }
        if (!TextUtils.isEmpty(urls)) {
            httpParams.put("img", urls.substring(1));
        }
        httpParams.put("isAnonymous", anonymity);
        RequestClient.upEvaluation(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, resultsource);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, resultsource);
            }
        });
    }

    @Override
    public void postEvaluationRoute(String air_id, String seller_id, String drv_rank, String drv_content, String drv_img, String drv_is_anonymous, String line_id, String line_rank, String line_content, String line_img, String line_is_anonymous, int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (TextUtils.isEmpty(drv_content) || TextUtils.isEmpty(line_content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.fillWords), resultsource);
            return;
        } else {
            httpParams.put("drv_content", drv_content);
            httpParams.put("line_content", line_content);
        }
        httpParams.put("air_id", air_id);
        httpParams.put("seller_id", seller_id);
        httpParams.put("drv_rank", drv_rank);
        if (!TextUtils.isEmpty(drv_img)) {
            httpParams.put("drv_img", drv_img.substring(1));
        }
        if (!TextUtils.isEmpty(line_img)) {
            httpParams.put("line_img", line_img.substring(1));
        }
        httpParams.put("drv_is_anonymous", drv_is_anonymous);
        httpParams.put("line_id", line_id);
        httpParams.put("line_rank", line_rank);
        httpParams.put("line_is_anonymous", line_is_anonymous);
        RequestClient.upEvaluationLine(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, resultsource);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, resultsource);
            }
        });
    }

    @Override
    public void seeEvaluation(String orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderid);
        RequestClient.seeEvaluationDetail(httpParams, new ResponseListener<String>() {
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
