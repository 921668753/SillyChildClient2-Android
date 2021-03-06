package com.sillykid.app.mine.setup.feedback;

import android.text.TextUtils;

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
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/2/11.
 */

public class FeedbackPresenter implements FeedbackContract.Presenter {

    private FeedbackContract.View mView;

    public FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void upPictures(String imgPath) {
        if (StringUtils.isEmpty(imgPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(imgPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
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
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    @Override
    public void postAdvice(String feedType, String content, List<String> imgs) {
        if (TextUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.textDescribe), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", feedType);
        if (imgs.size() > 0) {
            String imgsStr = "";
            for (int i = 0; i < imgs.size(); i++) {
                imgsStr = imgsStr + "," + imgs.get(i);
            }
            map.put("imgUrls", imgsStr.substring(1));
        }
        map.put("text", content);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map));
        RequestClient.postAdvice(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
