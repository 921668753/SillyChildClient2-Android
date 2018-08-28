package com.sillykid.app.adapter.homepage.boutiqueline;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.boutiqueline.DueDemandBean.DataBean.ServiceBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class DueDemandViewAdapter extends BGAAdapterViewAdapter<ServiceBean> {

    public DueDemandViewAdapter(Context context) {
        super(context, R.layout.item_containsservice);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ServiceBean model) {
        if (model.getIs_show() == 0) {
            helper.setBackgroundRes(R.id.ll_serviceName, R.drawable.shape_containsservice1);
            helper.setTextColorRes(R.id.tv_serviceName, R.color.hintColors);
        } else {
            helper.setBackgroundRes(R.id.ll_serviceName, R.drawable.shape_containsservice);
            helper.setTextColorRes(R.id.tv_serviceName, R.color.greenColors);
        }
        helper.setText(R.id.tv_serviceName, model.getName());
    }
}
