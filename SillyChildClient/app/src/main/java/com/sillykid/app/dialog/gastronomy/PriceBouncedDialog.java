package com.sillykid.app.dialog.gastronomy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.myview.ChildListView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.gastronomybounced.PriceBouncedViewAdapter;
import com.sillykid.app.entity.IndexCityBean;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 美食-----价格
 * Created by Admin on 2017/7/23.
 */

public class PriceBouncedDialog extends Dialog implements View.OnClickListener, PriceBouncedContract.View, AdapterView.OnItemClickListener {
    private Context context;
    private ChildListView lv_gastronomyDialog;
    private PriceBouncedViewAdapter priceBouncedViewAdapter;
    private PriceBouncedContract.Presenter mPresenter;
    private SweetAlertDialog mLoadingDialog;
    private IndexCityBean.ResultBean bean;

    public PriceBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        // this.destination = destination;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_gastronomybounced);
        initView();
    }

    private void initView() {
        mPresenter = new PriceBouncedPresenter(this);
        lv_gastronomyDialog = (ChildListView) findViewById(R.id.lv_gastronomyDialog);
        priceBouncedViewAdapter = new PriceBouncedViewAdapter(context);
        lv_gastronomyDialog.setAdapter(priceBouncedViewAdapter);
        lv_gastronomyDialog.setOnItemClickListener(this);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getPriceBounced();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.tv_baidu:
//
//                break;
//            case R.id.tv_gaode:
//                dismiss();
//                //高德
//                initGaoDeMap("", destination);
//                break;
//            case R.id.tv_cancel:
//                dismiss();
//                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        bean = priceBouncedViewAdapter.getItem(i);
        setListPrice(bean.getId());
    }

    /**
     * 设置数据
     *
     * @param state
     */
    private void setListPrice(int state) {
        List<IndexCityBean.ResultBean> list = priceBouncedViewAdapter.getData();
        for (int i = 0; i < list.size(); i++) {
            if (state == list.get(i).getId()) {
                list.get(i).setStatus(1);
            } else {
                list.get(i).setStatus(0);
            }
        }
        priceBouncedViewAdapter.clear();
        priceBouncedViewAdapter.addNewData(list);
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
    public void setPresenter(PriceBouncedContract.Presenter presenter) {
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
