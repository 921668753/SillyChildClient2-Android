package com.sillykid.app.mine.myorder.goodorder.orderevaluation;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.MemberCommentExtsBean;
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruitu on 2016/9/24.
 */

public class PublishedeEvaluationPresenter implements PublishedeEvaluationContract.Presenter {
    private PublishedeEvaluationContract.View mView;

    public PublishedeEvaluationPresenter(PublishedeEvaluationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(int orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderId);
        RequestClient.getOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void upPictures(String imgPath) {
        if (StringUtils.isEmpty(imgPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 1);
            return;
        }
        File oldFile = new File(imgPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 1);
            return;
        }
        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 0, new ResponseListener<String>() {
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
    public void postCommentCreate(List<MemberCommentExtsBean> listBean, int store_desccredit, int store_servicecredit, int store_deliverycredit) {
        if (store_desccredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectDescriptionMatchingScore), 0);
            return;
        }
        if (store_servicecredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectLogisticsServiceRating), 0);
            return;
        }
        if (store_deliverycredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectServiceAttitudeScore), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberCommentExts", listBean);
        map.put("store_desccredit", String.valueOf(store_desccredit));
        map.put("store_servicecredit", String.valueOf(store_servicecredit));
        map.put("store_deliverycredit", String.valueOf(store_deliverycredit));
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postCommentCreate(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
