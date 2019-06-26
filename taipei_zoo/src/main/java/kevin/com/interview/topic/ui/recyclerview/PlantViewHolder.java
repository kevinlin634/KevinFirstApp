package kevin.com.interview.topic.ui.recyclerview;

import android.content.Context;
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

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.entity.PlantEntity;
import kevin.com.interview.topic.event.CommonEvent;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-24
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/
public class PlantViewHolder extends BaseViewHolder implements View.OnClickListener, RequestListener {

    private ConstraintLayout mConstraintLayout;
    private ImageView mImageView;
    private TextView mTitleTextView;
    private TextView mAlsoKnowTextView;
    private WeakReference<Context> mContextWeakReference;
    private PlantEntity entity;

    public PlantViewHolder(View itemView) {
        super(itemView);
        mContextWeakReference = new WeakReference<>(itemView.getContext());
        mConstraintLayout = itemView.findViewById(R.id.plantRecyclerViewViewHolder_layout);
        mConstraintLayout.setOnClickListener(this);
        mImageView = itemView.findViewById(R.id.plantRecyclerViewViewHolder_ImageView);
        mTitleTextView = itemView.findViewById(R.id.plantRecyclerViewViewHolder_titleTextView);
        mAlsoKnowTextView = itemView.findViewById(R.id.plantRecyclerViewViewHolder_alsoKnowTextView);

    }

    @Override
    public void onBindViewHolder(Object viewInfo) {
        entity = (PlantEntity) viewInfo;

        if (!TextUtils.isEmpty(entity.getfNameCh())) {
            mTitleTextView.setText(entity.getfNameCh());
        }
        if (!TextUtils.isEmpty(entity.getfAlsoKnown())) {
            mAlsoKnowTextView.setText(entity.getfAlsoKnown());
        }

        String url = entity.getfPic01URL();
        Log.e("PavilionViewHolder", "onBindViewHolder getfNameCh: " + entity.getfNameCh() + "  getfPic01URL:" + entity.getfPic01URL());
        if (!TextUtils.isEmpty(url)) {
            Glide.with(mImageView.getContext())
                    .load(entity.getfPic01URL())
                    .listener(this)
                    .into(mImageView);
//            mImageView.setBackgroundColor(Color.BLACK);
        } else {
            mImageView.setImageResource(R.mipmap.download_fail);
        }
    }

    @Override
    public void onViewRecycled() {
        //Glide.with(mImageView.getContext()).clear(mImageView);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new CommonEvent(CommonEvent.EVENT_START_PLANT_DETAIL, entity));
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        Log.e("PlantViewHolder", "onLoadFailed onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
        mImageView.setImageResource(R.mipmap.download_fail);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        return false;
    }
}
