package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;
import java.util.List;

/**
 * Created by ruitu on 2018/9/24.
 */

public class ReleaseDynamicPresenter implements ReleaseDynamicContract.Presenter {

    private ReleaseDynamicContract.View mView;

    public ReleaseDynamicPresenter(ReleaseDynamicContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getClassificationList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getClassificationList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void uploadVideo(String videoPath) {


    }

    @Override
    public void postAddPost(String post_title, List<String> imgs, String content, String classification_id, int type) {

        if (StringUtils.isEmpty(post_title)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addTitle1), 2);
            return;
        }
        if (StringUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addDescription1), 3);
            return;
        }
        String imgsStr = "";
        if (imgs.size() > 0) {
            for (int i = 0; i < imgs.size(); i++) {
                imgsStr = imgsStr + "," + imgs.get(i);
            }
        }
        if (StringUtils.isEmpty(imgsStr) && type != 2 || StringUtils.isEmpty(imgsStr.substring(1)) && type != 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addImages1), 3);
            return;
        }
        if (StringUtils.isEmpty(imgsStr) && type == 2 || StringUtils.isEmpty(imgsStr.substring(1)) && type == 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addVideo), 3);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("post_title", post_title);
        httpParams.put("content", content);
        httpParams.put("classification_id", classification_id);
        httpParams.put("type", type);
        httpParams.put("file_url", imgsStr.substring(1));
        RequestClient.postAddPost(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 3);
            }
        });
    }

}
