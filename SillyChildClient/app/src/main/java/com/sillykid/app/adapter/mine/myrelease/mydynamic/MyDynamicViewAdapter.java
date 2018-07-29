package com.sillykid.app.adapter.mine.myrelease.mydynamic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kymjs.common.DensityUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myrelease.mydynamic.MyDynamicBean.DataBean.ResultBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的发布----我的动态  适配器
 * Created by Administrator on 2017/9/6.
 */

public class MyDynamicViewAdapter extends BGARecyclerViewAdapter<ResultBean> {

    public MyDynamicViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mystrategy);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {
        ImageView imageView = helper.getImageView(R.id.img_strategy);
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
        GlideImageLoader.glideLoaderRaudio(mContext, model.getPicture() + "?imageView2/0/w/" + lp.width + "/h/" + lp.height, imageView, 4, (int) lp.width, (int) lp.height, R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_strategy, model.getPost_title());

        GlideImageLoader.glideLoader(mContext, model.getFace(), helper.getImageView(R.id.img_head), 0, R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_nickName, model.getNickname());

        helper.setVisibility(R.id.ll_attention, View.GONE);

//        if (StringUtils.isEmpty(model.getConcern_number())) {
//            helper.setText(R.id.tv_attentionNum, "0");
//        } else {
//            helper.setText(R.id.tv_attentionNum, model.getConcern_number());
//        }

    }

}
