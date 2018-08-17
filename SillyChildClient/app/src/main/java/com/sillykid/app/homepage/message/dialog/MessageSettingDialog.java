package com.sillykid.app.homepage.message.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.OperationCallback;
import io.rong.imlib.model.Conversation;

/**
 * 消息设置弹框
 */
public class MessageSettingDialog extends BaseDialog implements View.OnClickListener {

    private String mTargetId;
    private Conversation.ConversationType mConversationType;
    private Conversation.ConversationNotificationStatus conversationNotificationStatus1;
    private TextView tv_messageFree;


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
        tv_messageFree = (TextView) findViewById(R.id.tv_messageFree);
        tv_messageFree.setOnClickListener(this);
        TextView tv_deleteSession = (TextView) findViewById(R.id.tv_deleteSession);
        tv_deleteSession.setVisibility(View.GONE);
        TextView tv_divider = (TextView) findViewById(R.id.tv_divider);
        tv_divider.setVisibility(View.GONE);
        TextView tv_clearMessages = (TextView) findViewById(R.id.tv_clearMessages);
        tv_clearMessages.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_messageFree:
                RongIM.getInstance().setNotificationQuietHours("00:00:00", 1440, new OperationCallback() {
                    @Override
                    public void onSuccess() {
                        ViewInject.toast(mContext.getString(R.string.successfullySet));
                        dismiss();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.setupFailed));
                    }
                });
                break;
            case R.id.tv_clearMessages:
                RongIM.getInstance().removeNotificationQuietHours(new OperationCallback() {
                    @Override
                    public void onSuccess() {
                        ViewInject.toast(mContext.getString(R.string.successfullySet));
                        dismiss();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.setupFailed));
                    }
                });
                break;
//            case R.id.tv_clearMessages:
//
//                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

}
