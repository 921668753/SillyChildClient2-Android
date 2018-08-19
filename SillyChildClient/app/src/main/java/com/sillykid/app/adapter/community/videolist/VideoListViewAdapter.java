package com.sillykid.app.adapter.community.videolist;

import android.support.v7.widget.RecyclerView;

import com.common.cklibrary.utils.custommediaplayer.JZPLMediaPlayer;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.community.CommunityBean.DataBean.ResultBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

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
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) helper.getView(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(model.getPicture().substring(0, model.getPicture().indexOf("?")), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getPicture(), jzVideoPlayerStandard.thumbImageView, R.mipmap.placeholderfigure);
        jzVideoPlayerStandard.startVideo();
        helper.setText(R.id.tv_zanNum1, model.getConcern_number());
        helper.setText(R.id.tv_collectionNum1, model.getConcern_number());
        helper.setText(R.id.tv_commentNum1, model.getConcern_number());
        JZVideoPlayer.setMediaInterface(new JZPLMediaPlayer());
        jzVideoPlayerStandard.onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
    }

}
