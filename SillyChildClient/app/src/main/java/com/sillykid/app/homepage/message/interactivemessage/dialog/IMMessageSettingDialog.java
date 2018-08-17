package com.sillykid.app.homepage.message.interactivemessage.dialog;

import android.app.Activity;
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
import io.rong.imlib.model.Conversation;

/**
 * 消息设置弹框
 */
public class IMMessageSettingDialog extends BaseDialog implements View.OnClickListener {

    private String mTargetId;
    private Conversation.ConversationType mConversationType;
    private Conversation.ConversationNotificationStatus conversationNotificationStatus1;
    private TextView tv_messageFree;


    public IMMessageSettingDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_immessagesetting);
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
        tv_deleteSession.setOnClickListener(this);

        TextView tv_clearMessages = (TextView) findViewById(R.id.tv_clearMessages);
        tv_clearMessages.setOnClickListener(this);

        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_messageFree:
                RongIM.getInstance().setConversationNotificationStatus(mConversationType, mTargetId, conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                    @Override
                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                        ViewInject.toast(mContext.getString(R.string.successfullySet));
                        dismiss();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.setupFailed));
                    }
                });
                break;
            case R.id.tv_deleteSession:
                RongIM.getInstance().removeConversation(mConversationType, mTargetId, new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        ViewInject.toast(mContext.getString(R.string.deleteSuccess));
                        ((Activity) mContext).finish();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.deleteFailed));
                    }
                });
                break;
            case R.id.tv_clearMessages:
                RongIM.getInstance().clearMessages(mConversationType, mTargetId, new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        ViewInject.toast(mContext.getString(R.string.clearSuccess1));
                        dismiss();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        ViewInject.toast(mContext.getString(R.string.clearFailed));
                    }
                });

                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


    public void setConversationType(Conversation.ConversationType mConversationType1, String mTargetId1) {
        mConversationType = mConversationType1;
        mTargetId = mTargetId1;
        RongIM.getInstance().getConversationNotificationStatus(mConversationType, mTargetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                final int value = conversationNotificationStatus.getValue();
                if (value == 1) {
                    conversationNotificationStatus1 = conversationNotificationStatus.setValue(0);
                    tv_messageFree.setText(mContext.getString(R.string.messageFree));
                } else {
                    conversationNotificationStatus1 = conversationNotificationStatus.setValue(1);
                    tv_messageFree.setText(mContext.getString(R.string.cancelAvoidInterruption));
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
