package com.sillykid.app.homepage.chartercustom.transfer;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportDropOffContract {
    interface Presenter extends BasePresenter {
        /**
         * 送机
         */
        void postAirportDropOff(String type, String flightNumber, String deliveredAirport, String placeDeparture,
                                String selectModels, String workNumber, String bags, String bags1, String bags2,
                                String bags3, String name, String contactWay, String fewAdults, String severalChildren, long departureTime,
                                String remark, String con_car_seat_num, int pcpid);

        /**
         * 获得车型
         */
        void getCarBrand();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


