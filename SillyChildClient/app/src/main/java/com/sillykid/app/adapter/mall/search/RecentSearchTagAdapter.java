package com.sillykid.app.adapter.mall.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sillykid.app.R;
import com.sillykid.app.entity.mall.search.RecentSearchBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * 最近搜索
 */
public class RecentSearchTagAdapter extends TagNewAdapter<RecentSearchBean.DataBean> {

    private final LayoutInflater mInflater;
    private final Context context;

    public RecentSearchTagAdapter(Context context, List<RecentSearchBean.DataBean> datas) {
        super(datas);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, RecentSearchBean.DataBean s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_recentsearch, parent, false);
        tv.setText(s.getName());
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(TagFlowLayout.dip2px(context, 5),
                TagFlowLayout.dip2px(context, 15),
                TagFlowLayout.dip2px(context, 5),
                TagFlowLayout.dip2px(context, 0));
        tv.setLayoutParams(lp);
        return tv;
    }
}
