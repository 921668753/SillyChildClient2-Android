package com.sillykid.app.mall.goodslist.shop;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AllGoodsPresenter implements AllGoodsContract.Presenter {
    private AllGoodsContract.View mView;

    public AllGoodsPresenter(AllGoodsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getStoreGoodsList(int storeid, int key, String order, int page, String keyword) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("storeid", storeid);
        httpParams.put("key", key);
        httpParams.put("order", order);
        httpParams.put("page", page);
        httpParams.put("keyword", keyword);
        RequestClient.getStoreGoodsList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
