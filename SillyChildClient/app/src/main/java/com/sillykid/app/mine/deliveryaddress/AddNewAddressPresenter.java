package com.sillykid.app.mine.deliveryaddress;

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

public class AddNewAddressPresenter implements AddNewAddressContract.Presenter {

    private AddNewAddressContract.View mView;

    public AddNewAddressPresenter(AddNewAddressContract.View view) {
        mView = view;
        mView.setPresenter(this);
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
                mView.errorMsg(msg, 0);
            }
        });
    }


    @Override
    public void postEditAddress(int addr_id, String name, String mobile, int province_id, int city_id, int region_id, int town_id, String addr, int def_addr) {
        if (addr_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.wrongAddress), 0);
            return;
        }
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.consignee1), 0);
            return;
        }
        if (StringUtils.isEmpty(mobile)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWayHint), 0);
            return;
        }
//        if (mobile.length() != 11) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWayHint1), 0);
//            return;
//        }
        if (province_id == 0 || city_id == 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.region1), 0);
            return;
        }
        if (StringUtils.isEmpty(addr)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.regiondetail), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("addr_id", addr_id);
        httpParams.put("name", name);
        httpParams.put("mobile", mobile);
        httpParams.put("province_id", province_id);
        httpParams.put("city_id", city_id);
        httpParams.put("region_id", region_id);
        httpParams.put("town_id", town_id);
        httpParams.put("addr", addr);
        httpParams.put("def", def_addr);
        RequestClient.postEditAddress(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddAddress(String name, String mobile, int province_id, int city_id, int region_id, int town_id, String addr, int def_addr) {
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.consignee1), 0);
            return;
        }
        if (StringUtils.isEmpty(mobile)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWayHint), 0);
            return;
        }
//        if (mobile.length() != 11) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.contactWayHint1), 0);
//            return;
//        }
        if (province_id == 0 || city_id == 0 || region_id == 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.region1), 0);
            return;
        }
        if (StringUtils.isEmpty(addr)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.regiondetail), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("name", name);
        httpParams.put("mobile", mobile);
        httpParams.put("province_id", province_id);
        httpParams.put("city_id", city_id);
        httpParams.put("region_id", region_id);
        httpParams.put("town_id", town_id);
        httpParams.put("addr", addr);
        httpParams.put("def_addr", def_addr);
        RequestClient.postAddAddress(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
