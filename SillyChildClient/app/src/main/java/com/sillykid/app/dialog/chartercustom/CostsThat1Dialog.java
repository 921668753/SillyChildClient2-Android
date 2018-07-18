package com.sillykid.app.dialog.chartercustom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.CompensationChangeBackBean;
import com.sillykid.app.entity.IndexCityBean;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 费用说明弹框
 * Created by Administrator on 2017/9/27.
 */

public class CostsThat1Dialog extends Dialog implements CompensationChangeBackContract.View, View.OnClickListener {

    private final int type;
    private Context context;

    private CompensationChangeBackContract.Presenter mPresenter;
    private SweetAlertDialog mLoadingDialog;
    private IndexCityBean.ResultBean bean;
    private TextView tv_title;
    private ImageView img_back;
    private WebView web_view;


    public CostsThat1Dialog(Context context, int type) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.type = type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_coststhat1);
        initView();
    }

    private void initView() {
        mPresenter = new CompensationChangeBackPresenter(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        web_view = (WebView) findViewById(R.id.web_view);
        web_view.setVerticalScrollBarEnabled(false); //垂直不显示
        WebSettings webSettings = web_view.getSettings();
////如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
////设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
////缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
////其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");////设置默认为utf-8
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getCompensationChangeBack(type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:
                dismiss();
                break;
        }
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
    public void setPresenter(CompensationChangeBackContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        CompensationChangeBackBean compensationChangeBackBean = (CompensationChangeBackBean) JsonUtil.getInstance().json2Obj(success, CompensationChangeBackBean.class);
        tv_title.setText(compensationChangeBackBean.getData().getTitle());
        if (!StringUtils.isEmpty(compensationChangeBackBean.getData().getContent())) {
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + compensationChangeBackBean.getData().getContent()
                    + "</body></html>";
            web_view.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        }
        dismissLoadingDialog();
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


}
