package com.sillykid.app.adapter.mine.myorder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean.OrderItemsBean;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.AfterSalesDetailsActivity;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单列表 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderViewAdapter extends BGAAdapterViewAdapter<OrderItemsBean> {

    private ResultBean model;

    public GoodOrderViewAdapter(Context context, ResultBean model) {
        super(context, R.layout.item_shopgoodsup);
        this.model = model;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, OrderItemsBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, listBean.getSpecs());
        viewHolderHelper.setText(R.id.tv_number, String.valueOf(listBean.getNum()));
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));
        if (model.getStatus() == 4 || model.getStatus() == 5) {
            if (listBean.getSellback_state() == 0) {
                viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
            } else if (listBean.getSellback_state() == 1) {
                viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.applyAfterSales1));
            } else if (listBean.getSellback_state() == 2) {
                viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterComplete));
            } else if (listBean.getSellback_state() == 3) {
                viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterRefusing));
            } else if (listBean.getSellback_state() == 4) {
                viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.merchantPassed));
            }
        } else if (model.getStatus() == 7 || model.getStatus() == 8) {
            if (listBean.getSellback_state() == 0) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.applyAfterSales));
            } else if (listBean.getSellback_state() == 1) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.applyAfterSales1));
            } else if (listBean.getSellback_state() == 2) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterComplete));
            } else if (listBean.getSellback_state() == 3) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterRefusing));
            } else if (listBean.getSellback_state() == 4) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.merchantPassed));
            }
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
        }
        viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ApplyAfterSalesActivity.class);
                intent.putExtra("num", listBean.getNum());
                intent.putExtra("orderCode", model.getSn());
                intent.putExtra("submitTime", DataUtil.formatData(StringUtils.toLong(model.getCreate_time()), "yyyy-MM-dd HH:mm:ss"));
                intent.putExtra("item_id", String.valueOf(listBean.getItem_id()));
                intent.putExtra("good_id", String.valueOf(listBean.getGoods_id()));
                mContext.startActivity(intent);
            }
        });
        viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBean.getSellback_state() == 0) {
                    Intent intent = new Intent(mContext, ApplyAfterSalesActivity.class);
                    intent.putExtra("num", listBean.getNum());
                    intent.putExtra("orderCode", model.getSn());
                    intent.putExtra("submitTime", DataUtil.formatData(StringUtils.toLong(model.getCreate_time()), "yyyy-MM-dd HH:mm:ss"));
                    intent.putExtra("item_id", String.valueOf(listBean.getItem_id()));
                    intent.putExtra("good_id", String.valueOf(listBean.getGoods_id()));
                    mContext.startActivity(intent);
                    return;
                }
                Intent intent = new Intent(mContext, AfterSalesDetailsActivity.class);
                intent.putExtra("item_id", String.valueOf(listBean.getItem_id()));
                intent.putExtra("good_id", String.valueOf(listBean.getGoods_id()));
                mContext.startActivity(intent);
            }
        });
    }

    public void setResultBeanModel(ResultBean model) {
        this.model = model;
    }


}