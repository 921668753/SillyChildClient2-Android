package com.sillykid.app.adapter;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.BagListBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 行李添加选择
 * Created by Admin on 2017/9/14.
 */

public class BythedagBagsViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public BythedagBagsViewAdapter(Context context) {
        super(context, R.layout.item_bythedag_bags1);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_subtractionBags);
        helper.setItemChildClickListener(R.id.tv_addBags);
        helper.setItemChildClickListener(R.id.img_dimension30);
        helper.setItemChildClickListener(R.id.img_dimension28);
        helper.setItemChildClickListener(R.id.img_dimension26);
        helper.setItemChildClickListener(R.id.img_dimension24);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
        viewHolderHelper.setText(R.id.tv_bags, listBean.getLuggage_num());
        if (listBean.getLuggage_num().equals("0")) {
            viewHolderHelper.setBackgroundRes(R.id.tv_subtractionBags, R.drawable.shape_code1);
            viewHolderHelper.setTextColorRes(R.id.tv_subtractionBags, R.color.hintColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_addBags, R.drawable.shape_code);
            viewHolderHelper.setImageResource(R.id.img_dimension30, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension28, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension26, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension24, R.mipmap.icon_checkbox_unselect);
        } else if (listBean.getDimension().equals(mContext.getString(R.string.inch30))) {
            viewHolderHelper.setBackgroundRes(R.id.tv_subtractionBags, R.drawable.shape_code);
            viewHolderHelper.setTextColorRes(R.id.tv_subtractionBags, R.color.greenColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_addBags, R.drawable.shape_code);
            viewHolderHelper.setImageResource(R.id.img_dimension30, R.mipmap.icon_checkbox_selected);
            viewHolderHelper.setImageResource(R.id.img_dimension28, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension26, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension24, R.mipmap.icon_checkbox_unselect);
        } else if (listBean.getDimension().equals(mContext.getString(R.string.inch28))) {
            viewHolderHelper.setBackgroundRes(R.id.tv_subtractionBags, R.drawable.shape_code);
            viewHolderHelper.setTextColorRes(R.id.tv_subtractionBags, R.color.greenColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_addBags, R.drawable.shape_code);
            viewHolderHelper.setImageResource(R.id.img_dimension30, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension28, R.mipmap.icon_checkbox_selected);
            viewHolderHelper.setImageResource(R.id.img_dimension26, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension24, R.mipmap.icon_checkbox_unselect);
        } else if (listBean.getDimension().equals(mContext.getString(R.string.inch26))) {
            viewHolderHelper.setBackgroundRes(R.id.tv_subtractionBags, R.drawable.shape_code);
            viewHolderHelper.setTextColorRes(R.id.tv_subtractionBags, R.color.greenColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_addBags, R.drawable.shape_code);
            viewHolderHelper.setImageResource(R.id.img_dimension30, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension28, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension26, R.mipmap.icon_checkbox_selected);
            viewHolderHelper.setImageResource(R.id.img_dimension24, R.mipmap.icon_checkbox_unselect);
        } else if (listBean.getDimension().equals(mContext.getString(R.string.inch24))) {
            viewHolderHelper.setBackgroundRes(R.id.tv_subtractionBags, R.drawable.shape_code);
            viewHolderHelper.setTextColorRes(R.id.tv_subtractionBags, R.color.greenColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_addBags, R.drawable.shape_code);
            viewHolderHelper.setImageResource(R.id.img_dimension30, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension28, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension26, R.mipmap.icon_checkbox_unselect);
            viewHolderHelper.setImageResource(R.id.img_dimension24, R.mipmap.icon_checkbox_selected);
        }

    }

}