package com.sillykid.app.mine.myorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.mine.myorder.goodorder.AfterSaleGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.AllGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.CompletedGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.ObligationGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.SendGoodsGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.WaitGoodsGoodFragment;

/**
 * 我的订单----商品订单
 * Created by Administrator on 2017/9/2.
 */

public class GoodOrderFragment extends BaseFragment {

    private MyOrderActivity aty;

    @BindView(id = R.id.ll_good_obligation, click = true)
    private LinearLayout ll_good_obligation;

    @BindView(id = R.id.tv_good_obligation)
    private TextView tv_good_obligation;

    @BindView(id = R.id.tv_good_obligation1)
    private TextView tv_good_obligation1;


    @BindView(id = R.id.ll_good_send, click = true)
    private LinearLayout ll_good_send;

    @BindView(id = R.id.tv_good_send)
    private TextView tv_good_send;

    @BindView(id = R.id.tv_good_send1)
    private TextView tv_good_send1;


    @BindView(id = R.id.ll_good_wait, click = true)
    private LinearLayout ll_good_wait;

    @BindView(id = R.id.tv_good_wait)
    private TextView tv_good_wait;

    @BindView(id = R.id.tv_good_wait1)
    private TextView tv_good_wait1;


    @BindView(id = R.id.ll_good_completed, click = true)
    private LinearLayout ll_good_completed;

    @BindView(id = R.id.tv_good_completed)
    private TextView tv_good_completed;

    @BindView(id = R.id.tv_good_completed1)
    private TextView tv_good_completed1;


    @BindView(id = R.id.ll_good_afterSale, click = true)
    private LinearLayout ll_good_afterSale;

    @BindView(id = R.id.tv_good_afterSale)
    private TextView tv_good_afterSale;

    @BindView(id = R.id.tv_good_afterSale1)
    private TextView tv_good_afterSale1;


    @BindView(id = R.id.ll_good_all, click = true)
    private LinearLayout ll_good_all;

    @BindView(id = R.id.tv_good_all)
    private TextView tv_good_all;

    @BindView(id = R.id.tv_good_all1)
    private TextView tv_good_all1;


    private BaseFragment obligationGoodFragment;
    private BaseFragment sendGoodsGoodFragment;
    private BaseFragment waitGoodsGoodFragment;
    private BaseFragment completedGoodFragment;
    private BaseFragment afterSaleGoodFragment;
    private BaseFragment allGoodFragment;

    private int chageIcon = 20;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_goodorder, null);
    }

    @Override
    protected void initData() {
        super.initData();
        obligationGoodFragment = new ObligationGoodFragment();
        sendGoodsGoodFragment = new SendGoodsGoodFragment();
        waitGoodsGoodFragment = new WaitGoodsGoodFragment();
        completedGoodFragment = new CompletedGoodFragment();
        afterSaleGoodFragment = new AfterSaleGoodFragment();
        allGoodFragment = new AllGoodFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 20);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        cleanColors(20);
        changeFragment(obligationGoodFragment);
        chageIcon = 20;


    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.order_goodcontent, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_good_obligation:
                cleanColors(20);
                changeFragment(obligationGoodFragment);
                chageIcon = 20;
                break;
            case R.id.ll_good_send:
                cleanColors(21);
                changeFragment(sendGoodsGoodFragment);
                chageIcon = 21;
                break;
            case R.id.ll_good_wait:
                cleanColors(22);
                changeFragment(waitGoodsGoodFragment);
                chageIcon = 22;
                break;
            case R.id.ll_good_completed:
                cleanColors(23);
                changeFragment(completedGoodFragment);
                chageIcon = 23;
                break;
            case R.id.ll_good_afterSale:
                cleanColors(24);
                changeFragment(afterSaleGoodFragment);
                chageIcon = 24;
                break;
            case R.id.ll_good_all:
                cleanColors(25);
                changeFragment(allGoodFragment);
                chageIcon = 25;
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_good_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_obligation1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_good_send.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_send1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_good_wait.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_wait1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_good_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_completed1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_good_afterSale.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_afterSale1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_good_all.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_all1.setTextColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 20) {
            tv_good_obligation.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_obligation1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 21) {
            tv_good_send.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_send.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 22) {
            tv_good_wait.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_wait1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 23) {
            tv_good_completed.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_completed1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 24) {
            tv_good_afterSale.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_afterSale1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 25) {
            tv_good_all.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_all1.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }
}
