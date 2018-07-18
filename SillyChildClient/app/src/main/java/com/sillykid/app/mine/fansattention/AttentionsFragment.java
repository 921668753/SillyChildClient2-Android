package com.sillykid.app.mine.fansattention;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.FansInfoAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.custominterfaces.FragmentJumpBetween;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.FansAndAttentionBean;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/11/20.
 */

public class AttentionsFragment extends BaseFragment implements BGAOnItemChildClickListener,BGARefreshLayout.BGARefreshLayoutDelegate,FansAttentionContract.View,AdapterView.OnItemClickListener{
    private FansAttentionActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError, click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    private FansInfoAdapter fansInfoAdapter;
    private FragmentJumpBetween fragmentJumpBetween;
    private boolean isloadmore;
    private int pagenum=0;
    private FansAndAttentionBean fansAndAttentionBean;
    private int currentitem;
    private PublicPromptDialog publicPromptDialog;
    private int totalpages=1;
    private Intent intentjump;
    private String userid;
    private int mobileuserid;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (FansAttentionActivity) getActivity();
        userid=aty.getIntent().getStringExtra("userid");
        return View.inflate(aty, R.layout.fragment_fans, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new FansAttentionPresenter(this);
        fragmentJumpBetween=new FragmentJumpBetween() {
            @Override
            public void fragmentPosition() {
                mRefreshLayout.beginRefreshing();
            }
            @Override
            public void doAttention() {

            }

            @Override
            public void doCancleAttention() {

            }
        };
        fansInfoAdapter=new FansInfoAdapter(aty,true);
        fansInfoAdapter.setOnItemChildClickListener(this);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout,this,aty,true);
        lv_order.setAdapter(fansInfoAdapter);
        lv_order.setOnItemClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_commonError:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    aty.showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        currentitem=position;
        if (fansInfoAdapter.getData().get(currentitem).getIs_attention()==0){
            //去关注
            showLoadingDialog(getString(R.string.submissionLoad));
            ((FansAttentionPresenter)mPresenter).attentionOrNo(fansInfoAdapter.getData().get(currentitem).getUser_id()+"",0);
        }else{
            //取消关注
            showPrompting();
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        isloadmore=false;
        getDataList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isloadmore=true;
        mRefreshLayout.endLoadingMore();
        if (pagenum<=totalpages){
            showLoadingDialog(getString(R.string.dataLoad));
            getDataList();
        }else {
            ViewInject.toast(getString(R.string.noMoreData));
        }
        return true;
    }

    @Override
    public void setPresenter(FansAttentionContract.Presenter presenter) {
        mPresenter=(FansAttentionPresenter)presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag){
            case 0:
                fansAndAttentionBean=(FansAndAttentionBean) JsonUtil.json2Obj(success, FansAndAttentionBean.class);
                if (fansAndAttentionBean==null){
                    dismissLoadingDialog();
                    ViewInject.toast(getString(R.string.otherError));
                    return;
                }
                if (fansAndAttentionBean.getData()==null||fansAndAttentionBean.getData().getList()==null||fansAndAttentionBean.getData().getList().size()==0){
                    if (isloadmore){
                        dismissLoadingDialog();
                        ViewInject.toast(getString(R.string.noMoreData));
                    }else{
                        dismissLoadingDialog();
                    }
                    return;
                }
                if (pagenum==fansAndAttentionBean.getData().getP()){
                    if (pagenum==1){
                        fansInfoAdapter.clear();
                        fansInfoAdapter.addNewData(fansAndAttentionBean.getData().getList());
                    }else if (pagenum>1){
                        for (int i=(pagenum-1)*NumericConstants.LOADCOUNT;i<fansInfoAdapter.getData().size();i++){
                            fansInfoAdapter.getData().remove(i);
                        }
                        fansInfoAdapter.addMoreData(fansAndAttentionBean.getData().getList());
                    }
                    dismissLoadingDialog();
                }else{
                    pagenum=fansAndAttentionBean.getData().getP();
                    totalpages=fansAndAttentionBean.getData().getTotalPages();
                    if (isloadmore){
                        fansInfoAdapter.addMoreData(fansAndAttentionBean.getData().getList());
                    }else{
                        fansInfoAdapter.clear();
                        fansInfoAdapter.addNewData(fansAndAttentionBean.getData().getList());
                    }
                    dismissLoadingDialog();
                }
                break;
            case 1://关注
                fansInfoAdapter.getData().get(currentitem).setIs_attention(1);
                fansInfoAdapter.notifyDataSetChanged();
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.attentionSuccess));
                break;
            case 2://取消关注
                showLoadingDialog(getString(R.string.dataLoad));
                ViewInject.toast(getString(R.string.focusSuccess));
                getDataList();
                break;
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag==0){
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                dismissLoadingDialog();
                tv_hintText.setText(getString(R.string.login1));
                //       aty.showActivity(aty, LoginActivity.class);
                return;
            }
            tv_hintText.setText(msg);
            dismissLoadingDialog();
        }else{
            dismissLoadingDialog();
            ViewInject.toast(msg);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        KJActivityStack.create().finishActivity(FansInfoActivity.class);
        if (intentjump==null){
            intentjump=new Intent(aty,FansInfoActivity.class);
        }
        intentjump.putExtra("fansuserid",fansInfoAdapter.getData().get(i).getUser_id()+"");
        aty.showActivity(aty,intentjump);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 1) {
            mRefreshLayout.beginRefreshing();
        }
    }

    private void showPrompting(){
        if (publicPromptDialog ==null){
            publicPromptDialog =new PublicPromptDialog(aty) {
                @Override
                public void doAction() {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    ((FansAttentionPresenter)mPresenter).attentionOrNo(fansInfoAdapter.getData().get(currentitem).getUser_id()+"",1);
                }
            };
        }
        publicPromptDialog.show();
        publicPromptDialog.setContent(getString(R.string.cancelAttention));
        publicPromptDialog.setBtnContent(getString(R.string.confirm));
        publicPromptDialog.setTitle(getResources().getColor(R.color.phonenumbercolor),18);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (publicPromptDialog!=null){
            publicPromptDialog.dismiss();
            publicPromptDialog=null;
        }
    }

    public FragmentJumpBetween getFragmentJumpBetween() {
        return fragmentJumpBetween;
    }

    private void getDataList(){
        if (StringUtils.isEmpty(userid)){
            ((FansAttentionPresenter)mPresenter).getAttentionList(pagenum,NumericConstants.LOADCOUNT,PreferenceHelper.readInt(aty, StringConstants.FILENAME, "userId", 0)+"");
        }else{
            ((FansAttentionPresenter)mPresenter).getAttentionList(pagenum,NumericConstants.LOADCOUNT,userid);
        }
    }
}
