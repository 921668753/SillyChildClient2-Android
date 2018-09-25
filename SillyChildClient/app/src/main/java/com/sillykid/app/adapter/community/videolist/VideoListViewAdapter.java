package com.sillykid.app.adapter.community.videolist;

import android.support.v7.widget.RecyclerView;

import com.common.cklibrary.utils.custommediaplayer.JZPLMediaPlayer;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.community.CommunityBean.DataBean.ResultBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 上下滑动切换视频  适配器
 */
public class VideoListViewAdapter extends BGARecyclerViewAdapter<ResultBean> {

    private int itemCount = 0;

    public VideoListViewAdapter(RecyclerView recyclerView, int itemCount) {
        super(recyclerView, R.layout.item_dynamicvideo);
        this.itemCount = itemCount;
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        super.setItemChildListener(helper, viewType);
        helper.setItemChildClickListener(R.id.ll_content1);
        helper.setItemChildClickListener(R.id.ll_bottom1);
        helper.setItemChildClickListener(R.id.ll_collection1);
        helper.setItemChildClickListener(R.id.ll_comment1);
    }


//    @Override
//    public int getItemCount() {
//        return itemCount;
//    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {
        JzvdStd jzVideoPlayerStandard = (JzvdStd) helper.getView(R.id.videoplayer);
        String url = model.getPicture().substring(0, model.getPicture().indexOf("?"));
        JZDataSource jzDataSource = new JZDataSource(url);
        jzDataSource.looping = true;
        Jzvd.SAVE_PROGRESS = false;
        jzVideoPlayerStandard.setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_NORMAL);
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getPicture(), jzVideoPlayerStandard.thumbImageView, R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_zanNum1, model.getConcern_number());
        helper.setText(R.id.tv_collectionNum1, model.getConcern_number());
        helper.setText(R.id.tv_commentNum1, model.getConcern_number());
        JzvdStd.setMediaInterface(new JZPLMediaPlayer());
//        jzVideoPlayerStandard.onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
//        jzVideoPlayerStandard.startVideo();
    }

}
