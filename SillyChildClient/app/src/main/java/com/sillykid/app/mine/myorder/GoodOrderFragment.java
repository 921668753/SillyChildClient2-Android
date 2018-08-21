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


    @BindView(id = R.id.ll_goodSend, click = true)
    private LinearLayout ll_goodSend;

    @BindView(id = R.id.tv_goodSend)
    private TextView tv_goodSend;

    @BindView(id = R.id.tv_goodSend1)
    private TextView tv_goodSend1;


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

    private int chageIcon = 10;


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
        chageIcon = aty.getIntent().getIntExtra("chageGoodIcon", 10);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (chageIcon == 10) {
            chageIcon = 10;
        } else if (chageIcon == 11) {
            chageIcon = 11;
        } else if (chageIcon == 12) {
            chageIcon = 12;
        } else if (chageIcon == 13) {
            chageIcon = 13;
        } else if (chageIcon == 14) {
            chageIcon = 14;
        } else if (chageIcon == 15) {
            chageIcon = 15;
        } else {
            chageIcon = 10;
        }
        cleanColors(chageIcon);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.order_goodcontent, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_good_obligation:
                chageIcon = 10;
                cleanColors(chageIcon);
                break;
            case R.id.ll_goodSend:
                chageIcon = 11;
                cleanColors(chageIcon);
                break;
            case R.id.ll_good_wait:
                chageIcon = 12;
                cleanColors(chageIcon);
                break;
            case R.id.ll_good_completed:
                chageIcon = 13;
                cleanColors(chageIcon);
                break;
            case R.id.ll_good_afterSale:
                chageIcon = 14;
                cleanColors(chageIcon);
                break;
            case R.id.ll_good_all:
                chageIcon = 15;
                cleanColors(chageIcon);
                break;
        }
    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_good_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_obligation1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_goodSend.setTextColor(getResources().getColor(R.color.textColor));
        tv_goodSend1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_good_wait.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_wait1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_good_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_completed1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_good_afterSale.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_afterSale1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_good_all.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_all1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 10) {
            tv_good_obligation.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_obligation1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(obligationGoodFragment);
        } else if (chageIcon == 11) {
            tv_goodSend.setTextColor(getResources().getColor(R.color.greenColors));
            tv_goodSend1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(sendGoodsGoodFragment);
        } else if (chageIcon == 12) {
            tv_good_wait.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_wait1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(waitGoodsGoodFragment);
        } else if (chageIcon == 13) {
            tv_good_completed.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_completed1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(completedGoodFragment);
        } else if (chageIcon == 14) {
            tv_good_afterSale.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_afterSale1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(afterSaleGoodFragment);
        } else if (chageIcon == 15) {
            tv_good_all.setTextColor(getResources().getColor(R.color.greenColors));
            tv_good_all1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(allGoodFragment);
        }
    }
}
