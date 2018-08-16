package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.common.cklibrary.utils.httputil.ResponseProgressbarListener;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.nanchen.compresshelper.FileUtil;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.retrofit.uploadimg.UploadManagerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
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
        RequestClient.getPostClassificationList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
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
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
        if (StringUtils.isEmpty(videoPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 2);
            return;
        }
        File oldFile = new File(videoPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.videoPathError), 2);
            return;
        }
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 1, new ResponseProgressbarListener<String>() {
            @Override
            public void onProgress(String progress) {
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad) + progress + "%");
            }

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
    public void postAddPost(String post_title, List<LocalMedia> selectList, int mediaType, String content, int classification_id) {
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
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        List<String> listStr = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 3);
                return;
            }
            listStr.add("");
            String token = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuToken");
            if (mediaType != 2) {
                //参数 图片路径,图片名,token,成功的回调
                int finalI = i;
                UploadManagerUtil.getInstance().getUploadManager().put(selectList.get(i).getPath(), null, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + String.valueOf(jsonObject));
                        if (responseInfo.isOK()) {
                            String host = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuImgHost");
                            String headpicPath = null;
                            try {
                                headpicPath = host + jsonObject.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 3);
                                        return;
                                    }
                                });
                                return;
                            }
                            int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                            selectListSize = selectListSize + 1;
                            PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                            Log.i("ReadFragment", "complete: " + headpicPath);
                            listStr.set(finalI, headpicPath);
                            if (selectListSize == selectList.size()) {
                                postAddPost1(post_title, listStr, mediaType, content, classification_id);
                            }
                            return;
                        }
                        KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 3);
                                return;
                            }
                        });
                        return;
                    }
                }, null);
            } else {
                File file = new File(selectList.get(i).getPath());
                int finalI1 = i;
                RequestClient.upLoadImg(KJActivityStack.create().topActivity(), file, 1, new ResponseProgressbarListener<String>() {
                    @Override
                    public void onProgress(String progress) {
                        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad) + progress + "%");
                    }

                    @Override
                    public void onSuccess(String response) {
                        listStr.set(finalI1, response);
                        postAddPost1(post_title, listStr, mediaType, content, classification_id);
                    }

                    @Override
                    public void onFailure(String msg) {
                        KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mView.errorMsg(msg, 3);
                                return;
                            }
                        });
                    }
                });
            }
        }
    }

    private void postAddPost1(String post_title, List<String> selectList, int mediaType, String content, int classification_id) {
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
                imgsStr = imgsStr + "," + selectList.get(i);
            }
        }
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
    public void postEditPost(String post_title, List<LocalMedia> selectList, int mediaType, String content, int classification_id, int post_id) {
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
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        List<String> listStr = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 4);
                return;
            }
            listStr.add("");
            if (mediaType != 2) {
                String imgStr = selectList.get(i).getPath();
                if (!StringUtils.isEmpty(imgStr) && imgStr.startsWith("http")) {
                    int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                    selectListSize = selectListSize + 1;
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                    listStr.set(i, imgStr);
                    if (selectListSize == selectList.size()) {
                        postEditPost1(post_title, listStr, mediaType, content, classification_id, post_id);
                        return;
                    }
                    continue;
                }
                String token = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuToken");
                int finalI = i;
                UploadManagerUtil.getInstance().getUploadManager().put(selectList.get(i).getPath(), null, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + String.valueOf(jsonObject));
                        if (responseInfo.isOK()) {
                            String host = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuImgHost");
                            String headpicPath = null;
                            try {
                                headpicPath = host + jsonObject.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 4);
                                        return;
                                    }
                                });
                                return;
                            }
                            int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                            selectListSize = selectListSize + 1;
                            PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                            Log.i("ReadFragment", "complete: " + headpicPath);
                            listStr.set(finalI, headpicPath);
                            if (selectListSize == selectList.size()) {
                                postEditPost1(post_title, listStr, mediaType, content, classification_id, post_id);
                            }
                            return;
                        }
                        KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 4);
                                return;
                            }
                        });
                        return;
                    }
                }, null);
            } else {
                String viodeStr = selectList.get(i).getPath();
                if (!StringUtils.isEmpty(viodeStr) && viodeStr.startsWith("http")) {
                    listStr.set(i, viodeStr);
                    postEditPost1(post_title, listStr, mediaType, content, classification_id, post_id);
                    return;
                }
                File file = new File(selectList.get(i).getPath());
                int finalI1 = i;
                RequestClient.upLoadImg(KJActivityStack.create().topActivity(), file, 1, new ResponseProgressbarListener<String>() {
                    @Override
                    public void onProgress(String progress) {
                        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad) + progress + "%");
                    }

                    @Override
                    public void onSuccess(String response) {
                        listStr.set(finalI1, response);
                        postEditPost1(post_title, listStr, mediaType, content, classification_id, post_id);
                    }

                    @Override
                    public void onFailure(String msg) {
                        KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mView.errorMsg(msg, 4);
                                return;
                            }
                        });
                    }
                });
            }
        }
    }


    public void postEditPost1(String post_title, List<String> selectList, int mediaType, String content, int classification_id, int post_id) {
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
                imgsStr = imgsStr + "," + selectList.get(i);
            }
        }
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
