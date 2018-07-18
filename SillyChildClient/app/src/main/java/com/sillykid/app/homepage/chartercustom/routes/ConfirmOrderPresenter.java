package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.utils.DataUtil;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ConfirmOrderPresenter implements ConfirmOrderContract.Presenter {
    private ConfirmOrderContract.View mView;

    public ConfirmOrderPresenter(ConfirmOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postConfirmOrder(String line_id, String title, String customer_name, String customer_phone, String user_passport, String user_user_identity, String twenty_four, String twenty_six, String twenty_eight, String thirty, String use_car_adult, String use_car_children, String work_at, String work_address, String dest_address, int discount_id, String total_price, String remark, String real_price) {
//        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//           mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            return;
//        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("line_id", line_id);
        httpParams.put("title", title);
       // httpParams.put("city", selectCity);
        httpParams.put("customer_name", customer_name);
        httpParams.put("customer_phone", customer_phone);
        httpParams.put("user_passport", user_passport);
        httpParams.put("user_identity", user_user_identity);
        httpParams.put("twenty_four", twenty_four);
        httpParams.put("twenty_six", twenty_six);
        httpParams.put("twenty_eight", twenty_eight);
        httpParams.put("thirty", thirty);
        httpParams.put("use_car_adult", use_car_adult);
        httpParams.put("use_car_children", use_car_children);
        httpParams.put("work_at", work_at);
        httpParams.put("work_address", work_address);
        httpParams.put("twenty_eight", twenty_eight);
        httpParams.put("dest_address", dest_address);
        httpParams.put("discount_id", discount_id);
        httpParams.put("total_price", total_price);
        httpParams.put("real_price", real_price);
        httpParams.put("start_time", String.valueOf(DataUtil.getStringToDate(work_at, "yyyy-MM-dd") / 1000));
        httpParams.put("user_message", remark);
        RequestClient.postConfirmOrder(httpParams, new ResponseListener<String>() {
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
