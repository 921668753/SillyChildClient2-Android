package com.sillykid.app.homepage.message;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.RongIMUtil;

/**
 * 消息
 * Created by Admin on 2017/8/10.
 */

public class MessageActivity extends BaseActivity{

    @BindView(id = R.id.tv_interactiveMessage, click = true)
    private TextView tv_interactiveMessage;

    @BindView(id = R.id.tv_systemMessage, click = true)
    private TextView tv_systemMessage;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        aty = (MainActivity) getActivity();
//        return View.inflate(aty, R.layout.fragment_message, null);
//    }

    @Override
    public void setRootView() {
        setContentView(R.layout.fragment_message);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new InteractiveMessageFragment();
        contentFragment1 = new SystemMessageFragment();
        chageIcon = aty.getIntent().getIntExtra("chageMessageIcon", 20);
    }


    @Override
    public void initWidget() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.message), true, R.id.titlebar);
        if (chageIcon == 20) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(this);
        } else if (chageIcon == 21) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(this);
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_interactiveMessage:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                break;
            case R.id.tv_systemMessage:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
                break;
            default:
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
//    @Override
//    public void onChange() {
//        super.onChange();
//
//    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_interactiveMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_systemMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        if (chageIcon == 20) {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else if (chageIcon == 21) {
            tv_systemMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        }
    }
}