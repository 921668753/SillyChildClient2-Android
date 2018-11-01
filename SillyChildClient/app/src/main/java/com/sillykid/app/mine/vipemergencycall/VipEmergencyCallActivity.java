package com.sillykid.app.mine.vipemergencycall;

import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.Manifest;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.mine.vipemergencycall.dialog.VIPServicePhoneDialog;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * VIP救助电话
 * Created by Administrator on 2017/9/2.
 */
public class VipEmergencyCallActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, WebViewLayout1.WebViewCallBack {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout1 webViewLayout;

    private VIPServicePhoneDialog vipServicePhoneDialog = null;

    private String tel = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_sharingceremony);
    }

    @Override
    public void initData() {
        super.initData();
        vipServicePhoneDialog = new VIPServicePhoneDialog(aty);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        webViewLayout.setTitleVisibility(false);
        webViewLayout.setWebViewCallBack(this);
        String url = URLConstants.VIPEMERGENCYCALL;
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vipEmergencyCall), true, R.id.titlebar);
    }

    @Override
    public void backOnclick(String id) {
        tel = id;
        choiceLocationWrapper();
    }

    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    private void choiceLocationWrapper() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (vipServicePhoneDialog == null) {
                vipServicePhoneDialog = new VIPServicePhoneDialog(aty);
            }
            if (vipServicePhoneDialog != null && !vipServicePhoneDialog.isShowing()) {
                vipServicePhoneDialog.show();
                vipServicePhoneDialog.setPhone(tel);
            }
            return;
        }
        EasyPermissions.requestPermissions(this, getString(R.string.callSwitch), NumericConstants.READ_AND_WRITE_CODE, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.callPermission));
        }
    }

    @Override
    public void loadFailedError() {

    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (vipServicePhoneDialog != null && vipServicePhoneDialog.isShowing()) {
                    vipServicePhoneDialog.dismiss();
                    return true;
                }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vipServicePhoneDialog != null && vipServicePhoneDialog.isShowing()) {
            vipServicePhoneDialog.cancel();
        }
        vipServicePhoneDialog = null;
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }


}
