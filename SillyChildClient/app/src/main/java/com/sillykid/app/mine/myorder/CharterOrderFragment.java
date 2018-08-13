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
import com.sillykid.app.mine.myorder.charterorder.AllCharterFragment;
import com.sillykid.app.mine.myorder.charterorder.CompletedCharterFragment;
import com.sillykid.app.mine.myorder.charterorder.EvaluateCharterFragment;
import com.sillykid.app.mine.myorder.charterorder.ObligationCharterFragment;
import com.sillykid.app.mine.myorder.charterorder.OngoingCharterFragment;

/**
 * 我的订单----包车订单
 * Created by Administrator on 2017/9/2.
 */

public class CharterOrderFragment extends BaseFragment {
    private MyOrderActivity aty;

    @BindView(id = R.id.ll_obligation, click = true)
    private LinearLayout ll_obligation;
    @BindView(id = R.id.tv_obligation)
    private TextView tv_obligation;
    @BindView(id = R.id.tv_obligation1)
    private TextView tv_obligation1;

    @BindView(id = R.id.ll_ongoing, click = true)
    private LinearLayout ll_ongoing;
    @BindView(id = R.id.tv_ongoing)
    private TextView tv_ongoing;
    @BindView(id = R.id.tv_ongoing1)
    private TextView tv_ongoing1;

    @BindView(id = R.id.ll_evaluate, click = true)
    private LinearLayout ll_evaluate;
    @BindView(id = R.id.tv_evaluate)
    private TextView tv_evaluate;
    @BindView(id = R.id.tv_evaluate1)
    private TextView tv_evaluate1;

    @BindView(id = R.id.ll_completed, click = true)
    private LinearLayout ll_completed;
    @BindView(id = R.id.tv_completed)
    private TextView tv_completed;
    @BindView(id = R.id.tv_completed1)
    private TextView tv_completed1;

    @BindView(id = R.id.ll_all, click = true)
    private LinearLayout ll_all;
    @BindView(id = R.id.tv_all)
    private TextView tv_all;
    @BindView(id = R.id.tv_all1)
    private TextView tv_all1;

    private ObligationCharterFragment obligationCharterFragment;
    private OngoingCharterFragment ongoingCharterFragment;
    private EvaluateCharterFragment evaluateCharterFragment;
    private CompletedCharterFragment completedCharterFragment;
    private AllCharterFragment allCharterFragment;
    private int chageIcon;//0:待发货，1：进行中，2：待评价，3：完成，4：全部

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_charterorder, null);
    }

    @Override
    protected void initData() {
        super.initData();
        obligationCharterFragment = new ObligationCharterFragment();
        ongoingCharterFragment = new OngoingCharterFragment();
        evaluateCharterFragment = new EvaluateCharterFragment();
        completedCharterFragment = new CompletedCharterFragment();
        allCharterFragment = new AllCharterFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        cleanColors(chageIcon);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_charterOrder, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_obligation:
                chageIcon = 0;
                cleanColors(chageIcon);
                break;
            case R.id.ll_ongoing:
                chageIcon = 1;
                cleanColors(chageIcon);
                break;
            case R.id.ll_evaluate:
                chageIcon = 2;
                cleanColors(chageIcon);
                break;
            case R.id.ll_completed:
                chageIcon = 3;
                cleanColors(chageIcon);
                break;
            case R.id.ll_all:
                chageIcon = 4;
                cleanColors(chageIcon);
                break;
        }


    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_obligation1.setBackgroundResource(R.color.whiteColors);

        tv_ongoing.setTextColor(getResources().getColor(R.color.textColor));
        tv_ongoing1.setBackgroundResource(R.color.whiteColors);

        tv_evaluate.setTextColor(getResources().getColor(R.color.textColor));
        tv_evaluate1.setBackgroundResource(R.color.whiteColors);

        tv_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_completed1.setBackgroundResource(R.color.whiteColors);

        tv_all.setTextColor(getResources().getColor(R.color.textColor));
        tv_all1.setBackgroundResource(R.color.whiteColors);

        if (chageIcon == 0) {
            tv_obligation.setTextColor(getResources().getColor(R.color.greenColors));
            tv_obligation1.setBackgroundResource(R.color.greenColors);
            changeFragment(obligationCharterFragment);
        } else if (chageIcon == 1) {
            tv_ongoing.setTextColor(getResources().getColor(R.color.greenColors));
            tv_ongoing1.setBackgroundResource(R.color.greenColors);
            changeFragment(ongoingCharterFragment);
        } else if (chageIcon == 2) {
            tv_evaluate.setTextColor(getResources().getColor(R.color.greenColors));
            tv_evaluate.setBackgroundResource(R.color.greenColors);
            changeFragment(evaluateCharterFragment);
        } else if (chageIcon == 3) {
            tv_completed.setTextColor(getResources().getColor(R.color.greenColors));
            tv_completed.setBackgroundResource(R.color.greenColors);
            changeFragment(completedCharterFragment);
        } else if (chageIcon == 4) {
            tv_all.setTextColor(getResources().getColor(R.color.greenColors));
            tv_all.setBackgroundResource(R.color.greenColors);
            changeFragment(allCharterFragment);
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

}
