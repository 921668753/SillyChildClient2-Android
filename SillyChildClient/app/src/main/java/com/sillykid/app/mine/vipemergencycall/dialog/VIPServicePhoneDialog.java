package com.sillykid.app.mine.vipemergencycall.dialog;

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

import com.sillykid.app.R;

/**
 * 拨打电话无权限提示
 * Created by Administrator on 2017/9/5.
 */
public class VIPServicePhoneDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private String phoneNum = "";

    TextView tv_phone;

    public VIPServicePhoneDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
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
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNum));
                context.startActivity(intent);
                break;
        }
    }

    public void setPhone(String phoneNum) {
        this.phoneNum = phoneNum;
        tv_phone.setText(phoneNum.substring(3));
    }
}
