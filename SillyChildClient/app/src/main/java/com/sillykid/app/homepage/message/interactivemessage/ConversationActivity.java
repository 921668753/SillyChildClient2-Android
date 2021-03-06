package com.sillykid.app.homepage.message.interactivemessage;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.application.RongCloudBean;
import com.sillykid.app.homepage.message.interactivemessage.dialog.IMMessageSettingDialog;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.rong.imkit.RongIM;
import io.rong.imlib.CustomServiceConfig;
import io.rong.imlib.ICustomServiceListener;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.CSGroupItem;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.CustomServiceMode;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * 会话页面
 * 1，设置 ActionBar title
 * 2，加载会话页面
 * 3，push 和 通知 判断
 */
public class ConversationActivity extends BaseActivity implements ConversationContract.View {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 对方id
     */
    private String mTargetId;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    /**
     * title
     */
    private String title;

    private final String TextTypingTitle = "对方正在输入...";
    private final String VoiceTypingTitle = "对方正在讲话...";

    private Handler mHandler;

    public static final int SET_TEXT_TYPING_TITLE = 1;
    public static final int SET_VOICE_TYPING_TITLE = 2;
    public static final int SET_TARGET_ID_TITLE = 0;
    private IMMessageSettingDialog messageSettingDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ConversationPresenter(this);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            return;
        }
        mTargetId = intent.getData().getQueryParameter("targetId");
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        title = intent.getData().getQueryParameter("title");
        initMessageSettingDialog();
    }

    private void initMessageSettingDialog() {
        messageSettingDialog = new IMMessageSettingDialog(this);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        showLoadingDialog(getString(R.string.dataLoad));
        if (StringUtils.isEmpty(title)) {
            ((ConversationContract.Presenter) mPresenter).getUserInfo(mTargetId);
        } else {
            titlebar.setTitleText(title);
            errorMsg("", 0);
        }
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.img_set));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                if (messageSettingDialog == null) {
                    initMessageSettingDialog();
                }
                if (messageSettingDialog != null && !messageSettingDialog.isShowing()) {
                    messageSettingDialog.show();
                    messageSettingDialog.setConversationType(mConversationType, mTargetId);
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);
        setTypingStatusListener();
    }

    private void setTypingStatusListener() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case SET_TEXT_TYPING_TITLE:
                        setTitle(TextTypingTitle);
                        break;
                    case SET_VOICE_TYPING_TITLE:
                        setTitle(VoiceTypingTitle);
                        break;
                    case SET_TARGET_ID_TITLE:
                        if (StringUtils.isEmpty(title)) {
                            ((ConversationContract.Presenter) mPresenter).getUserInfo(mTargetId);
                        } else {
                            ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        RongIMClient.setTypingStatusListener(new RongIMClient.TypingStatusListener() {
            @Override
            public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {
                //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
                if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
                    //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
                    int count = typingStatusSet.size();
                    if (count > 0) {
                        Iterator iterator = typingStatusSet.iterator();
                        TypingStatus status = (TypingStatus) iterator.next();
                        String objectName = status.getTypingContentType();

                        MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                        MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
                        //匹配对方正在输入的是文本消息还是语音消息
                        if (objectName.equals(textTag.value())) {
                            //显示“对方正在输入”
                            mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                        } else if (objectName.equals(voiceTag.value())) {
                            //显示"对方正在讲话"
                            mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                        }
                    } else {
                        //当前会话没有用户正在输入，标题栏仍显示原来标题
                        mHandler.sendEmptyMessage(SET_TARGET_ID_TITLE);
                    }
                }
            }
        });

//        switch (mConversationType) {
//            case CUSTOMER_SERVICE:
//                ICustomServiceListener listener = new ICustomServiceListener() {
//                    @Override
//                    public void onSuccess(CustomServiceConfig customServiceConfig) {
//
//                    }
//
//                    @Override
//                    public void onError(int i, String s) {
//
//                    }
//
//                    @Override
//                    public void onModeChanged(CustomServiceMode customServiceMode) {
//                        RongIMClient.getInstance().switchToHumanMode(mTargetId);
//                        //                    RongIMClient.getInstance().selectCustomServiceGroup(BuildConfig.RONGYUN_KEFU, "b60d3ba1c2534c27af40a1f49fe929f9");
//                        // RongIMClient.getInstance().selectCustomServiceGroup(BuildConfig.RONGYUN_KEFU, "19c9a8685dff4257b6f4aaf3eeab9b82");
//                    }
//
//                    @Override
//                    public void onQuit(String s) {
//
//                    }
//
//                    @Override
//                    public void onPullEvaluation(String s) {
//
//                    }
//
//                    @Override
//                    public void onSelectGroup(List<CSGroupItem> list) {
//                        Log.d("CSGroupItem", JsonUtil.getInstance().obj2JsonString(list));
//                        //    RongIMClient.getInstance().selectCustomServiceGroup(BuildConfig.RONGYUN_KEFU, list.get(0).getId());
//                    }
//                };
//                RongIMClient.getInstance().startCustomService(mTargetId, listener, null);
//                break;
//        }

    }


    @Override
    public void setPresenter(ConversationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(success, RongCloudBean.class);
        if (RongIM.getInstance() != null && rongCloudBean.getData() != null && !StringUtils.isEmpty(rongCloudBean.getData().getFace())) {
            UserInfo userInfo;
            if (mTargetId.startsWith("S") && !StringUtils.isEmpty(rongCloudBean.getData().getStoreName())) {
                userInfo = new UserInfo(mTargetId + "", rongCloudBean.getData().getStoreName(), Uri.parse(rongCloudBean.getData().getStoreLog()));
            } else {
                userInfo = new UserInfo(mTargetId + "", rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
            }
            titlebar.setTitleText(userInfo.getName());
            //  ActivityTitleUtils.initToolbar(aty, userInfo.getName(), true, R.id.titlebar);
            RongIM.getInstance().refreshUserInfoCache(userInfo);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (messageSettingDialog != null) {
            messageSettingDialog.cancel();
        }
        messageSettingDialog = null;
    }
}
