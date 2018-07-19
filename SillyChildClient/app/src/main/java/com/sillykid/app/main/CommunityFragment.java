package com.sillykid.app.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 社区
 */
public class CommunityFragment extends BaseFragment {

    private MainActivity aty;

    @BindView(id = R.id.tabLayout)
    private TabLayout tabLayout;




    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_community, null);
    }

    @Override
    protected void initData() {
        super.initData();



    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        //tabLayout

    }












}
