package com.sillykid.app.adapter.homepage.airporttransportation.comments;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.common.ImagePreviewNoDelActivity;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.comments.CharterCommentsBean.DataBean.ResultBean;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 产品信息---评论
 */
public class CharterCommentsViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CharterCommentsGridViewAdapter> commentsCounters;
    private SparseArray<List<ImageItem>> selImageListCounters;


    public CharterCommentsViewAdapter(Context context) {
        super(context, R.layout.item_userevaluation);
        this.commentsCounters = new SparseArray<>();
        this.selImageListCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.ll_giveLike);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {
        GlideImageLoader.glideLoader(mContext, model.getFace(), helper.getImageView(R.id.img_head), 0, R.mipmap.avatar);
        helper.setText(R.id.tv_nickName, model.getNickname());
        switch (model.getSatisfaction_level()) {
            case 1:
                helper.setImageResource(R.id.img_satisfactionLevel, R.mipmap.comment_star_one);
                break;
            case 2:
                helper.setImageResource(R.id.img_satisfactionLevel, R.mipmap.comment_star_two);
                break;
            case 3:
                helper.setImageResource(R.id.img_satisfactionLevel, R.mipmap.comment_star_three);
                break;
            case 4:
                helper.setImageResource(R.id.img_satisfactionLevel, R.mipmap.comment_star_four);
                break;
            case 5:
                helper.setImageResource(R.id.img_satisfactionLevel, R.mipmap.comment_star_five);
                break;
        }
        helper.setText(R.id.tv_content, model.getContent());


        /**
         * 图片
         */
        List<ImageItem> selImageList = null;
        NoScrollGridView gv_imgComments = (NoScrollGridView) helper.getView(R.id.gv_imgComments);
        if (selImageListCounters.get(gv_imgComments.hashCode()) != null) {
            selImageListCounters.get(gv_imgComments.hashCode()).clear();
            selImageList = selImageListCounters.get(gv_imgComments.hashCode());
        } else {
            selImageList = new ArrayList<>();
            selImageListCounters.put(gv_imgComments.hashCode(), selImageList);
        }
        CharterCommentsGridViewAdapter commentsGridViewAdapter = null;
        if (commentsCounters.get(gv_imgComments.hashCode()) != null && model.getPicture() != null && model.getPicture().size() > 0) {
            commentsGridViewAdapter = commentsCounters.get(gv_imgComments.hashCode());
        } else if (model.getPicture() != null && model.getPicture().size() > 0) {
            commentsGridViewAdapter = new CharterCommentsGridViewAdapter(mContext);
            gv_imgComments.setAdapter(commentsGridViewAdapter);
            commentsCounters.put(gv_imgComments.hashCode(), commentsGridViewAdapter);
            CharterCommentsGridViewAdapter finalCommentsGridViewAdapter = commentsGridViewAdapter;
            gv_imgComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //打开预览
                    //  onStatusListener.onSetStatusListener(adapterView, view, finalCommentsGridViewAdapter, position, i);
                    Intent intentPreview = new Intent(mContext, ImagePreviewNoDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) finalCommentsGridViewAdapter.getData());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, i);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    // startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                    mContext.startActivity(intentPreview);
                }
            });
        }

        if (commentsGridViewAdapter != null && model.getPicture() != null && model.getPicture().size() > 0) {
            helper.setVisibility(R.id.gv_imgComments, View.VISIBLE);
            selImageList.clear();
            for (int i = 0; i < model.getPicture().size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = model.getPicture().get(i);
                selImageList.add(imageItem);
            }
            commentsGridViewAdapter.clear();
            commentsGridViewAdapter.addNewData(selImageList);
        } else {
            helper.setVisibility(R.id.gv_imgComments, View.GONE);
        }

        helper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getCreate_time()), "yyyy.MM.dd"));
        helper.setText(R.id.tv_zanNum, model.getLike_number() + "");
        if (model.isIs_like()) {
            helper.setImageResource(R.id.img_giveLike, R.mipmap.dynamic_zan1);
            helper.setTextColorRes(R.id.tv_zanNum, R.color.greenColors);
        } else {
            helper.setImageResource(R.id.img_giveLike, R.mipmap.dynamic_zan);
            //       tv_zanNum.setText(getString(R.string.giveLike));
            helper.setTextColorRes(R.id.tv_zanNum, R.color.tabColors);
        }
    }



    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllComments() {
        if (commentsCounters != null) {
            Log.e("TAG", "size :  " + commentsCounters.size());
            for (int i = 0, length = commentsCounters.size(); i < length; i++) {
                CharterCommentsGridViewAdapter cdt = commentsCounters.get(commentsCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        if (selImageListCounters != null) {
            Log.e("TAG", "size :  " + selImageListCounters.size());
            for (int i = 0, length = selImageListCounters.size(); i < length; i++) {
                List<ImageItem> selImageList = selImageListCounters.get(selImageListCounters.keyAt(i));
                if (selImageList != null) {
                    selImageList.clear();
                    selImageList = null;
                }
            }
        }
    }

}
