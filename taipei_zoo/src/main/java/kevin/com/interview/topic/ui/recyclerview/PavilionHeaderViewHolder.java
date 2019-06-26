package kevin.com.interview.topic.ui.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.manager.AppActivityManager;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-25
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/
public class PavilionHeaderViewHolder extends BaseViewHolder implements RequestListener {

    private ImageView mImageView;
    private TextView mInfoTextView;
    private TextView mMemoTextView;
    private TextView mCategoryTextView;
    private TextView mUrlTextView;
    private WeakReference<Context> mContextWeakReference;

    public PavilionHeaderViewHolder(View itemView) {
        super(itemView);
        mContextWeakReference = new WeakReference<>(itemView.getContext());
        mImageView = itemView.findViewById(R.id.pavilionHeaderRecyclerViewViewHolder_headerImageView);
        mInfoTextView = itemView.findViewById(R.id.pavilionHeaderRecyclerViewViewHolder_infoTextView);
        mMemoTextView = itemView.findViewById(R.id.pavilionHeaderRecyclerViewViewHolder_memoTextView);
        mCategoryTextView = itemView.findViewById(R.id.pavilionHeaderRecyclerViewViewHolder_categoryTextView);
        mUrlTextView = itemView.findViewById(R.id.pavilionHeaderRecyclerViewViewHolder_urlTextView);
    }

    @Override
    public void onBindViewHolder(Object viewInfo) {
        final PavilionAreaEntity entity = (PavilionAreaEntity) viewInfo;

        if (!TextUtils.isEmpty(entity.geteInfo())) {
            mInfoTextView.setText(entity.geteInfo());
        }

        if (!TextUtils.isEmpty(entity.geteMemo())) {
            mMemoTextView.setText(entity.geteMemo());
        } else {
            mMemoTextView.setText(mContextWeakReference.get().getString(R.string.no_take_a_rest_info));
        }

        if (!TextUtils.isEmpty(entity.getE_Category())) {
            mCategoryTextView.setText(entity.getE_Category());
        }

        if (!TextUtils.isEmpty(entity.geteURL())) {
            mUrlTextView.setVisibility(View.VISIBLE);
            mUrlTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            mUrlTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startToBrowser(entity.geteURL());
                }
            });
        } else {
            mUrlTextView.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(entity.getePicURL())) {
            Glide.with(mImageView.getContext())
                    .load(entity.getePicURL())
                    .listener(this)
                    .into(mImageView);
        } else {
            mImageView.setBackgroundResource(R.mipmap.download_fail);
        }

    }

    private void startToBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        AppActivityManager.getInstance().getCurrentActivity().startActivity(intent);
    }

    @Override
    public void onViewRecycled() {
        Glide.with(mImageView.getContext()).clear(mImageView);
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        mImageView.setImageResource(R.mipmap.download_fail);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        return false;
    }
}
