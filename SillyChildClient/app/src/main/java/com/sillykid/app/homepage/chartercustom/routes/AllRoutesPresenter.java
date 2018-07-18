package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AllRoutesPresenter implements AllRoutesContract.Presenter {
    private AllRoutesContract.View mView;

    public AllRoutesPresenter(AllRoutesContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAllRoutes(int page, String seat_num, String car_level, String line_buy_num, String city) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (city.equals(KJActivityStack.create().bottomActivity().getString(R.string.allAeservationNumber))) {
            city = "";
        }
//        if (time.equals(KJActivityStack.create().bottomActivity().getString(R.string.time))) {
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//            //   time = df.format(new Date());
//            time = "";
//        }
        RequestClient.getQualityLine(httpParams, page, seat_num, car_level, line_buy_num, city, new ResponseListener<String>() {
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
    public void getCarWhere() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarWhere(httpParams, new ResponseListener<String>() {
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
