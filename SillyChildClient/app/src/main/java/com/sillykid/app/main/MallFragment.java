package com.sillykid.app.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.MallHomeCategoryAdapter;

/**
 * 商城
 * Created by Admin on 2017/8/10.
 */

public class MallFragment extends BaseFragment implements MallContract.View {

    private MainActivity aty;

    @BindView(id = R.id.gv_classification)
    private NoScrollGridView gv_classification;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mall, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MallPresenter(this);

        MallHomeCategoryAdapter mallHomeCategoryAdapter=new MallHomeCategoryAdapter(aty);
        gv_classification.setAdapter(mallHomeCategoryAdapter);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

    }

    @Override
    public void setPresenter(MallContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
