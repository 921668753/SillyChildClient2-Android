package com.sillykid.app.mine.personaldata;

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
import java.util.Map;

/**
 * Created by ruitu on 2016/9/24.
 */

public class PersonalDataPresenter implements PersonalDataContract.Presenter {
    private PersonalDataContract.View mView;

    public PersonalDataPresenter(PersonalDataContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void changeShzCode(String shz_code) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("shz_code", shz_code);
        RequestClient.changeShzCode(httpParams, new ResponseListener<String>() {
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
    public void upPictures(String path) {
        if (StringUtils.isEmpty(path)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(path);
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
                setFace(response);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    /**
     * 保存头像
     */
    private void setFace(String imgUrl) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("face", imgUrl);
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postSaveInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(imgUrl, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void setBirthday(long birthday) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("birthday", String.valueOf(birthday));
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postSaveInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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


    @Override
    public void setRegion(String province, int province_id, String city, int city_id, String region, int region_id) {
        if (province_id == 0 || city_id == 0 || region_id == 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.atRegion1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("province", province);
        map.put("province_id", province_id);
        map.put("city", city);
        map.put("city_id", city_id);
        map.put("region", region);
        map.put("region_id", region_id);
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postSaveInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getAddress(int parentid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("parentid", parentid);
        RequestClient.getAddressRegionList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, -1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, -1);
            }
        });
    }


    @Override
    public void getRegionList(int parentid, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("parentid", parentid);
        RequestClient.getRegionList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

}
