package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.homepage.chartercustom.routes.CheckstandActivity;
import com.sillykid.app.homepage.chartercustom.routes.RouteDetailsActivity;
import com.sillykid.app.mine.myorder.orderevaluation.PostEvaluationActivity;
import com.sillykid.app.mine.myorder.orderevaluation.SeeEvaluationActivity;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class CharterOrderDetailsPresenter implements CharterOrderDetailsContract.Presenter {
    private CharterOrderDetailsContract.View mView;
    private Intent jumpintent;
    private Uri data;

    public CharterOrderDetailsPresenter(CharterOrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCharterOrderDetails(String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
        RequestClient.getCharterOrderDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void CallPhone(CharterOrderDetailsActivity aty, String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() < 5) {
            mView.errorMsg(aty.getResources().getString(R.string.noPhone), 1);
            return;
        }
        jumpintent = new Intent(Intent.ACTION_DIAL);
        data = Uri.parse("tel:" + phone);
        jumpintent.setData(data);
        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void toPay(CharterOrderDetailsActivity aty, String orderid, String paymoney, String paymoneyfmt) {
        if (TextUtils.isEmpty(orderid) || TextUtils.isEmpty(paymoney)) {
            mView.errorMsg(aty.getResources().getString(R.string.doNotPay), 1);
            return;
        }
        jumpintent = new Intent(aty, CheckstandActivity.class);
        jumpintent.putExtra("orderid", orderid);
        jumpintent.putExtra("paymoney", paymoney);
        jumpintent.putExtra("paymoneyfmt", paymoneyfmt);
        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void toChart(CharterOrderDetailsActivity aty, String hxusername, String nickname, String defaultnickname, String avatar) {
//        if (TextUtils.isEmpty(hxusername)) {
//            mView.errorMsg(aty.getResources().getString(R.string.doNotChat), 1);
//            return;
//        }
//        jumpintent = new Intent(aty, ChatMessageActivity.class);
//        jumpintent.putExtra("userId", hxusername);
//        if (TextUtils.isEmpty(nickname)) {
//            if (TextUtils.isEmpty(defaultnickname)){
//                mView.errorMsg(aty.getResources().getString(R.string.doNotChat), 1);
//                return;
//            }else{
//                jumpintent.putExtra("nickname", defaultnickname);
//            }
//        }else{
//            jumpintent.putExtra("nickname", nickname);
//        }
//        if (!TextUtils.isEmpty(avatar)) {
//            jumpintent.putExtra("avatar", avatar);
//        }
//        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void toEvaluate(CharterOrderDetailsActivity aty, String air_id, int type, String line_id, String seller_id) {
        jumpintent = new Intent(aty, PostEvaluationActivity.class);
        jumpintent.putExtra("air_id", air_id);
        jumpintent.putExtra("type", type);
        jumpintent.putExtra("line_id", line_id);
        jumpintent.putExtra("seller_id", seller_id);
        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void toRouteDetail(CharterOrderDetailsActivity aty, String line_id) {
        if (TextUtils.isEmpty(line_id)) {
            mView.errorMsg(aty.getResources().getString(R.string.noLineId), 0);
            return;
        }
        jumpintent = new Intent(aty, RouteDetailsActivity.class);
        jumpintent.putExtra("line_id", line_id);
        jumpintent.putExtra("orderDetailTag", true);
        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void toSeeEvaluate(CharterOrderDetailsActivity aty, String air_id) {
        jumpintent = new Intent(aty, SeeEvaluationActivity.class);
        jumpintent.putExtra("airid", air_id);
        aty.showActivity(aty, jumpintent);
    }

    @Override
    public void orderConfirmCompleted(CharterOrderDetailsActivity aty, String id, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String location = PreferenceHelper.readString(aty, StringConstants.FILENAME, "location");
        if (!TextUtils.isEmpty(location)) {
            String[] strarry = location.trim().split(",");
            if (strarry != null && strarry.length == 2) {
                httpParams.put("work_pointlng", strarry[0]);
                httpParams.put("work_pointlat", strarry[1]);
            }
        }
        httpParams.put("id", id);
        RequestClient.finishOrder(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void delPackOrder(String air_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("air_id", air_id);
        RequestClient.delPackOrderHttp(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void articleInfo(int type, int flag) {//flag:无特殊原因为：5,6
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        RequestClient.delPackOrderHttp(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

}
