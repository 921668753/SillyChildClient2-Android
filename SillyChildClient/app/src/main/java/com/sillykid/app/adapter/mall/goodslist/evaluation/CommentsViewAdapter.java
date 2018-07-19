package com.sillykid.app.adapter.mall.goodslist.evaluation;

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
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.comments.CommentsBean.DataBean.CommentListBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class CommentsViewAdapter extends BGAAdapterViewAdapter<CommentListBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CommentsGridViewAdapter> commentsCounters;
    private SparseArray<AddCommentsGridViewAdapter> addCommentsCounters;
    private SparseArray<List<ImageItem>> selImageListCounters;

    private OnStatusListener onStatusListener;

    public CommentsViewAdapter(Context context) {
        super(context, R.layout.item_comments);
        this.commentsCounters = new SparseArray<>();
        this.selImageListCounters = new SparseArray<>();
        this.addCommentsCounters = new SparseArray<>();

    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.ll_revert);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, CommentListBean model) {

        /**
         * 头像
         */
        GlideImageLoader.glideLoader(mContext, model.getFace(), helper.getImageView(R.id.img_head), 0, R.mipmap.placeholderfigure);

        /**
         * 名字
         */
        helper.setText(R.id.tv_name, model.getUname());

        /**
         * 时间
         */
        helper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getDateline()), "yyyy-MM-dd"));

        /**
         * 评论内容
         */
        if (StringUtils.isEmpty(model.getContent())) {
            helper.setText(R.id.tv_commentscContent, mContext.getString(R.string.notFillEvaluation));
        } else {
            helper.setText(R.id.tv_commentscContent, model.getContent());
        }


        /**
         * 图片
         */
        List<ImageItem> selImageList;
        NoScrollGridView gv_imgComments = (NoScrollGridView) helper.getView(R.id.gv_imgComments);
        if (selImageListCounters.get(gv_imgComments.hashCode()) != null) {
            selImageListCounters.get(gv_imgComments.hashCode()).clear();
            selImageList = selImageListCounters.get(gv_imgComments.hashCode());
        } else {
            selImageList = new ArrayList<>();
            selImageListCounters.put(gv_imgComments.hashCode(), selImageList);
        }
        CommentsGridViewAdapter commentsGridViewAdapter = null;
        if (commentsCounters.get(gv_imgComments.hashCode()) != null && model.getGallery() != null && model.getGallery().size() > 0) {
            commentsGridViewAdapter = commentsCounters.get(gv_imgComments.hashCode());
        } else if (model.getGallery() != null && model.getGallery().size() > 0) {
            commentsGridViewAdapter = new CommentsGridViewAdapter(mContext);
            gv_imgComments.setAdapter(commentsGridViewAdapter);
            commentsCounters.put(gv_imgComments.hashCode(), commentsGridViewAdapter);
            CommentsGridViewAdapter finalCommentsGridViewAdapter = commentsGridViewAdapter;
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

        if (commentsGridViewAdapter != null && model.getGallery() != null && model.getGallery().size() > 0) {
            helper.setVisibility(R.id.gv_imgComments, View.VISIBLE);
            selImageList.clear();
            for (int i = 0; i < model.getGallery().size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = model.getGallery().get(i).getOriginal();
                selImageList.add(imageItem);
            }
            commentsGridViewAdapter.clear();
            commentsGridViewAdapter.addNewData(selImageList);
        } else {
            helper.setVisibility(R.id.gv_imgComments, View.GONE);
        }

        /**
         * 追评
         */
        // helper.setText(R.id.tv_additionalReviewContent, model.getId());

        /**
         * 图片
         */
//        NoScrollGridView gv_imgAddComments = (NoScrollGridView) helper.getView(R.id.gv_imgAddComments);
//        AddCommentsGridViewAdapter addCommentsGridViewAdapter = new AddCommentsGridViewAdapter(mContext);
//        gv_imgAddComments.setAdapter(addCommentsGridViewAdapter);
//        addCommentsCounters.put(gv_imgAddComments.hashCode(), addCommentsGridViewAdapter);
        // addCommentsGridViewAdapter.addNewData(model.getImgs());
//        gv_imgComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//            }
//        });
//        gv_imgComments.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
//            @Override
//            public boolean onTouchInvalidPosition(int motionEvent) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//                return false;
//            }
//        });
    }


    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetStatusListener(AdapterView<?> adapterView, View view, CommentsGridViewAdapter adapter, int position, int position1);
        //  void onDeleteListener(int pos, int tagPos);
    }


    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllComments() {
        if (commentsCounters != null) {
            Log.e("TAG", "size :  " + commentsCounters.size());
            for (int i = 0, length = commentsCounters.size(); i < length; i++) {
                CommentsGridViewAdapter cdt = commentsCounters.get(commentsCounters.keyAt(i));
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
        if (addCommentsCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + addCommentsCounters.size());
        for (int i = 0, length = addCommentsCounters.size(); i < length; i++) {
            AddCommentsGridViewAdapter cdt = addCommentsCounters.get(addCommentsCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
    }


}
