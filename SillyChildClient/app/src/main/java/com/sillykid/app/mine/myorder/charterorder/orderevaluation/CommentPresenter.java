package com.sillykid.app.mine.myorder.charterorder.orderevaluation;


import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.retrofit.uploadimg.UploadManagerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruitu on 2018/9/24.
 */
public class CommentPresenter implements CommentContract.Presenter {
    private CommentContract.View mView;

    public CommentPresenter(CommentContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getReviewProduct(String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
        RequestClient.getReviewProduct(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddProductReview(String order_number, int time_degree, int play_experience, int service_attitude, String content, List<LocalMedia> selectList) {
        if (time_degree <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.degreeOnTime1), 1);
            return;
        }
        if (play_experience <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.reasonableDistance1), 1);
            return;
        }
        if (service_attitude <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.serviceAttitude1), 1);
            return;
        }
        if (selectList.size() <= 0) {
            List<String> listStr = new ArrayList<String>();
            postAddProductReview1(order_number, time_degree, play_experience, service_attitude, content, listStr);
            return;
        }
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        List<String> listStr = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 1);
                return;
            }
            listStr.add("");
            String token = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuToken");
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
                                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 1);
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
                            postAddProductReview1(order_number, time_degree, play_experience, service_attitude, content, listStr);
                        }
                        return;
                    }
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 1);
                            return;
                        }
                    });
                    return;
                }
            }, null);
        }
    }

    private void postAddProductReview1(String order_number, int time_degree, int play_experience, int service_attitude, String content, List<String> selectList) {
        if (time_degree <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.degreeOnTime1), 1);
            return;
        }
        if (play_experience <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.reasonableDistance1), 1);
            return;
        }
        if (service_attitude <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.serviceAttitude1), 1);
            return;
        }
        String imgsStr = "";
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i);
            }
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("play_experience", play_experience);
        httpParams.put("service_attitude", service_attitude);
        httpParams.put("content", content);
        httpParams.put("order_number", order_number);
        httpParams.put("time_degree", time_degree);
        if (!StringUtils.isEmpty(imgsStr)) {
            httpParams.put("picture", imgsStr.substring(1));
        }
        RequestClient.postAddProductReview(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
