package com.sillykid.app.homepage.message.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.R;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.OperationCallback;

/**
 * 消息设置弹框
 */
public class MessageSettingDialog extends BaseDialog implements View.OnClickListener {

    private ImageView img_IMMessages;
    private ImageView img_systemMessages;
    private boolean isIMMessages = true;

    private boolean isSystemMessages = false;

    public MessageSettingDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_messagesetting);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        img_IMMessages = (ImageView) findViewById(R.id.img_IMMessages);
        img_IMMessages.setOnClickListener(this);
        img_systemMessages = (ImageView) findViewById(R.id.img_systemMessages);
        img_systemMessages.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_IMMessages:
                if (isIMMessages) {
                    RongIM.getInstance().removeNotificationQuietHours(new OperationCallback() {
                        @Override
                        public void onSuccess() {
                            isIMMessages = false;
                            img_IMMessages.setImageResource(R.mipmap.messages_turnoff);
                            ViewInject.toast(mContext.getString(R.string.successfullySet));
                            dismiss();
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {
                            ViewInject.toast(mContext.getString(R.string.setupFailed));
                        }
                    });
                    return;
                }
                RongIM.getInstance().setNotificationQuietHours("00:00:00", 1439, new OperationCallback() {
                    @Override
                    public void onSuccess() {
                        isIMMessages = true;
                        img_IMMessages.setImageResource(R.mipmap.messages_turnon);
                        ViewInject.toast(mContext.getString(R.string.successfullySet));
                        dismiss();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.setupFailed));
                    }
                });
                break;
            case R.id.img_systemMessages:
                if (isSystemMessages) {
                    JPushInterface.resumePush(mContext);
                    img_systemMessages.setImageResource(R.mipmap.messages_turnon);
                    isSystemMessages = false;
                    dismiss();
                    return;
                }
                JPushInterface.stopPush(mContext);
                img_systemMessages.setImageResource(R.mipmap.messages_turnoff);
                isSystemMessages = true;
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


    @Override
    public void show() {
        super.show();
        isSystemMessages = JPushInterface.isPushStopped(mContext);
        if (isSystemMessages) {
            img_systemMessages.setImageResource(R.mipmap.messages_turnon);
        } else {
            img_systemMessages.setImageResource(R.mipmap.messages_turnoff);
        }
        RongIM.getInstance().getNotificationQuietHours(new RongIMClient.GetNotificationQuietHoursCallback() {
            @Override
            public void onSuccess(String s, int i) {
                if (i > 0) {
                    isIMMessages = true;
                    img_IMMessages.setImageResource(R.mipmap.messages_turnon);
                } else {
                    isIMMessages = false;
                    img_IMMessages.setImageResource(R.mipmap.messages_turnoff);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
