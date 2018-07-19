package com.sillykid.app.mine.mycollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.sillykid.app.R;

/**
 * 我的收藏中的房源
 * Created by Administrator on 2017/9/2.
 */

public class HouseFragment extends BaseFragment {
    private MyCollectionActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_house, null);
    }
}
