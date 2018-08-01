package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
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
    public void getPostDetail(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getDynamicDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 5);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 5);
            }
        });

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
                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.errorMsg(msg, 1);
                    }
                });
            }
        });
    }

    @Override
    public void uploadVideo(String videoPath) {
        if (StringUtils.isEmpty(videoPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 2);
            return;
        }
        File oldFile = new File(videoPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.videoPathError), 2);
            return;
        }
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 1, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.errorMsg(msg, 2);
                    }
                });
            }
        });
    }


    @Override
    public void postAddPost(String post_title, List<LocalMedia> selectList, String content, int classification_id) {
        if (classification_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + KJActivityStack.create().topActivity().getString(R.string.category), 3);
            return;
        }
        if (StringUtils.isEmpty(post_title)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addTitle1), 3);
            return;
        }
        if (StringUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addDescription1), 3);
            return;
        }
        if (selectList.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addPicturesOrVideo), 3);
            return;
        }
        String imgsStr = "";
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i).getPath();
            }
        }
        LocalMedia media = selectList.get(selectList.size() - 1);
        String pictureType = media.getPictureType();
        int mediaType = PictureMimeType.pictureToVideo(pictureType);
        if (StringUtils.isEmpty(imgsStr) && mediaType != 2 || StringUtils.isEmpty(imgsStr.substring(1)) && mediaType != 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addImages1), 3);
            return;
        }
        if (StringUtils.isEmpty(imgsStr) && mediaType == 2 || StringUtils.isEmpty(imgsStr.substring(1)) && mediaType == 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addVideo), 3);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("post_title", post_title);
        httpParams.put("content", content);
        httpParams.put("classification_id", classification_id);
        httpParams.put("type", mediaType);
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

    @Override
    public void postEditPost(String post_title, List<LocalMedia> selectList, String content, int classification_id, int post_id) {
        if (classification_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + KJActivityStack.create().topActivity().getString(R.string.category), 4);
            return;
        }
        if (StringUtils.isEmpty(post_title)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addTitle1), 4);
            return;
        }
        if (StringUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addDescription1), 4);
            return;
        }
        if (selectList.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addPicturesOrVideo), 4);
            return;
        }
        String imgsStr = "";
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i).getPath();
            }
        }
        LocalMedia media = selectList.get(selectList.size() - 1);
        String pictureType = media.getPictureType();
        int mediaType = PictureMimeType.pictureToVideo(pictureType);
        if (StringUtils.isEmpty(imgsStr) && mediaType != 2 || StringUtils.isEmpty(imgsStr.substring(1)) && mediaType != 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addImages1), 4);
            return;
        }
        if (StringUtils.isEmpty(imgsStr) && mediaType == 2 || StringUtils.isEmpty(imgsStr.substring(1)) && mediaType == 2) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addVideo), 4);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("post_id", post_id);
        httpParams.put("post_title", post_title);
        httpParams.put("content", content);
        httpParams.put("classification_id", classification_id);
        httpParams.put("type", mediaType);
        httpParams.put("file_url", imgsStr.substring(1));
        RequestClient.postEditPost(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 4);
            }
        });
    }

    @Override
    public void postDeletePost(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.postDeletePost(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 6);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 6);
            }
        });
    }


}
