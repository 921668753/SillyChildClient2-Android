package com.sillykid.app.adapter.trip;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.VisaBean.ResultBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.homepage.popularstrategy.HotStrategyActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 签证/选择地区 适配器
 * Created by Admin on 2017/8/15.
 */

public class VisaViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    private int type;

    public VisaViewAdapter(Context context, int type) {
        super(context, R.layout.item_visa);
        this.type = type;
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, ResultBean listBean) {

        /**
         * 姓名
         */
        helper.setText(R.id.tv_name, listBean.getName());

        /**
         * 国家名字和图片
         */
        NoScrollGridView gv_itemVisa = (NoScrollGridView) helper.getView(R.id.gv_itemVisa);
        ItemVisaViewAdapter itemVisaViewAdapter = new ItemVisaViewAdapter(mContext);
        gv_itemVisa.setAdapter(itemVisaViewAdapter);
        itemVisaViewAdapter.addNewData(listBean.getList());
        gv_itemVisa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                if (type == 0) {
                    intent.setClass(mContext, BannerDetailsActivity.class);
                    intent.putExtra("title", mContext.getString(R.string.visaDetails));
                    intent.putExtra("url", "");
                } else {
                    intent.setClass(mContext, HotStrategyActivity.class);
                    intent.putExtra("title", mContext.getString(R.string.regionalStrategy));
                    intent.putExtra("country_name", String.valueOf(itemVisaViewAdapter.getItem(i).getName()));
                }
                mContext.startActivity(intent);
            }
        });
    }

}