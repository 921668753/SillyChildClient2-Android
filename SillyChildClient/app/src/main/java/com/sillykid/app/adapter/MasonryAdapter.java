package com.sillykid.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.entity.DynamicStateBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

/**
 * 我的发布——动态  适配器
 * Created by Administrator on 2017/9/6.
 */

public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private String head_pic;
    private String nickname;
    private List<ListBean> list;
    private Context mcontext;
    private MasonryItemOnClickListener listener;
    private boolean isme;//true：我的动态和攻略；false：收藏的动态和攻略

    public MasonryAdapter(Context mcontext, List<ListBean> list, MasonryItemOnClickListener listener, boolean isme) {
        this.list = list;
        this.mcontext = mcontext;
        this.listener = listener;
        this.isme = isme;
        if (isme) {
            head_pic = PreferenceHelper.readString(mcontext, StringConstants.FILENAME, "head_pic");
            nickname = PreferenceHelper.readString(mcontext, StringConstants.FILENAME, "nickname");
        }
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myreleaselabel, viewGroup, false);
        MasonryView masonryView = new MasonryView(view, listener);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView masonryView, int position) {
        if (isme && position == 0) {
            masonryView.ll_new.setVisibility(View.VISIBLE);
            masonryView.ll_content.setVisibility(View.GONE);
            masonryView.tv_release.setText(list.get(position).getTitle());
        } else {
            masonryView.ll_new.setVisibility(View.GONE);
            masonryView.ll_content.setVisibility(View.VISIBLE);
            GlideImageLoader.glideOrdinaryLoader(mcontext, list.get(position).getImg(), masonryView.iv_picture, R.mipmap.placeholderfigure);
            masonryView.tv_title.setText(list.get(position).getTitle());
            if (TextUtils.isEmpty(list.get(position).getSubTitle())) {
                masonryView.tv_detail.setVisibility(View.GONE);
            } else {
                masonryView.tv_detail.setText(list.get(position).getSubTitle());
            }
            if (isme) {
                GlideImageLoader.glideLoader(mcontext, head_pic, masonryView.iv_touxiang, 0);
                masonryView.tv_name.setText(nickname);
            } else {
                GlideImageLoader.glideLoader(mcontext, list.get(position).getReadAvatar(), masonryView.iv_touxiang, 0);
                masonryView.tv_name.setText(list.get(position).getOwnerName());
            }
            masonryView.tv_zannumber.setText(list.get(position).getPraiseNum());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MasonryView extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        LinearLayout ll_new;
        LinearLayout ll_content;
        TextView tv_release;
        ImageView iv_picture;
        TextView tv_title;
        TextView tv_detail;
        ImageView iv_touxiang;
        TextView tv_name;
        TextView tv_zannumber;
        MasonryItemOnClickListener itemlistener;

        public MasonryView(View itemView, MasonryItemOnClickListener itemlistener) {
            super(itemView);
            ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
            ll_new = (LinearLayout) itemView.findViewById(R.id.ll_new);
            tv_release = (TextView) itemView.findViewById(R.id.tv_release);
            iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_detail);
            iv_touxiang = (ImageView) itemView.findViewById(R.id.iv_touxiang);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_zannumber = (TextView) itemView.findViewById(R.id.tv_zannumber);
            this.itemlistener = itemlistener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }


        @Override
        public void onClick(View view) {
            if (itemlistener != null) itemlistener.masonryOnItemClick(view, getPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (itemlistener != null) itemlistener.masonryOnLongItemClick(view, getPosition());
            return false;
        }
    }

    public interface MasonryItemOnClickListener {
        void masonryOnItemClick(View view, int postion);

        void masonryOnLongItemClick(View view, int postion);
    }

}
