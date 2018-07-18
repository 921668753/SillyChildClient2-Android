package com.sillykid.app.dialog.chartercustom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.common.cklibrary.common.KJActivityStack;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.IndexCityBean;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 费用说明弹框
 * Created by Administrator on 2017/9/27.
 */

public class CostsThatDialog extends Dialog implements View.OnClickListener {

    private final String text;
    private Context context;

    private SweetAlertDialog mLoadingDialog;
    private IndexCityBean.ResultBean bean;
    private ImageView img_back;
    private WebView web_view;


    public CostsThatDialog(Context context, String text) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.text = text;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_coststhat1);
        initView();
//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        //   mPresenter = new CompensationChangeBackPresenter(this);
        // tv_title = (TextView) findViewById(R.id.tv_title);
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
        // showLoadingDialog(context.getString(R.string.dataLoad));
        if (StringUtils.isEmpty(text)) {
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + context.getString(R.string.noExplanation)
                    + "</body></html>";
            web_view.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        }else {
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + text
                    + "</body></html>";
            web_view.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        }
        //  mPresenter.getCompensationChangeBack(24);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:
                dismiss();
                break;
        }
    }


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

}
