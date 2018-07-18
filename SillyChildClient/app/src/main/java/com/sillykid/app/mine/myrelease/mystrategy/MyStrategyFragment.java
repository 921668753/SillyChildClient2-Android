package com.sillykid.app.mine.myrelease.mystrategy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.sillykid.app.R;
import com.sillykid.app.main.MainActivity;

/**
 * 我的攻略
 */
public class MyStrategyFragment extends BaseFragment {

    private MainActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mystrategy, null);
    }


}
