package com.sillykid.app.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.BuildConfig;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.BaiDuInfo;
import com.sillykid.app.retrofit.RequestClient;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.sillykid.app.constant.StringNewConstants.BAIDUTABID;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MallPresenter implements MallContract.Presenter {
    private MallContract.View mView;

    public MallPresenter(MallContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

//    @Override
//    public void getAdvCat() {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("acid", "1");
//        RequestClient.getAdvCat(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.getSuccess(msg, 0);
//            }
//        });
//    }

    @Override
    public void getHomePage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getHomePage(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postBaiDuUpdateInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String baiDuId = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", "");
        if (StringUtils.isEmpty(baiDuId)) {
            return;
        }
        httpParams.put("id", baiDuId);
        String mobile = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "mobile", "");
        httpParams.put("title", mobile);
        String latitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "latitude", "");
        String longitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "longitude", "");
        String locationAddress = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "locationAddress", "");
        httpParams.put("latitude", latitude);
        httpParams.put("longitude", longitude);
        httpParams.put("locationAddress", locationAddress);
        httpParams.put("tags", "0");
        httpParams.put("coord_type", 3);
        httpParams.put("geotable_id", BAIDUTABID);
        httpParams.put("ak", BuildConfig.BAIDU_APPKEY);
        RequestClient.postBaiDuUpdateInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                BaiDuInfo baiDuInfo = (BaiDuInfo) JsonUtil.getInstance().json2Obj(response, BaiDuInfo.class);
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", baiDuInfo.getId());
                //  mView.getSuccess("", 1);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("BaiDuYun", "onFailure");
                //  mView.getSuccess("", 1);
                //  mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
            }
        });

    }

    @Override
    public void initLocation(Activity activity, LocationClient mLocationClient) {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 30000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        // option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位
        mLocationClient.setLocOption(option);
        choiceLocationWrapper(activity, mLocationClient);
    }


    @AfterPermissionGranted(NumericConstants.LOCATION_CODE)
    private void choiceLocationWrapper(Activity activity, LocationClient mLocationClient) {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(activity, perms)) {
            LocationManager locManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            try {
                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
                    ViewInject.toast(activity.getString(R.string.notOpenPositionSwitch));
                    return;
                }
            } catch (Exception e) {
                ViewInject.toast(activity.getString(R.string.notOpenPositionSwitch));
                return;
            }
            mLocationClient.start();
        } else {
            EasyPermissions.requestPermissions(activity, activity.getString(R.string.openPositionSwitch), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }
}
