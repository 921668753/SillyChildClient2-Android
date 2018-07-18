package com.sillykid.app.mine.myorder.goodorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.mine.myorder.MyOrderActivity;

/**
 * 我的订单----商品订单
 * Created by Administrator on 2017/9/2.
 */

public class GoodOrderFragment extends BaseFragment {
    private MyOrderActivity aty;

    @BindView(id = R.id.tv_good_obligation, click = true)
    private TextView tv_good_obligation;


    @BindView(id = R.id.tv_good_send, click = true)
    private TextView tv_good_send;


    @BindView(id = R.id.tv_good_wait, click = true)
    private TextView tv_good_wait;


    @BindView(id = R.id.tv_good_completed, click = true)
    private TextView tv_good_completed;


    @BindView(id = R.id.tv_good_afterSale, click = true)
    private TextView tv_good_afterSale;


    @BindView(id = R.id.tv_good_all, click = true)
    private TextView tv_good_all;


    private ObligationGoodFragment obligationGoodFragment;
    private SendGoodsGoodFragment sendGoodsGoodFragment;
    private WaitGoodsGoodFragment waitGoodsGoodFragment;
    private CompletedGoodFragment completedGoodFragment;
    private AfterSaleGoodFragment afterSaleGoodFragment;
    private AllGoodFragment allGoodFragment;
    private int chageIcon = 0;


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
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        cleanColors(0);
        changeFragment(obligationGoodFragment);
        chageIcon = 0;


    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.order_content, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_good_obligation:
                cleanColors(0);
                changeFragment(obligationGoodFragment);
                chageIcon = 0;
                break;
            case R.id.tv_good_send:
                cleanColors(1);
                changeFragment(sendGoodsGoodFragment);
                chageIcon = 1;
                break;
            case R.id.tv_good_wait:
                cleanColors(2);
                changeFragment(waitGoodsGoodFragment);
                chageIcon = 2;
                break;
            case R.id.tv_good_completed:
                cleanColors(3);
                changeFragment(completedGoodFragment);
                chageIcon = 3;
                break;
            case R.id.tv_good_afterSale:
                cleanColors(4);
                changeFragment(afterSaleGoodFragment);
                chageIcon = 4;
                break;
            case R.id.tv_good_all:
                cleanColors(5);
                changeFragment(allGoodFragment);
                chageIcon = 5;
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_good_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_send.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_wait.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_afterSale.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_all.setTextColor(getResources().getColor(R.color.textColor));
        if (chageIcon == 0) {
            tv_good_obligation.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_good_send.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 2) {
            tv_good_wait.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 3) {
            tv_good_completed.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 4) {
            tv_good_afterSale.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 5) {
            tv_good_all.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }

}
