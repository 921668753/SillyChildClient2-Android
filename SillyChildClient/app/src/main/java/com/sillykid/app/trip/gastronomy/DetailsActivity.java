package com.sillykid.app.trip.gastronomy;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 美食详情
 * Created by Admin on 2017/9/4.
 */

public class DetailsActivity extends BaseActivity implements DetailsContract.View {


    @BindView(id = R.id.tv_commentaries, click = true)
    private TextView tv_commentaries;

    @BindView(id = R.id.ll_mapAddress, click = true)
    private LinearLayout ll_mapAddress;

    @BindView(id = R.id.ll_askRoadCueCard, click = true)
    private LinearLayout ll_askRoadCueCard;

    @BindView(id = R.id.tv_viewAllComments, click = true)
    private TextView tv_viewAllComments;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_details);
    }

    @Override
    public void initData() {
        super.initData();
        String id = getIntent().getStringExtra("id");


    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_mapAddress:


                break;
            case R.id.ll_askRoadCueCard:
                Intent intent = new Intent(aty, AskRoadCueCardActivity.class);
                intent.putExtra("askRoadCueCard", "");
                showActivity(aty, intent);
                break;
        }
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
