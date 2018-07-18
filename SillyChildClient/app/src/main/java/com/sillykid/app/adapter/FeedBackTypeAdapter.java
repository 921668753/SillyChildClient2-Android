package com.sillykid.app.adapter;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.FeedBackTypeBean.ResultBean.ListBean;
import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的  我的钱包 账户明细  适配器
 * Created by Admin on 2017/9/8.
 */

public class FeedBackTypeAdapter extends BGAAdapterViewAdapter<ListBean> {


    public FeedBackTypeAdapter(Context context) {
        super(context, R.layout.item_feedback_type);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {

        helper.setText(R.id.tvbtn_type,model.getName());
        if (model.isSelector()){
            helper.setTextColorRes(R.id.tvbtn_type,R.color.greenColors);
            helper.setBackgroundRes(R.id.tvbtn_type,R.drawable.shape_code);
        }else{
            helper.setTextColorRes(R.id.tvbtn_type,R.color.textColor);
            helper.setBackgroundRes(R.id.tvbtn_type,R.drawable.shape_addshoppingcarte);
        }

    }
}
