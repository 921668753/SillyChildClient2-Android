package com.sillykid.app.homepage.chartercustom.addresspoisearch;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AddressPOISearchPresenter implements AddressPOISearchContract.Presenter {
    private AddressPOISearchContract.View mView;

    public AddressPOISearchPresenter(AddressPOISearchContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSearchCity(String name) {






    }

}
