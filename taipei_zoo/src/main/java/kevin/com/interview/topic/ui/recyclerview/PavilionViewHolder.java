package kevin.com.interview.topic.ui.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
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
import kevin.com.interview.topic.constant.AppConstant;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.manager.AppActivityManager;
import kevin.com.interview.topic.pavilion.PavilionActivity;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-23
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/
public class PavilionViewHolder extends BaseViewHolder implements View.OnClickListener, RequestListener {

    private ConstraintLayout mConstraintLayout;
    private ImageView mImageView;
    private TextView mTitleTextView;
    private TextView mInfoTextView;
    private TextView mMemoTextView;
    private WeakReference<Context> mContextWeakReference;
    private PavilionAreaEntity entity;

    public PavilionViewHolder(View itemView) {
        super(itemView);
        mContextWeakReference = new WeakReference<>(itemView.getContext());
        mConstraintLayout = itemView.findViewById(R.id.pavilionRecyclerViewViewHolder_layout);
        mConstraintLayout.setOnClickListener(this);
        mImageView = itemView.findViewById(R.id.pavilionRecyclerViewViewHolder_ImageView);
        mTitleTextView = itemView.findViewById(R.id.pavilionRecyclerViewViewHolder_titleTextView);
        mInfoTextView = itemView.findViewById(R.id.pavilionRecyclerViewViewHolder_infoTextView);
        mMemoTextView = itemView.findViewById(R.id.pavilionRecyclerViewViewHolder_memoTextView);
    }

    @Override
    public void onBindViewHolder(Object viewInfo) {
        entity = (PavilionAreaEntity) viewInfo;

        if (!TextUtils.isEmpty(entity.geteName())) {
            mTitleTextView.setText(entity.geteName());
        }
        if (!TextUtils.isEmpty(entity.geteInfo())) {
            mInfoTextView.setText(entity.geteInfo());
        }
        if (!TextUtils.isEmpty(entity.geteMemo())) {
            mMemoTextView.setText(entity.geteMemo());
        } else {
            mMemoTextView.setText(mContextWeakReference.get().getString(R.string.no_take_a_rest_info));
        }

        Log.e("PavilionViewHolder", "onBindViewHolder getePicURL: " + entity.getePicURL());
        if (!TextUtils.isEmpty(entity.getePicURL())) {
            Glide.with(mImageView.getContext())
                    .load(entity.getePicURL())
                    .listener(this)
                    .into(mImageView);
        } else {
            mImageView.setBackgroundResource(R.mipmap.download_fail);
        }

    }

    @Override
    public void onViewRecycled() {
        Glide.with(mImageView.getContext()).clear(mImageView);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContextWeakReference.get(), PavilionActivity.class);
        intent.putExtra(AppConstant.INTENT_PAVILION_AREA_ENTITY, entity);
        AppActivityManager.getInstance().getCurrentActivity().startActivity(intent);
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        Log.e("PavilionViewHolder", "onLoadFailed onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
        mImageView.setBackgroundResource(R.mipmap.download_fail);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        return false;
    }
}
