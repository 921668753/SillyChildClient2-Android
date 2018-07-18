package com.sillykid.app.mine.myrelease.strate;

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
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.MasonryAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.DynamicStateBean;
import com.sillykid.app.entity.DynamicStateBean.ResultBean.ListBean;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myrelease.MyReleaseActivity;
import com.sillykid.app.trip.strategy.StrategyDetailsActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**我的发布  攻略
 * Created by Administrator on 2017/9/2.
 */

public class StrateFragment extends BaseFragment implements StrateContract.View,MasonryAdapter.MasonryItemOnClickListener,PullLoadMoreRecyclerView.PullLoadMoreListener{
    private MyReleaseActivity aty;

    @BindView(id = R.id.pullLoadMoreRecyclerView)
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    private List<ListBean> listbean;
    private MasonryAdapter adapter;
    private int pagenum= NumericConstants.START_PAGE_NUMBER;
    private boolean isloadmore;
    private DynamicStateBean dsBean;
    private Intent jumpintent;
    private boolean isresume;
    private int currentpostion=0;
    private PublicPromptDialog publicPromptDialog;
    private UserInfoBean userInfoBean;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyReleaseActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_strate, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new StratePresenter(this);
        pullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        listbean=new ArrayList<>();
        adapter=new MasonryAdapter(aty,listbean,this,true);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        //设置item之间的间隔
        pullLoadMoreRecyclerView.addItemDecoration(aty.getSpacesItemDecoration());
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setFooterViewText(getString(R.string.load_more_text));
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.titletextcolors);
        pullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.background);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        refreshListBean(null);
    }

    @Override
    public void setPresenter(StrateContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag==1&&currentpostion!=0){
            listbean.remove(currentpostion);
            adapter.notifyDataSetChanged();
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.deleteFinish));
        }else if (flag==2){
//            userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (userInfoBean!=null&&userInfoBean.getData()!=null){
//                aty.initAmount(userInfoBean.getData().getFans_num(),userInfoBean.getData().getAttention_num(),userInfoBean.getData().getGood_num(),userInfoBean.getData().getCollection_num());
//            }
//            ((StratePresenter)mPresenter).getStrateList(pagenum,NumericConstants.LOADCOUNT);
//        }else {
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
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            aty.showActivity(aty,LoginActivity.class);
            return;
        }
        if (isresume){
            isresume=false;
        }else{
            ViewInject.toast(msg);
        }

    }

    /**
     * 刷新数据
     */
    private void refreshListBean(List<ListBean> addlist){
        if (isloadmore){
            if (pagenum==1){
                listbean.clear();
                ListBean initbean=new ListBean();
                initbean.setTitle(getString(R.string.newStrategy));
                listbean.add(initbean);
                if (addlist!=null&&addlist.size()>0){
                    listbean.addAll(addlist);
                    if (addlist.size()<NumericConstants.LOADCOUNT){
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            }else{
                int listbeansize = listbean.size();
                for (int i=((pagenum-1)*NumericConstants.LOADCOUNT);i<listbeansize;i++){
                    listbean.remove(i);
                }
                if (addlist!=null&&addlist.size()>0){
                    listbean.addAll(addlist);
                    if (addlist.size()<NumericConstants.LOADCOUNT){
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            }
        }else{
            listbean.clear();
            ListBean initbean=new ListBean();
            initbean.setTitle(getString(R.string.newStrategy));
            listbean.add(initbean);
            if (addlist!=null&&addlist.size()>0){
                listbean.addAll(addlist);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void masonryOnItemClick(View view, int postion) {
        if (postion==0){
            aty.showActivity(aty,ReleaseStrateActivity.class);
        }else{
            if (jumpintent==null) jumpintent=new Intent(aty, StrategyDetailsActivity.class);
            jumpintent.putExtra("title",listbean.get(postion).getTitle());
            jumpintent.putExtra("guide_id", StringUtils.toInt(listbean.get(postion).getId(),0));
            aty.showActivity(aty,jumpintent);
        }
    }

    @Override
    public void masonryOnLongItemClick(View view, int postion) {
        currentpostion=postion;
        //删除
        if (postion!=0){
            if (publicPromptDialog ==null){
                publicPromptDialog =new PublicPromptDialog(aty) {
                    @Override
                    public void doAction() {
                        showLoadingDialog(getString(R.string.submissionLoad));
                        ((StrateContract.Presenter)mPresenter).doDelete(listbean.get(postion).getId(),1);
                    }
                };
            }
            publicPromptDialog.show();
            publicPromptDialog.setContent(getString(R.string.deleteStrategy));
            publicPromptDialog.setBtnContent(getString(R.string.delete));
        }
    }

    @Override
    public void onRefresh() {
        //刷新
        isloadmore=false;
        pagenum=NumericConstants.START_PAGE_NUMBER;
        ((StratePresenter)mPresenter).getInfo();
    }

    @Override
    public void onLoadMore() {
        //加载更多
        isloadmore=true;
        pagenum=(listbean.size()/NumericConstants.LOADCOUNT)+1;
        ((StratePresenter)mPresenter).getStrateList(pagenum,NumericConstants.LOADCOUNT);
    }

    @Override
    public void onResume() {
        super.onResume();
        isresume=true;
        if (aty.getChageIcon()==1){
            pullLoadMoreRecyclerView.refresh();
        }

    }
}
