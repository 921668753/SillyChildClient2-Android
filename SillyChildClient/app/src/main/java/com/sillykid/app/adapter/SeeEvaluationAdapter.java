package com.sillykid.app.adapter;

import android.content.Context;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.sillykid.app.R;
import com.sillykid.app.entity.SeeEvaluationBean.ResultBean;

import java.text.SimpleDateFormat;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的订单---查看评价   适配器
 * Created by Admin on 2017/8/15.
 */

public class SeeEvaluationAdapter extends BGAAdapterViewAdapter<ResultBean>{
    private Context context;
    private SimpleDateFormat dateformat;
    private NoScrollGridView noscrollgridview;

    public SeeEvaluationAdapter(Context context) {
        super(context, R.layout.item_seeevaluation);
        this.context=context;
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);

    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {

        noscrollgridview=viewHolderHelper.getView(R.id.noscrollgridview);
        CharterListGridViewAdapter charterListGridViewAdapter=new CharterListGridViewAdapter(context);
//        charterListGridViewAdapter.addNewData();
        noscrollgridview.setAdapter(charterListGridViewAdapter);

    }
}