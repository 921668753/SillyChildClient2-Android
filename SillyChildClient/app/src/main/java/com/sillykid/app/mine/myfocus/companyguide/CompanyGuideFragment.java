package com.sillykid.app.mine.myfocus.companyguide;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.sillykid.app.R;
import com.sillykid.app.main.MainActivity;

/**
 * 司导
 */
public class CompanyGuideFragment extends BaseFragment {

    private MainActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_user, null);
    }


}
