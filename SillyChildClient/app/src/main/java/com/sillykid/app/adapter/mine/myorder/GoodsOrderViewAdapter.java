package com.sillykid.app.adapter.mine.myorder;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean;
import com.sillykid.app.mine.myorder.goodorder.orderdetails.GoodOrderDetailsActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品订单列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodsOrderViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<GoodOrderViewAdapter> countDownCounters;


    public GoodsOrderViewAdapter(Context context) {
        super(context, R.layout.item_goodsorder);
        this.countDownCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_cancelOrder);
        helper.setItemChildClickListener(R.id.tv_payment);
        helper.setItemChildClickListener(R.id.tv_remindDelivery);
        helper.setItemChildClickListener(R.id.tv_checkLogistics);
        helper.setItemChildClickListener(R.id.tv_confirmReceipt);
        helper.setItemChildClickListener(R.id.tv_appraiseOrder);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean model) {
        viewHolderHelper.setText(R.id.tv_orderNumber, model.getSn());
        if (model.getStatus() == 1) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.obligation));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else if (model.getStatus() == 2) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.sendGoods));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else if (model.getStatus() == 3) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.waitGoods));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else if (model.getStatus() == 5 && model.getCommented() == 0) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.completed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.VISIBLE);
        } else if (model.getStatus() == 5 && model.getCommented() == 1) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.completed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else if (model.getStatus() == 7) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.afterSale1));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else if (model.getStatus() == 8) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.haveAfter));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_payment, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remindDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
        } else {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.tradingClosed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.GONE);
        }
        viewHolderHelper.setText(R.id.tv_goodNumber, mContext.getString(R.string.totalOnlyWord) + model.getItemsCount() + mContext.getString(R.string.goods));
        viewHolderHelper.setText(R.id.tv_goodsMoney, MathUtil.keepTwo(StringUtils.toDouble(model.getNeed_pay_money())));
        ChildListView clv_shopgoods = (ChildListView) viewHolderHelper.getView(R.id.clv_shopgoods);
        GoodOrderViewAdapter adapter;
//        if (countDownCounters.get(clv_shopgoods.hashCode()) != null) {
//            adapter = countDownCounters.get(clv_shopgoods.hashCode());
//            adapter.clear();
//            adapter.setResultBeanModel(model);
//            adapter.addNewData(model.getOrderItems());
//        } else {
        adapter = new GoodOrderViewAdapter(mContext, model);
        clv_shopgoods.setAdapter(adapter);
        clv_shopgoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, GoodOrderDetailsActivity.class);
                intent.putExtra("order_id", model.getOrderId());
                mContext.startActivity(intent);
            }
        });
        adapter.clear();
        adapter.addNewData(model.getOrderItems());
        //将此 countDownTimer 放入list.
        countDownCounters.put(clv_shopgoods.hashCode(), adapter);
        //   }
    }


    @Override
    public void clear() {
        super.clear();
        if (countDownCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownCounters.size());
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            GoodOrderViewAdapter cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
    }
}