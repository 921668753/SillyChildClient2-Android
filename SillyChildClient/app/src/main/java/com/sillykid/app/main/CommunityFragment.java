package com.sillykid.app.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.sillykid.app.R;
import com.sillykid.app.community.CommunityClassificationFragment;
import com.sillykid.app.community.search.CommunitySearchActivity;
import com.sillykid.app.entity.main.community.ClassificationListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myrelease.mydynamic.ReleaseDynamicActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import static com.sillykid.app.utils.DisplayUtil.dip2px;

/**
 * 社区
 */
public class CommunityFragment extends BaseFragment implements CommunityContract.View {

    private MainActivity aty;

    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    @BindView(id = R.id.img_search, click = true)
    private ImageView img_search;


    @BindView(id = R.id.tabLayout)
    private TabLayout tabLayout;

    /**
     * 标记选择位置/用来表示移动的Fragment
     **/
    private int itemSelected = 0;

    private List<CommunityClassificationFragment> list;
//
//    @BindView(id = R.id.tv_newTrends, click = true)
//    private TextView tv_newTrends;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private int classification_id = 0;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_community, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CommunityPresenter(this);
        list = new ArrayList<CommunityClassificationFragment>();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CommunityContract.Presenter) mPresenter).getClassificationList();
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_search:
            case R.id.img_search:
                aty.showActivity(aty, CommunitySearchActivity.class);
                break;
//            case R.id.tv_newTrends:
//                aty.showActivity(aty, ReleaseDynamicActivity.class);
//                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((CommunityContract.Presenter) mPresenter).getClassificationList();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }


    @Override
    public void setPresenter(CommunityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ll_commonError.setVisibility(View.GONE);
            //    tv_newTrends.setVisibility(View.VISIBLE);
            ClassificationListBean classificationListBean = (ClassificationListBean) JsonUtil.getInstance().json2Obj(success, ClassificationListBean.class);
            if (classificationListBean.getData() == null || classificationListBean.getData().size() <= 0) {
                dismissLoadingDialog();
                return;
            }
            /**动态添加值**/
            for (int i = 0; i < classificationListBean.getData().size(); i++) {
                CommunityClassificationFragment baseFragment = new CommunityClassificationFragment();
                tabLayout.addTab(tabLayout.newTab());
                list.add(baseFragment);
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab == null) {
                    continue;
                }
                tab.setText(classificationListBean.getData().get(i).getName());
                if (i == itemSelected) {
                    classification_id = classificationListBean.getData().get(itemSelected).getId();
                    changeFragment(list.get(itemSelected));
                    list.get(itemSelected).setClassificationId(classification_id, itemSelected);
                    /**
                     * 发送消息
                     */
                    //  RxBus.getInstance().post(new MsgEvent<String>("RxBusCommunityClassificationFragmentEvent"));
                }
            }
            reflex(tabLayout);
            /**计算滑动的偏移量**/
            final int width = (int) (getOffsetWidth(classificationListBean.getData(), itemSelected) * getResources().getDisplayMetrics().density);
            tabLayout.post(new Runnable() {
                @Override
                public void run() {
                    tabLayout.scrollTo(width, 0);
                }
            });
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    itemSelected = tab.getPosition();
                    classification_id = classificationListBean.getData().get(itemSelected).getId();
                    changeFragment(list.get(itemSelected));
                    list.get(itemSelected).setClassificationId(classification_id, itemSelected);
                    /**
                     * 发送消息
                     */
                    //   RxBus.getInstance().post(new MsgEvent<String>("RxBusCommunityClassificationFragmentEvent"));
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            dismissLoadingDialog();
        }
    }

    public void reflex(final TabLayout tabLayout) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = dip2px(tabLayout.getContext(), 11);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }


    /**
     * 根据字符个数计算偏移量
     */
    private int getOffsetWidth(List<ClassificationListBean.DataBean> list, int index) {
        String str = "";
        for (int i = 0; i < index; i++) {
            str += list.get(i).getName();
        }
        return str.length() * 14 + index * 12;
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //   tv_newTrends.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noMovement))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }

//    public void setTVnewTrendsGone() {
//        tv_newTrends.setVisibility(View.GONE);
//    }
//
//    public void setTVnewTrendsVisible() {
//        tv_newTrends.setVisibility(View.VISIBLE);
//    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        list.clear();
        list = null;
    }

}
