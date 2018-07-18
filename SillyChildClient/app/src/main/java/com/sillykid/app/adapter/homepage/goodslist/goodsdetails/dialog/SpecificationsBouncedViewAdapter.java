package com.sillykid.app.adapter.homepage.goodslist.goodsdetails.dialog;


import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品详情--------规格弹框
 */
public class SpecificationsBouncedViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<SpecificationsCvBouncedViewAdapter> specificationsCvAdapterCounters;
    private OnStatusListener onStatusListener;

    public SpecificationsBouncedViewAdapter(Context context) {
        super(context, R.layout.dialog_item_specificationsbounced);
        this.specificationsCvAdapterCounters = new SparseArray<>();
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {
        helper.setText(R.id.tv_specificationsName, model.getSpec_name());
        NoScrollGridView gv_specifications = (NoScrollGridView) helper.getView(R.id.gv_specifications);
        SpecificationsCvBouncedViewAdapter adapter;
        if (specificationsCvAdapterCounters.get(gv_specifications.hashCode()) != null) {
            adapter = specificationsCvAdapterCounters.get(gv_specifications.hashCode());
            adapter.clear();
            adapter.addNewData(model.getSpecValueIds());
        } else {
            adapter = new SpecificationsCvBouncedViewAdapter(mContext);
            gv_specifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position1, long l) {
                    onStatusListener.onSetStatusListener(view, adapter, position, position1);
                }
            });
            gv_specifications.setAdapter(adapter);
            adapter.clear();
            adapter.addNewData(model.getSpecValueIds());
            specificationsCvAdapterCounters.put(gv_specifications.hashCode(), adapter);
        }
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetStatusListener(View view, SpecificationsCvBouncedViewAdapter adapter, int position, int position1);
    }

    @Override
    public void clear() {
        if (specificationsCvAdapterCounters != null) {
            Log.e("TAG", "size :  " + specificationsCvAdapterCounters.size());
            for (int i = 0, length = specificationsCvAdapterCounters.size(); i < length; i++) {
                SpecificationsCvBouncedViewAdapter cdt = specificationsCvAdapterCounters.get(specificationsCvAdapterCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        super.clear();
    }
}
