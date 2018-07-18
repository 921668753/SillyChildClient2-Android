package com.sillykid.app.mine.myorder.homestayorder;

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
import com.sillykid.app.mine.myorder.goodorder.AfterSaleGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.AllGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.CompletedGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.ObligationGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.SendGoodsGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.WaitGoodsGoodFragment;

/**我的订单----民宿订单
 * Created by Administrator on 2017/9/2.
 */

public class HomestayOrderFragment extends BaseFragment {
    private MyOrderActivity aty;

    @BindView(id=R.id.ll_homestay_ongoing,click = true)
    private LinearLayout ll_homestay_ongoing;
    @BindView(id=R.id.tv_homestay_ongoing)
    private TextView tv_homestay_ongoing;
    @BindView(id=R.id.v_homestay_ongoing)
    private View v_homestay_ongoing;

    @BindView(id=R.id.ll_homestay_evaluate,click = true)
    private LinearLayout ll_homestay_evaluate;
    @BindView(id=R.id.tv_homestay_evaluate)
    private TextView tv_homestay_evaluate;
    @BindView(id=R.id.v_homestay_evaluate)
    private View v_homestay_evaluate;

    @BindView(id=R.id.ll_homestay_completed,click = true)
    private LinearLayout ll_homestay_completed;
    @BindView(id=R.id.tv_homestay_completed)
    private TextView tv_homestay_completed;
    @BindView(id=R.id.v_homestay_completed)
    private View v_homestay_completed;

    @BindView(id=R.id.ll_homestay_all,click = true)
    private LinearLayout ll_homestay_all;
    @BindView(id=R.id.tv_homestay_all)
    private TextView tv_homestay_all;
    @BindView(id=R.id.v_homestay_all)
    private View v_homestay_all;

    @BindView(id=R.id.fl_homestayorder)
    private FrameLayout fl_homestayorder;

    private OngoingHomestayFragment ongoingHomestayFragment;
    private EvaluateHomestayFragment evaluateHomestayFragment;
    private CompletedHomestayFragment completedHomestayFragment;
    private AllHomestayFragment allHomestayFragment;
    private int chageIcon;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homestayorder, null);
    }

    @Override
    protected void initData() {
        super.initData();
        ongoingHomestayFragment =new OngoingHomestayFragment();
        evaluateHomestayFragment =new EvaluateHomestayFragment();
        completedHomestayFragment =new CompletedHomestayFragment();
        allHomestayFragment =new AllHomestayFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        cleanColors(0);
        changeFragment(ongoingHomestayFragment);
        chageIcon = 0;



    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_homestayorder, targetFragment);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_homestay_ongoing:
                cleanColors(0);
                changeFragment(ongoingHomestayFragment);
                chageIcon = 0;
                break;
            case R.id.ll_homestay_evaluate:
                cleanColors(1);
                changeFragment(evaluateHomestayFragment);
                chageIcon = 1;
                break;
            case R.id.ll_homestay_completed:
                cleanColors(2);
                changeFragment(completedHomestayFragment);
                chageIcon = 2;
                break;
            case R.id.ll_homestay_all:
                cleanColors(3);
                changeFragment(allHomestayFragment);
                chageIcon = 3;
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_homestay_ongoing.setTextColor(getResources().getColor(R.color.hintColors));
        v_homestay_ongoing.setBackgroundResource(android.R.color.transparent);
        tv_homestay_evaluate.setTextColor(getResources().getColor(R.color.hintColors));
        v_homestay_evaluate.setBackgroundResource(android.R.color.transparent);
        tv_homestay_completed.setTextColor(getResources().getColor(R.color.hintColors));
        v_homestay_completed.setBackgroundResource(android.R.color.transparent);
        tv_homestay_all.setTextColor(getResources().getColor(R.color.hintColors));
        v_homestay_all.setBackgroundResource(android.R.color.transparent);

        if (chageIcon == 0) {
            tv_homestay_ongoing.setTextColor(getResources().getColor(R.color.greenColors));
            v_homestay_ongoing.setBackgroundResource(R.color.greenColors);
        } else if (chageIcon == 1) {
            tv_homestay_evaluate.setTextColor(getResources().getColor(R.color.greenColors));
            v_homestay_evaluate.setBackgroundResource(R.color.greenColors);
        } else if (chageIcon == 2) {
            tv_homestay_completed.setTextColor(getResources().getColor(R.color.greenColors));
            v_homestay_completed.setBackgroundResource(R.color.greenColors);
        }else if (chageIcon == 3) {
            tv_homestay_all.setTextColor(getResources().getColor(R.color.greenColors));
            v_homestay_all.setBackgroundResource(R.color.greenColors);
        }
    }

}
