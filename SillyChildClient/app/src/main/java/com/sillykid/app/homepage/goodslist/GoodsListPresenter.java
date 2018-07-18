package com.sillykid.app.homepage.goodslist;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodsListPresenter implements GoodsListContract.Presenter {
    private GoodsListContract.View mView;

    public GoodsListPresenter(GoodsListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodsList(int page, int cat, String sort, String keyword, String tag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        if (!StringUtils.isEmpty(tag)) {
            httpParams.put("mark", tag);
        }
        if (cat > 0) {
            httpParams.put("cat", cat);
        }
        httpParams.put("sort", sort);
        if (!StringUtils.isEmpty(keyword)) {
            httpParams.put("keyword", keyword);
        }
        RequestClient.getGoodsList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
