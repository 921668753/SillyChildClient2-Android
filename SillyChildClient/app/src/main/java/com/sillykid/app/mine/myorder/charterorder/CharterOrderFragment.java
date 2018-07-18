package com.sillykid.app.mine.myorder.charterorder;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.mine.myorder.MyOrderActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;

/**
 * 我的订单----包车订单
 * Created by Administrator on 2017/9/2.
 */

public class CharterOrderFragment extends BaseFragment {
    private MyOrderActivity aty;

    @BindView(id = R.id.ll_charter_obligation, click = true)
    private LinearLayout ll_charter_obligation;
    @BindView(id = R.id.rl_charter_obligation, click = true)
    private RelativeLayout rl_charter_obligation;
    @BindView(id = R.id.tv_charter_obligation, click = true)
    private TextView tv_charter_obligation;
    @BindView(id = R.id.tv_charter_obligationnum, click = true)
    private TextView tv_charter_obligationnum;
    @BindView(id = R.id.v_charter_obligation)
    private View v_charter_obligation;

    @BindView(id = R.id.ll_charter_ongoing, click = true)
    private LinearLayout ll_charter_ongoing;
    @BindView(id = R.id.rl_charter_ongoing, click = true)
    private RelativeLayout rl_charter_ongoing;
    @BindView(id = R.id.tv_charter_ongoing, click = true)
    private TextView tv_charter_ongoing;
    @BindView(id = R.id.tv_charter_ongoingnum, click = true)
    private TextView tv_charter_ongoingnum;
    @BindView(id = R.id.v_charter_ongoing)
    private View v_charter_ongoing;

    @BindView(id = R.id.ll_charter_evaluate, click = true)
    private LinearLayout ll_charter_evaluate;
    @BindView(id = R.id.rl_charter_evaluate, click = true)
    private RelativeLayout rl_charter_evaluate;
    @BindView(id = R.id.tv_charter_evaluate, click = true)
    private TextView tv_charter_evaluate;
    @BindView(id = R.id.tv_charter_evaluatenum, click = true)
    private TextView tv_charter_evaluatenum;
    @BindView(id = R.id.v_charter_evaluate)
    private View v_charter_evaluate;

    @BindView(id = R.id.ll_charter_completed, click = true)
    private LinearLayout ll_charter_completed;
    @BindView(id = R.id.tv_charter_completed)
    private TextView tv_charter_completed;
    @BindView(id = R.id.v_charter_completed)
    private View v_charter_completed;

    @BindView(id = R.id.ll_charter_all, click = true)
    private LinearLayout ll_charter_all;
    @BindView(id = R.id.tv_charter_all)
    private TextView tv_charter_all;
    @BindView(id = R.id.v_charter_all)
    private View v_charter_all;

    @BindView(id = R.id.fl_charterorder)
    private FrameLayout fl_charterorder;

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
        cleanColors();
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_charterorder, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_charter_obligation:
            case R.id.rl_charter_obligation:
            case R.id.tv_charter_obligation:
            case R.id.tv_charter_obligationnum:
                chageIcon = 0;
                cleanColors();
                break;
            case R.id.ll_charter_ongoing:
            case R.id.rl_charter_ongoing:
            case R.id.tv_charter_ongoing:
            case R.id.tv_charter_ongoingnum:
                chageIcon = 1;
                cleanColors();
                break;
            case R.id.ll_charter_evaluate:
            case R.id.rl_charter_evaluate:
            case R.id.tv_charter_evaluate:
            case R.id.tv_charter_evaluatenum:
                chageIcon = 2;
                cleanColors();
                break;
            case R.id.ll_charter_completed:
                chageIcon = 3;
                cleanColors();
                break;
            case R.id.ll_charter_all:
                chageIcon = 4;
                cleanColors();
                break;
        }


    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors() {
        tv_charter_obligation.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter_obligation.setBackgroundResource(android.R.color.transparent);
        tv_charter_ongoing.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter_ongoing.setBackgroundResource(android.R.color.transparent);
        tv_charter_evaluate.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter_evaluate.setBackgroundResource(android.R.color.transparent);
        tv_charter_completed.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter_completed.setBackgroundResource(android.R.color.transparent);
        tv_charter_all.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter_all.setBackgroundResource(android.R.color.transparent);
        if (chageIcon == 0) {
            tv_charter_obligation.setTextColor(getResources().getColor(R.color.greenColors));
            v_charter_obligation.setBackgroundResource(R.color.greenColors);
            changeFragment(obligationCharterFragment);
            if (obligationCharterFragment.getFragmentJumpBetween()!=null)obligationCharterFragment.getFragmentJumpBetween().fragmentPosition();
        } else if (chageIcon == 1) {
            tv_charter_ongoing.setTextColor(getResources().getColor(R.color.greenColors));
            v_charter_ongoing.setBackgroundResource(R.color.greenColors);
            changeFragment(ongoingCharterFragment);
            if (ongoingCharterFragment.getFragmentJumpBetween()!=null)ongoingCharterFragment.getFragmentJumpBetween().fragmentPosition();
        } else if (chageIcon == 2) {
            tv_charter_evaluate.setTextColor(getResources().getColor(R.color.greenColors));
            v_charter_evaluate.setBackgroundResource(R.color.greenColors);
            changeFragment(evaluateCharterFragment);
            if (evaluateCharterFragment.getFragmentJumpBetween()!=null)evaluateCharterFragment.getFragmentJumpBetween().fragmentPosition();
        } else if (chageIcon == 3) {
            tv_charter_completed.setTextColor(getResources().getColor(R.color.greenColors));
            v_charter_completed.setBackgroundResource(R.color.greenColors);
            changeFragment(completedCharterFragment);
            if (completedCharterFragment.getFragmentJumpBetween()!=null)completedCharterFragment.getFragmentJumpBetween().fragmentPosition();
        } else if (chageIcon == 4) {
            tv_charter_all.setTextColor(getResources().getColor(R.color.greenColors));
            v_charter_all.setBackgroundResource(R.color.greenColors);
            changeFragment(allCharterFragment);
            if (allCharterFragment.getFragmentJumpBetween()!=null)allCharterFragment.getFragmentJumpBetween().fragmentPosition();
        }
    }

    /**
     * 初始化角标
     */
    public void initAngle(String obligationnum,String ongoingnum,String evaluatenum){
        if (TextUtils.isEmpty(obligationnum)|| StringUtils.toInt(obligationnum,0)==0){
            tv_charter_obligationnum.setVisibility(View.GONE);
        }else{
            tv_charter_obligationnum.setVisibility(View.VISIBLE);
            tv_charter_obligationnum.setText(obligationnum);
        }

        if (TextUtils.isEmpty(ongoingnum)|| StringUtils.toInt(ongoingnum,0)==0){
            tv_charter_ongoingnum.setVisibility(View.GONE);
        }else{
            tv_charter_ongoingnum.setVisibility(View.VISIBLE);
            tv_charter_ongoingnum.setText(ongoingnum);
        }

        if (TextUtils.isEmpty(evaluatenum)|| StringUtils.toInt(evaluatenum,0)==0){
            tv_charter_evaluatenum.setVisibility(View.GONE);
        }else{
            tv_charter_evaluatenum.setVisibility(View.VISIBLE);
            tv_charter_evaluatenum.setText(evaluatenum);
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

}
