package com.sillykid.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.sillykid.app.R;
import com.sillykid.app.entity.CharterListBean.ResultBean.ListBean;
import com.sillykid.app.homepage.chartercustom.chartercommon.CharterDetailsActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Admin on 2017/9/29.
 */

public class CharterListViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public CharterListViewAdapter(Context context) {
        super(context, R.layout.item_charterlist);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {

        helper.setText(R.id.tv_title, model.getTitle());
        helper.setText(R.id.tv_price, model.getPriceFmt());

        if (model.getType() == 1 | model.getType() == 3) {
            helper.setText(R.id.tv_unit, mContext.getString(R.string.yuan2));
        } else {
            helper.setText(R.id.tv_unit, mContext.getString(R.string.yuan1));
        }
        NoScrollGridView gv_charterList = (NoScrollGridView) helper.getView(R.id.gv_charterList);
        CharterListGridViewAdapter charterListGridViewAdapter = new CharterListGridViewAdapter(mContext);
        gv_charterList.setAdapter(charterListGridViewAdapter);
        charterListGridViewAdapter.addNewData(model.getImgs());
        gv_charterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("type", model.getType());
                mContext.startActivity(intent);
            }
        });
        gv_charterList.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("type", model.getType());
                mContext.startActivity(intent);
                return false;
            }
        });
    }
}
