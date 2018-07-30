package com.sillykid.app.adapter.main.homepage;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.common.cklibrary.utils.myview.HorizontalListView;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.DataBean.VideoListBean;
import com.sillykid.app.utils.DisplayUtil;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 热门视频 适配器
 * Created by Admin on 2018/8/15.
 */
public class HotVideoViewAdapter extends BGAAdapterViewAdapter<VideoListBean> {

    private LayoutParams layoutParams;

    private int minspacing;
    private int maxspacing;

    public HotVideoViewAdapter(Context context, HorizontalListView listView) {
        super(context, R.layout.item_hotvideo);
        maxspacing = DisplayUtil.dip2px(context, 15);
        minspacing = DisplayUtil.dip2px(context, 17);
        int width = DisplayUtil.getWidthForScreen(context, 30, 2f, 3f);
        layoutParams = new LayoutParams(width, width / 2);
        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, (int) (context.getResources().getDimension(R.dimen.dimen100)));
        listView.setLayoutParams(layoutParams2);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, VideoListBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_hotVideo = (LinearLayout) viewHolderHelper.getView(R.id.ll_hotVideo1);
        ll_hotVideo.requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            ll_hotVideo.setPadding(maxspacing, 0, maxspacing, 0);
        } else {
            if (position == 0) {
                ll_hotVideo.setPadding(maxspacing, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                ll_hotVideo.setPadding(minspacing, 0, maxspacing, 0);
            } else {
                ll_hotVideo.setPadding(minspacing, 0, 0, 0);
            }
        }
        ll_hotVideo.setLayoutParams(layoutParams);

        /**
         * 图片
         */
        GlideImageLoader.glideLoaderRaudio(mContext, listBean.getVideo_image(), viewHolderHelper.getImageView(R.id.img_hotVideo), 4, R.mipmap.placeholderfigure);

    }

}