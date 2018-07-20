package com.sillykid.app.adapter.mine.myrelease;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.DensityUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mall.goodslist.GoodsListBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的发布----攻略  适配器
 * Created by Administrator on 2017/9/6.
 */

public class MyStrategyViewAdapter extends BGARecyclerViewAdapter<DataBean> {

    public MyStrategyViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mallhomepage);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {
        ImageView imageView = (ImageView) helper.getView(R.id.img_good);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        float width1 = (DensityUtils.getScreenW() - 6 * 3 - 10 * 2) / 2;
        lp.width = (int) width1;
        float scale = 0;
        float tempHeight = 0;
        if (model.getWidth() <= 0 || model.getHeight() <= 0) {
            tempHeight = width1;
        } else {
            scale = (width1 + 0f) / model.getWidth();
            tempHeight = model.getHeight() * scale;
        }
        lp.height = (int) tempHeight;
        imageView.setLayoutParams(lp);
        GlideImageLoader.glideLoaderRaudio(mContext, model.getThumbnail() + "?imageView2/0/w/" + lp.width + "/h/" + lp.height, imageView, 4, (int) lp.width, (int) lp.height, R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_goodName, model.getName());
        if (StringUtils.isEmpty(model.getBrief())) {
            helper.setVisibility(R.id.tv_goodSynopsis, View.GONE);
        } else {
            helper.setText(R.id.tv_goodSynopsis, model.getBrief());
        }
        helper.setText(R.id.tv_goodMoney, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
        helper.setText(R.id.tv_brand, model.getBrand_name());
        if (StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        } else if (!StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.GONE);
        } else if (StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.GONE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else if (!StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        }
    }

}
