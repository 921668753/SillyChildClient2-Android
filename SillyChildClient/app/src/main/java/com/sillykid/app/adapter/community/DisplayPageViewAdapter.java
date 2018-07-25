package com.sillykid.app.adapter.community;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kymjs.common.DensityUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.community.OtherUserPostBean.DataBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 个人发布展示页面  适配器
 */
public class DisplayPageViewAdapter extends BGARecyclerViewAdapter<ListBean> {

    public DisplayPageViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mystrategy);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {
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

        if (StringUtils.isEmpty(model.getConcern_number())) {
            helper.setText(R.id.tv_attentionNum, "0");
        } else {
            helper.setText(R.id.tv_attentionNum, model.getConcern_number());
        }

    }

}
