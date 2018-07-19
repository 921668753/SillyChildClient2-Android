package com.sillykid.app.mine.myrelease.collectiondynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.MasonryAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.custominterfaces.FragmentJumpBetween;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.DynamicStateBean;
import com.sillykid.app.entity.DynamicStateBean.ResultBean.ListBean;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.homepage.dynamics.DynamicsDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myrelease.MyReleaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的发布  收藏的动态
 * Created by Administrator on 2017/9/2.
 */

public class CollectionDynamicFragment extends BaseFragment implements CollectionDynamicContract.View, MasonryAdapter.MasonryItemOnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private MyReleaseActivity aty;

    @BindView(id = R.id.pullLoadMoreRecyclerView, click = true)
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    private List<ListBean> listbean;
    private MasonryAdapter adapter;
    private int pagenum = NumericConstants.START_PAGE_NUMBER;
    private boolean isloadmore;
    private DynamicStateBean dsBean;
    private boolean isresume;
    private Intent jumpintent;
    private PublicPromptDialog publicPromptDialog;
    private int currentpostion = 0;//当前操作的位置
    private UserInfoBean userInfoBean;
    private int itemposition = 0;
    private FragmentJumpBetween fragmentJumpBetween;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyReleaseActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_strate, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CollectionDynamicPresenter(this);
        fragmentJumpBetween = new FragmentJumpBetween() {
            @Override
            public void fragmentPosition() {
                pullLoadMoreRecyclerView.refresh();
            }

            @Override
            public void doAttention() {

            }

            @Override
            public void doCancleAttention() {

            }
        };

        pullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        listbean = new ArrayList<>();
        adapter = new MasonryAdapter(aty, listbean, this, false);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        //设置item之间的间隔
      //  pullLoadMoreRecyclerView.addItemDecoration(((MyReleaseActivity) getActivity()).getSpacesItemDecoration());
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        refreshListBean(null);
    }

    @Override
    public void setPresenter(CollectionDynamicContract.Presenter presenter) {
        mPresenter = (CollectionDynamicPresenter) presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 1) {
            listbean.remove(currentpostion);
            adapter.notifyDataSetChanged();
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 2) {
//            userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (userInfoBean != null && userInfoBean.getData() != null) {
//                aty.initAmount(userInfoBean.getData().getFans_num(), userInfoBean.getData().getAttention_num(), userInfoBean.getData().getGood_num(), userInfoBean.getData().getCollection_num());
//            }
//            ((CollectionDynamicPresenter) mPresenter).getCollectionDynamicList(pagenum, NumericConstants.LOADCOUNT);
//        } else {
//            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//            dsBean = (DynamicStateBean) JsonUtil.json2Obj(success, DynamicStateBean.class);
//            if (dsBean == null) {
//                refreshListBean(null);
//                ViewInject.toast(getString(R.string.otherError));
//                return;
//            }
//            if (dsBean.getData() == null || dsBean.getData().getList() == null || dsBean.getData().getList().size() == 0) {
//                if (isloadmore) {
//                    ViewInject.toast(getString(R.string.noMoreData));
//                } else {
//                    refreshListBean(null);
//                }
//                return;
//            }
//            List<ListBean> datalist = dsBean.getData().getList();
//            refreshListBean(datalist);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        if (isresume) {
            isresume = false;
        } else {
            ViewInject.toast(msg);
        }
    }

    /**
     * 刷新数据
     */
    private void refreshListBean(List<ListBean> addlist) {
        if (isloadmore) {
            if (pagenum == 1) {
                listbean.clear();
                if (addlist != null && addlist.size() > 0) {
                    listbean.addAll(addlist);
                    if (addlist.size() < NumericConstants.LOADCOUNT) {
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            } else {
                int listbeansize = listbean.size();
                for (int i = ((pagenum - 1) * NumericConstants.LOADCOUNT); i < listbeansize; i++) {
                    listbean.remove(i);
                }
                if (addlist != null && addlist.size() > 0) {
                    listbean.addAll(addlist);
                    if (addlist.size() < NumericConstants.LOADCOUNT) {
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            }
        } else {
            listbean.clear();
            if (addlist != null && addlist.size() > 0) {
                listbean.addAll(addlist);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void masonryOnItemClick(View view, int postion) {
        itemposition = postion;
        if (jumpintent == null) jumpintent = new Intent(aty, DynamicsDetailsActivity.class);
        jumpintent.putExtra("act_id", listbean.get(postion).getId());
        startActivityForResult(jumpintent, 0);
    }

    @Override
    public void masonryOnLongItemClick(View view, int postion) {
        currentpostion = postion;
        //删除
        if (publicPromptDialog == null) {
            publicPromptDialog = new PublicPromptDialog(aty) {
                @Override
                public void doAction() {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    ((CollectionDynamicPresenter) mPresenter).doDelete(listbean.get(postion).getId(), 1);
                }
            };
        }
        publicPromptDialog.show();
        publicPromptDialog.setContent(getString(R.string.deleteCollectionStrategy));
        publicPromptDialog.setBtnContent(getString(R.string.confirm));
    }

    @Override
    public void onRefresh() {
        //刷新
        isloadmore = false;
        pagenum = NumericConstants.START_PAGE_NUMBER;
        ((CollectionDynamicPresenter) mPresenter).getInfo();
    }

    @Override
    public void onLoadMore() {
        //加载更多
        isloadmore = true;
        pagenum = (listbean.size() / NumericConstants.LOADCOUNT) + 1;
        ((CollectionDynamicPresenter) mPresenter).getCollectionDynamicList(pagenum, NumericConstants.LOADCOUNT);
    }

    @Override
    public void onResume() {
        super.onResume();
        isresume = true;
//        if (aty.getChageIcon() == 2) {
//            pullLoadMoreRecyclerView.refresh();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0 && data != null) {
            listbean.remove(currentpostion);
            adapter.notifyDataSetChanged();
        }
    }

    public FragmentJumpBetween getFragmentJumpBetween() {
        return fragmentJumpBetween;
    }
}
