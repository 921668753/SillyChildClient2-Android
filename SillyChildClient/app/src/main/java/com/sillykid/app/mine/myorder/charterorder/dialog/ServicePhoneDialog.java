package com.sillykid.app.mine.myorder.charterorder.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.sillykid.app.R;

/**
 * 拨打电话无权限提示
 * Created by Administrator on 2017/9/5.
 */
public class ServicePhoneDialog extends BaseDialog implements View.OnClickListener {

    private String phoneNum = "";

    TextView tv_phone;

    public ServicePhoneDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_servicephone);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView tv_servicePhone = (TextView) findViewById(R.id.tv_servicePhone);
        tv_servicePhone.setText(mContext.getString(R.string.contactWay));
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        TextView tv_call = (TextView) findViewById(R.id.tv_call);
        tv_call.setOnClickListener(this);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_call:
                dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                mContext.startActivity(intent);
                break;
        }
    }

    public void setPhone(String phoneNum) {
        this.phoneNum = phoneNum;
        tv_phone.setText(phoneNum);
    }
}
