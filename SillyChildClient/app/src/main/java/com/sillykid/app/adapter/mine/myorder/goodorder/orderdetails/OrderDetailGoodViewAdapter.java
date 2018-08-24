package com.sillykid.app.adapter.mine.myorder.goodorder.orderdetails;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.orderdetails.OrderDetailBean.DataBeanX.ItemListBean;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.AfterSalesDetailsActivity;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class OrderDetailGoodViewAdapter extends BGAAdapterViewAdapter<ItemListBean> {

    private String paymoney = "";

    private String create_time = "";

    private int status = 0;

    public OrderDetailGoodViewAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ItemListBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, model.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());
        viewHolderHelper.setText(R.id.tv_number, String.valueOf(model.getNum()));
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));

        if (status == 4 || status == 5) {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ApplyAfterSalesActivity.class);
                    intent.putExtra("orderCode", model.getSn());
                    intent.putExtra("submitTime", create_time);
                    // intent.putExtra("submitTime", DataUtil.formatData(StringUtils.toLong(create_time), "yyyy-MM-dd HH:mm:ss"));
                    intent.putExtra("num", model.getNum());
                    intent.putExtra("item_id", String.valueOf(model.getItem_id()));
                    intent.putExtra("good_id", String.valueOf(model.getGoods_id()));
                    mContext.startActivity(intent);
                }
            });
        } else if (status == 7 || status == 8) {
            if (model.getSellback_state() == 0) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.applyAfterSales));
            } else if (model.getSellback_state() == 1) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.applyAfterSales1));
            } else if (model.getSellback_state() == 2) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterComplete));
            } else if (model.getSellback_state() == 3) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.afterRefusing));
            }else if (model.getSellback_state() == 4) {
                viewHolderHelper.setText(R.id.tv_checkAfterSale, mContext.getString(R.string.merchantPassed));
            }
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (model.getSellback_state() == 0) {
                        Intent intent = new Intent(mContext, ApplyAfterSalesActivity.class);
                        intent.putExtra("num", model.getNum());
                        intent.putExtra("orderCode", model.getSn());
                  //      intent.putExtra("submitTime", DataUtil.formatData(StringUtils.toLong(model.getCreate_time()), "yyyy-MM-dd HH:mm:ss"));
                        intent.putExtra("item_id", String.valueOf(model.getItem_id()));
                        intent.putExtra("good_id", String.valueOf(model.getGoods_id()));
                        mContext.startActivity(intent);
                        return;
                    }
                    Intent intent = new Intent(mContext, AfterSalesDetailsActivity.class);
                    intent.putExtra("item_id", String.valueOf(model.getItem_id()));
                    intent.putExtra("sellback_state", String.valueOf(model.getSellback_state()));
                    intent.putExtra("good_id", String.valueOf(model.getGoods_id()));
                    mContext.startActivity(intent);
                }
            });
        } else {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(null);
        }

    }

    public void setStatus(int status, String paymoney, String create_time) {
        this.status = status;
        this.paymoney = paymoney;
        this.create_time = create_time;
        notifyDataSetChanged();
    }
}