package com.sillykid.app.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;

import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 司导消息
 * Created by Admin on 2017/8/10.
 */

public class CompanyGuideMessageFragment extends BaseFragment implements CompanyGuideMessageContract.View {

//    @BindView(id = R.id.rl_listview)
//    private RelativeLayout rl_listview;
//
//
//    @BindView(id = R.id.conversation_listview)
//    EaseConversationList conversationListview;

    private MainActivity aty;

    private Activity context;
//    /**
//     * 错误提示页
//     */
//    @BindView(id = R.id.ll_commonError)
//    private LinearLayout ll_commonError;
//
//    @BindView(id = R.id.img_err)
//    private ImageView img_err;
//
//    @BindView(id = R.id.tv_hintText, click = true)
//    private TextView tv_hintText;

//    private final List<EMConversation> conversationList = new ArrayList<>();

   // private List<HxUserListBean.ResultBean> hxUserList = null;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_im, null);
    }

    @Override
    public void setPresenter(CompanyGuideMessageContract.Presenter presenter) {

    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    //    @Override
//    protected void initData() {
//        super.initData();
//        mPresenter = new CompanyGuideMessagePresenter(this);
//    }
//
//    private final static int MSG_REFRESH = 2;
//
//    protected EMConversationListener convListener = new EMConversationListener() {
//
//        @Override
//        public void onCoversationUpdate() {
//            refresh();
//        }
//    };
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.context = activity;
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        mInit();
//        mListener();
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    private void mInit() {
////        conversationList.clear();
////        List<EMConversation> loadConversationList = loadConversationList();
////        ll_commonError.setVisibility(View.GONE);
////        rl_listview.setVisibility(View.VISIBLE);
////        conversationList.addAll(loadConversationList);
////        conversationListview.init(conversationList);
////        if (conversationList == null || conversationList.size() == 0) {
////            errorMsg(getString(R.string.noSession), 0);
////        }
////        if (listItemClickListener != null) {
////            conversationListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    EMConversation conversation = conversationListview.getItem(position);
////                    listItemClickListener.onListItemClicked(conversation);
////                }
////            });
////        }
////        EMClient.getInstance().addConnectionListener(connectionListener);
//    }
//
//
//    private void mListener() {
////        conversationListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                //  if (position < 1) return;
////                EMConversation conversation = conversationListview.getItem(position);
////                String userId = conversation.conversationId();
////                String userName = "";
////                String avatar = "";
////                if (hxUserList == null || hxUserList.size() == 0) {
////                    return;
////                }
////                for (int i = 0; i < hxUserList.size(); i++) {
////                    if (hxUserList.get(i).getHxName().equals(conversation.conversationId())) {
////                        userName = hxUserList.get(i).getNickname();
////                        avatar = hxUserList.get(i).getAvatar();
////                    }
////                }
////                if (userId.equals(EMClient.getInstance().getCurrentUser()))
////                    Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
////                else {
////                    // start chat acitivity
//////                    Intent intent = new Intent(getActivity(), ChatMessageActivity.class);
//////                    if (conversation.isGroup()) {
//////                        if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
//////                            // it's group chat
//////                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
//////                        } else {
//////                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_GROUP);
//////                        }
//////                    }
//////                    intent.putExtra("avatar", avatar);
//////                    intent.putExtra("nickname", userName);
//////                    intent.putExtra(EaseConstant.EXTRA_USER_ID, userId);
//////                    startActivity(intent);
////                }
////            }
////        });
//
//    }
//
////    protected EMConnectionListener connectionListener = new EMConnectionListener() {
////
////        @Override
////        public void onDisconnected(int error) {
////            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE || error == EMError.SERVER_SERVICE_RESTRICTED
////                    || error == EMError.USER_KICKED_BY_CHANGE_PASSWORD || error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
////                //     isConflict = true;
////            } else {
////                handler.sendEmptyMessage(0);
////            }
////        }
////
////        @Override
////        public void onConnected() {
////            handler.sendEmptyMessage(1);
////        }
////    };
//
//  //  private EaseConversationListFragment.EaseConversationListItemClickListener listItemClickListener;
//
//
//    public Handler handler = new Handler() {
//
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    onConnectionDisconnected();
//                    break;
//                case 1:
//                    onConnectionConnected();
//                    break;
//                case MSG_REFRESH: {
//                    conversationList.clear();
////                    List<EMConversation> loadConversationList = loadConversationList();
//////                    if (loadConversationList == null || loadConversationList.size() == 0) {
//////                        errorMsg(getString(R.string.noSession), 0);
//////                        return;
//////                    }
////                    conversationList.addAll(loadConversationList);
////                    conversationListview.refresh();
//                    if (conversationList == null || conversationList.size() == 0) {
//                        errorMsg(getString(R.string.noSession), 0);
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };
//
//    /**
//     * connected to server
//     */
//    protected void onConnectionConnected() {
//        /**
//         * 设置头像
//         */
//        //get easeui instance
////        EaseUI easeUI = EaseUI.getInstance();
////        //需要easeui库显示用户头像和昵称设置此provider
////        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
////
////            @Override
////            public EaseUser getUser(String username) {
////                return getUserInfo(username);
////            }
////        });
//    }
//
//    @Override
//    protected void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.tv_hintText:
//                if (tv_hintText.getText().toString().contains(getString(R.string.login1))) {
//                    Intent intent = new Intent(aty, LoginActivity.class);
//                    // intent.putExtra("name", "GetOrderFragment");
//                    aty.showActivity(aty, intent);
//                    return;
//                }
//                refresh();
//                break;
//        }
//    }
//
//    /**
//     * disconnected with server
//     */
//    protected void onConnectionDisconnected() {
////        ll_commonError.setVisibility(View.VISIBLE);
////        tv_hintText.setText(getString(R.string.noSession));
////        rl_listview.setVisibility(View.GONE);
//    }
//
//
//    /**
//     * refresh ui
//     */
//    public void refresh() {
//        int isRefreshCompanyGuideMessageFragment = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 20);
//        if (isRefreshCompanyGuideMessageFragment == 21) {
//            if (!handler.hasMessages(MSG_REFRESH)) {
//                handler.sendEmptyMessage(MSG_REFRESH);
//            }
//        }
//    }
//
//
//    /**
//     * load conversation list
//     *
//     * @return +
//     */
////    protected List<EMConversation> loadConversationList() {
////        // get all conversations
////        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
////        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
////        /**
////         * lastMsgTime will change if there is new message during sorting
////         * so use synchronized to make sure timestamp of last message won't change.
////         */
////        synchronized (conversations) {
////            ll_commonError.setVisibility(View.GONE);
////            rl_listview.setVisibility(View.VISIBLE);
////            for (EMConversation conversation : conversations.values()) {
////                if (conversation.getAllMessages().size() != 0) {
////                    EMMessage emMessage = conversation.getLastMessage();
////                    try {
////                        for (int i = 0; i < hxUserList.size(); i++) {
////                            if (hxUserList.get(i).getHxName().equals(conversation.conversationId())) {
////                                Log.d("tag1111", conversation.conversationId());
////                                sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
////                            }
////                        }
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        }
////        try {
////            sortConversationByLastChatTime(sortList);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        List<EMConversation> list = new ArrayList<EMConversation>();
////        for (Pair<Long, EMConversation> sortItem : sortList) {
////            list.add(sortItem.second);
////        }
////        return list;
////    }
//
//    /**
//     * sort conversations according time stamp of last message
//     *
//     * @param conversationList
//     */
//    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
//        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
//            @Override
//            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {
//
//                if (con1.first.equals(con2.first)) {
//                    return 0;
//                } else if (con2.first.longValue() > con1.first.longValue()) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });
//    }
//
//    public void mChatManagerListener() {
//        EMClient.getInstance().chatManager().addMessageListener(msgListener);
//    }
//
//    EMMessageListener msgListener = new EMMessageListener() {
//
//        @Override
//        public void onMessageReceived(List<EMMessage> messages) {
//            Log.d("tag1111", JsonUtil.obj2JsonString(messages.get(messages.size() - 1)).getBytes().toString());
//            //收到消息
//            refresh();
//        }
//
//        @Override
//        public void onCmdMessageReceived(List<EMMessage> messages) {
//            //收到透传消息
////            refresh();
//        }
//
//        @Override
//        public void onMessageRead(List<EMMessage> messages) {
//            //收到已读回执
//        }
//
//        @Override
//        public void onMessageDelivered(List<EMMessage> message) {
//            //收到已送达回执
//        }
//
//        @Override
//        public void onMessageRecalled(List<EMMessage> messages) {
//            //消息被撤回
//            refresh();
//        }
//
//        @Override
//        public void onMessageChanged(EMMessage message, Object change) {
//            //消息状态变动
//        }
//    };
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mChatManagerListener();
//        int isRefreshCompanyGuideMessageFragment = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 20);
//        if (isRefreshCompanyGuideMessageFragment == 21) {
//            showLoadingDialog(getString(R.string.dataLoad));
//            ((CompanyGuideMessageContract.Presenter) mPresenter).getCompanyGuideMessage();
//        }
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            showLoadingDialog(getString(R.string.dataLoad));
//            ((CompanyGuideMessageContract.Presenter) mPresenter).getCompanyGuideMessage();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EMClient.getInstance().removeConnectionListener(connectionListener);
//    }
//
//
//    //即可正常显示头像昵称
//    private EaseUser getUserInfo(String username) {
//        EaseUser easeUser = new EaseUser(username);
//        //  if ("admin".equals(username)) return easeUser;
//        EaseConnectionsBean bean = EaseConnectionsHelper.getEaseConnectionsBean(username);
//        if (bean != null && username.equals(bean.getUsername())) {
//            easeUser.setNickname(bean.getNickname());
//            easeUser.setAvatar(bean.getHead_pic());
//        }
//        return easeUser;
//    }
//
//    @Override
//    public void setPresenter(CompanyGuideMessageContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void getSuccess(String success, int flag) {
//        if (flag == 0) {
//            HxUserListBean hxUserListBean = (HxUserListBean) JsonUtil.getInstance().json2Obj(success, HxUserListBean.class);
//            if (hxUserListBean == null || hxUserListBean.getResult() == null || hxUserListBean.getResult().size() == 0) {
//                errorMsg(getString(R.string.noSession), 0);
//                return;
//            }
//            hxUserList = hxUserListBean.getResult();
//            refresh();
//        } else if (flag == 1) {
//
//        }
//        dismissLoadingDialog();
//    }
//
    @Override
    public void errorMsg(String msg, int flag) {
//        dismissLoadingDialog();
//        if (isLogin(msg)) {
//            ll_commonError.setVisibility(View.VISIBLE);
//            tv_hintText.setText(getString(R.string.login1));
//            rl_listview.setVisibility(View.GONE);
////            Intent intent = new Intent(aty, LoginActivity.class);
////            // intent.putExtra("name", "GetOrderFragment");
////            aty.showActivity(aty, intent);
//            return;
//        }
//        if (msg.equals(getString(R.string.checkNetwork))) {
//            img_err.setImageResource(R.mipmap.nonetworkxxx);
//        } else {
//            img_err.setImageResource(R.mipmap.nocontentxxx);
//        }
//        ll_commonError.setVisibility(View.VISIBLE);
//        tv_hintText.setText(msg);
//        rl_listview.setVisibility(View.GONE);
    }

}
