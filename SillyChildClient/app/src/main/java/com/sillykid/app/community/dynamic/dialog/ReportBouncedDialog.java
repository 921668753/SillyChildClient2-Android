package com.sillykid.app.community.dynamic.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;

/**
 * 举报弹框
 */
public class ReportBouncedDialog extends BaseDialog implements View.OnClickListener, ReportBouncedContract.View {

    private int post_id = 0;

    public ReportBouncedDialog(Context context, int post_id) {
        super(context, R.style.MyDialog);
        this.post_id = post_id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reportbounced);
        initView();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        mPresenter = new ReportBouncedPresenter(this);
        TextView tv_report = (TextView) findViewById(R.id.tv_report);
        tv_report.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_report:
                showLoadingDialog(mContext.getString(R.string.reportLoad));
                ((ReportBouncedContract.Presenter) mPresenter).postReport(post_id);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void setPresenter(ReportBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(mContext.getString(R.string.thankReport));
        dismiss();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
            return;
        }
        ViewInject.toast(msg);
    }

}
