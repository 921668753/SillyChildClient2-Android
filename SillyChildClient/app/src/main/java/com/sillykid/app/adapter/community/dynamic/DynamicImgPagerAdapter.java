package com.sillykid.app.adapter.community.dynamic;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.community.dynamic.DynamicDetailsImgBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterUtil;

public class DynamicImgPagerAdapter extends PagerAdapter {

    private List<DynamicDetailsImgBean> list = null;

    protected Context mContext;

    public DynamicImgPagerAdapter(Context context) {
        this.mContext = context;
        list = new ArrayList<DynamicDetailsImgBean>();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.viewpager_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        DynamicDetailsImgBean dynamicDetailsImgBean = list.get(position);
        if (dynamicDetailsImgBean.getCalculateHeight() == 0) {
            dynamicDetailsImgBean.setCalculateHeight(dynamicDetailsImgBean.getCalculateWidth());
        }
        GlideImageLoader.glideLoaderRaudio(mContext, dynamicDetailsImgBean.getUrl() + "?imageView2/0/w/" + dynamicDetailsImgBean.getCalculateWidth() + "/h/" + dynamicDetailsImgBean.getCalculateHeight(), imageView, 1, (int) dynamicDetailsImgBean.getCalculateWidth(), (int) dynamicDetailsImgBean.getCalculateHeight(), R.mipmap.placeholderfigure);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<DynamicDetailsImgBean> getData() {
        return list;
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据表）
     *
     * @param data
     */
    public void setData(List<DynamicDetailsImgBean> data) {
        if (BGAAdapterUtil.isListNotEmpty(data)) {
            list = data;
        } else {
            list.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据列表
     */
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}
