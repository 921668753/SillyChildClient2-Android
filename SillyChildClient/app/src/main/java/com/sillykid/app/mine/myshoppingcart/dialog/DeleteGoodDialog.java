package com.sillykid.app.mine.myshoppingcart.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sillykid.app.R;

/**
 * 删除商品提示
 * Created by Administrator on 2017/9/5.
 */
public class DeleteGoodDialog extends Dialog implements View.OnClickListener {

    private String content;
    private DialogCallBack callBack;

    public DeleteGoodDialog(Context context, String content) {
        super(context, R.style.MyDialog);
        this.content = content;
    }

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
        tv_content.setText(content);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                //   dismiss();
                callBack.confirm();
                break;
        }
    }

    public void setDialogCallBack(DialogCallBack callBack) {
        this.callBack = callBack;
    }


    public interface DialogCallBack {
        void confirm();
    }

}
