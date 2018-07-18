package com.sillykid.app.mine.vipemergencycall;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.dialog.VIPPermissionsDialog;
import com.sillykid.app.entity.VipPhoneBean;
import com.sillykid.app.loginregister.LoginActivity;


/**
 * VIP紧急电话
 * Created by Administrator on 2017/9/2.
 */

public class VipEmergencyCallActivity extends BaseActivity implements VipEmergencyCallContract.View{

    @BindView( id = R.id.ll_call , click = true)
    private LinearLayout ll_call;

    @BindView( id = R.id.ll_content )
    private LinearLayout ll_content;
    @BindView( id = R.id.tv_content)
    private TextView tv_content;

    private Intent jumpintent;
    private Uri data;
    private VipPhoneBean vipPhoneBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vipemergencycall);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new VipEmergencyCallPresenter(this);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        ((VipEmergencyCallPresenter)mPresenter).getVIPServicePhone();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vipEmergencyCall),true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (vipPhoneBean!=null&&vipPhoneBean.getData()!=null&&!TextUtils.isEmpty(vipPhoneBean.getData().getTelephone())){
            jumpintent = new Intent(Intent.ACTION_DIAL);
            data = Uri.parse("tel:" + vipPhoneBean.getData().getTelephone());
            jumpintent.setData(data);
            showActivity(aty, jumpintent);
        }else{
            initDialog(getString(R.string.noHavePhone));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(VipEmergencyCallContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        Log.d("调试","结果："+success);
        vipPhoneBean = (VipPhoneBean) JsonUtil.getInstance().json2Obj(success, VipPhoneBean.class);
        if (vipPhoneBean!=null&&vipPhoneBean.getData()!=null){
            if (TextUtils.isEmpty(vipPhoneBean.getData().getContent())){
                ll_content.setVisibility(View.GONE);
            }else{
                ll_content.setVisibility(View.VISIBLE);
                tv_content.setText(vipPhoneBean.getData().getContent());
            }
            dismissLoadingDialog();
        }else{
            initDialog(getString(R.string.noHavePhone));
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(this, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        initDialog(msg+getString(R.string.getPhoneError));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog(getString(R.string.dataLoad));
        ((VipEmergencyCallPresenter)mPresenter).getVIPServicePhone();
    }

    private void initDialog(String msg){
        VIPPermissionsDialog vipPermissionsDialog= new VIPPermissionsDialog(aty) {
            @Override
            public void doAction() {
                finish();
            }
        };
        vipPermissionsDialog.show();
        vipPermissionsDialog.setContent(msg);
    }

}
