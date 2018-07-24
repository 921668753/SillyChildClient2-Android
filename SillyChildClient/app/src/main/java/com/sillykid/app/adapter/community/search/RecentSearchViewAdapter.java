package com.sillykid.app.adapter.community.search;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.community.search.RecentSearchBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 最近搜索
 */
public class RecentSearchViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public RecentSearchViewAdapter(Context context) {
        super(context, R.layout.item_recentsearchcommunity);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        helper.setText(R.id.tv_name, model.getName());

    }
}

