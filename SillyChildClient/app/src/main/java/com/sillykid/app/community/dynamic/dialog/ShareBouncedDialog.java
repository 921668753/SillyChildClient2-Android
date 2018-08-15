package com.sillykid.app.community.dynamic.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/* 分享--------分享弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class ShareBouncedDialog extends BaseDialog implements View.OnClickListener, ReportBouncedContract.View {

    private int post_id = 0;

    public ShareBouncedDialog(Context context, int post_id) {
        super(context, R.style.MyDialog);
        this.post_id = post_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sharevideobounced);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        mPresenter = new ReportBouncedPresenter(this);
        LinearLayout ll_weChatFriends = (LinearLayout) findViewById(R.id.ll_weChatFriends);
        ll_weChatFriends.setOnClickListener(this);
        LinearLayout ll_circleFriends = (LinearLayout) findViewById(R.id.ll_circleFriends);
        ll_circleFriends.setOnClickListener(this);
        LinearLayout ll_QQFriends = (LinearLayout) findViewById(R.id.ll_QQFriends);
        ll_QQFriends.setOnClickListener(this);
        LinearLayout ll_sina = (LinearLayout) findViewById(R.id.ll_sina);
        ll_sina.setOnClickListener(this);
        LinearLayout ll_report = (LinearLayout) findViewById(R.id.ll_report);
        ll_report.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weChatFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) mContext, SHARE_MEDIA.WEIXIN)) {
                    ViewInject.toast(mContext.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_circleFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) mContext, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    ViewInject.toast(mContext.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.ll_QQFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) mContext, SHARE_MEDIA.QQ)) {
                    ViewInject.toast(mContext.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_sina:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) mContext, SHARE_MEDIA.SINA)) {
                    ViewInject.toast(mContext.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.SINA);
                break;
            case R.id.ll_report:
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

    public abstract void share(SHARE_MEDIA platform);

}
