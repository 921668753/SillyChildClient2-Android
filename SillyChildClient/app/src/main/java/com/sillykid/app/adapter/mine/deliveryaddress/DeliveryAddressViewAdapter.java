package com.sillykid.app.adapter.mine.deliveryaddress;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mine.deliveryaddress.DeliveryAddressBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 收货地址 适配器
 * Created by Admin on 2017/8/15.
 */

public class DeliveryAddressViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public DeliveryAddressViewAdapter(Context context) {
        super(context, R.layout.item_deliveryaddress);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.ll_setdefault);
        helper.setItemChildClickListener(R.id.ll_deliveryaddressedit);
        helper.setItemChildClickListener(R.id.ll_deliveryaddressdelete);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        viewHolderHelper.setText(R.id.tv_deliveryaddressname, listBean.getName());
        viewHolderHelper.setText(R.id.tv_deliveryaddressphone, listBean.getMobile());
        viewHolderHelper.setText(R.id.tv_deliveryaddress, listBean.getProvince() + listBean.getCity() + listBean.getRegion() + listBean.getTown() + listBean.getAddr());
        if (listBean.getDef_addr() == 1) {
            viewHolderHelper.setImageResource(R.id.iv_deliveryaddresschose, R.mipmap.address_address_select);
            viewHolderHelper.setText(R.id.tv_promptcharacters, mContext.getString(R.string.defaultAddress2));
        } else {
            viewHolderHelper.setImageResource(R.id.iv_deliveryaddresschose, R.mipmap.address_address_unselect);
            viewHolderHelper.setText(R.id.tv_promptcharacters, mContext.getString(R.string.defaultAddress));
        }
    }

}