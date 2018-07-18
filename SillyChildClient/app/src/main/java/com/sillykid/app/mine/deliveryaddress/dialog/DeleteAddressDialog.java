package com.sillykid.app.mine.deliveryaddress.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sillykid.app.R;

public abstract class DeleteAddressDialog extends Dialog implements View.OnClickListener {

    private int addressId = 0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clearcache);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(context.getString(R.string.deleteAddress));
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    public DeleteAddressDialog(Context context, int addressId) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.addressId = addressId;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                dismiss();
                deleteAddressDo(addressId);
                break;
        }
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public abstract void deleteAddressDo(int addressId);

}
