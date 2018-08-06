package com.sillykid.app.community.dynamic.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.myview.TextEditTextView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

/**
 * 动态评价/回复
 * Created by Admin on 2018/7/23.
 */
public abstract class RevertBouncedDialog extends BaseDialog implements View.OnClickListener, RevertBouncedContract.View {

    TextEditTextView tetv_revert;
    private Handler handler;
    private int post_id = 0;
    private int reply_comment_id = 0;
    private int reply_member_id = 0;
    private int type = 0;

    public RevertBouncedDialog(Context context) {
        super(context, R.style.MyDialog1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_revertbounced);
        initView();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        handler = new Handler();
        mPresenter = new RevertBouncedPresenter(this);
        RelativeLayout rl_cancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        rl_cancel.setOnClickListener(this);
        tetv_revert = (TextEditTextView) findViewById(R.id.tetv_revert);
        tetv_revert.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (StringUtils.isEmpty(textView.getText().toString().trim())) {
                        ViewInject.toast(mContext.getString(R.string.writeComment1));
                        handled = true;
                        return handled;
                    }
                    showLoadingDialog(mContext.getString(R.string.sendingLoad));
                    ((RevertBouncedContract.Presenter) mPresenter).postAddComment(mContext, textView.getText().toString().trim(), post_id, reply_comment_id, reply_member_id, type);
                    handled = true;
                }
                return handled;
            }
        });
        //键盘隐藏监听
        tetv_revert.setOnKeyBoardHideListener(new TextEditTextView.OnKeyBoardHideListener() {

            @Override
            public void onKeyHide(int keyCode, KeyEvent event) {
                tetv_revert.setText("");
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_cancel:
                tetv_revert.setText("");
                dismiss();
                break;

        }
    }

    /**
     * 设置提示
     */
    public void setHintText(String hint, int post_id, int reply_comment_id, int reply_member_id, int type) {
        TextEditTextView tetv_revert = (TextEditTextView) findViewById(R.id.tetv_revert);
        tetv_revert.setHint(hint);
        this.post_id = post_id;
        this.reply_comment_id = reply_comment_id;
        this.reply_member_id = reply_member_id;
        this.type = type;
        if (handler == null) {
            handler = new Handler();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SoftKeyboardUtils.ejectKeyboard(tetv_revert);
            }
        }, 200);
    }

    @Override
    public void setPresenter(RevertBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        tetv_revert.setText("");
        SoftKeyboardUtils.packUpKeyboard((Activity) mContext);
        ViewInject.toast(mContext.getString(R.string.commentSuccess));
        dismiss();
        toSuccess();
    }

    public abstract void toSuccess();


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        handler = null;
    }


}
