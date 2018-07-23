package com.sillykid.app.adapter.main.homepage;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.common.cklibrary.utils.myview.HorizontalListView;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.ResultBean.ActionBean.LocalBean;
import com.sillykid.app.utils.DisplayUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 热门视频 适配器
 * Created by Admin on 2018/8/15.
 */
public class HotVideoViewAdapter extends BGAAdapterViewAdapter<LocalBean> {

    private LayoutParams layoutParams;
    private int minspacing;
    private int maxspacing;

    public HotVideoViewAdapter(Context context, HorizontalListView listView) {
        super(context, R.layout.item_hotvideo);
        maxspacing = DisplayUtil.dip2px(context, 15);
        minspacing = DisplayUtil.dip2px(context, 10);
        int width = DisplayUtil.getWidthForScreen(context, 25, 2f, 3f);
        layoutParams = new LayoutParams(width, width / 2);
        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, width / 2 + (int) (context.getResources().getDimension(R.dimen.dimen60)));
        listView.setLayoutParams(layoutParams2);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, LocalBean listBean) {
        Log.d("position", position + "");
        RelativeLayout rl_hotVideo = (RelativeLayout) viewHolderHelper.getView(R.id.rl_hotVideo);
        rl_hotVideo.getParent().requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            rl_hotVideo.setPadding(maxspacing, 0, maxspacing, 0);
        } else {
            if (position == 0) {
                rl_hotVideo.setPadding(maxspacing, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                rl_hotVideo.setPadding(minspacing, 0, maxspacing, 0);
            } else {
                rl_hotVideo.setPadding(minspacing, 0, 0, 0);
            }
        }
        ((ImageView) viewHolderHelper.getView(R.id.img_region)).setLayoutParams(layoutParams);

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), viewHolderHelper.getImageView(R.id.img_hotVideo), R.mipmap.placeholderfigure);

    }

}