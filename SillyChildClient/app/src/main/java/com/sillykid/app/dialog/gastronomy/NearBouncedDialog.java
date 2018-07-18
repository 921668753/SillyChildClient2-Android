package com.sillykid.app.dialog.gastronomy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.myview.ChildListView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.gastronomybounced.Near1BouncedViewAdapter;
import com.sillykid.app.adapter.gastronomybounced.NearBouncedViewAdapter;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 美食-----附近
 * Created by Admin on 2017/7/23.
 */

public class NearBouncedDialog extends Dialog implements View.OnClickListener, NearBouncedContract.View, AdapterView.OnItemClickListener {

    private Context context;
    private ChildListView lv_nearDialog;
    private NearBouncedViewAdapter nearBouncedViewAdapter;
    private Near1BouncedViewAdapter near1BouncedViewAdapter;
    private ChildListView lv_near1Dialog;
    private NearBouncedContract.Presenter mPresenter;
    private SweetAlertDialog mLoadingDialog;

    public NearBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_nearbounced);
        initView();
    }

    private void initView() {
        mPresenter = new NearBouncedPresenter(this);
        lv_nearDialog = (ChildListView) findViewById(R.id.lv_nearDialog);
        lv_near1Dialog = (ChildListView) findViewById(R.id.lv_near1Dialog);
        nearBouncedViewAdapter = new NearBouncedViewAdapter(context);
        lv_nearDialog.setAdapter(nearBouncedViewAdapter);
        near1BouncedViewAdapter = new Near1BouncedViewAdapter(context);
        lv_near1Dialog.setAdapter(near1BouncedViewAdapter);


    }


    @Override
    public void onClick(View view) {


    }


    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(KJActivityStack.create().topActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(context.getResources().getColor(com.common.cklibrary.R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }

    @Override
    public void setPresenter(NearBouncedContract.Presenter presenter) {

    }

    @Override
    public void getSuccess(String success, int flag) {


        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {


        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




    }

}
