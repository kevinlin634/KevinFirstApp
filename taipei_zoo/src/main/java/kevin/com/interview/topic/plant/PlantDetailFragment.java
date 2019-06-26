package kevin.com.interview.topic.plant;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.base.BaseActivity;
import kevin.com.interview.topic.base.BaseFragment;
import kevin.com.interview.topic.entity.PlantEntity;

import static kevin.com.interview.topic.constant.AppConstant.BUNDLE_PLANT_ENTITY;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-26
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/

public class PlantDetailFragment extends BaseFragment implements PlantDetailContract.View, RequestListener {

    private PlantDetailPresenter mPlantDetailPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_detail, null);
        mPlantDetailPresenter = new PlantDetailPresenter(this, (PlantEntity) getArguments().getSerializable(BUNDLE_PLANT_ENTITY));
        mPlantDetailPresenter.onCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPlantDetailPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlantDetailPresenter.onDestroy();
    }

    @Override
    public void layoutPlantDetail(PlantEntity entity) {
        Log.e("PlantDetailFragment", "layoutPlantDetail:" + entity);

        if (entity != null) {
            ImageView picImageView = getView().findViewById(R.id.fragmentPlantDetail_picImageView);
            TextView fNameChTextView = getView().findViewById(R.id.fragmentPlantDetail_fNameChTextView);
            TextView fNameEnTextView = getView().findViewById(R.id.fragmentPlantDetail_fNameEnTextView);
            TextView fAlsoKnownTextView = getView().findViewById(R.id.fragmentPlantDetail_fAlsoKnownTextView);
            TextView fBriefTextView = getView().findViewById(R.id.fragmentPlantDetail_fBriefTextView);
            TextView fFeatureTextView = getView().findViewById(R.id.fragmentPlantDetail_fFeatureTextView);
            TextView fFunctionAndApplicationTextView = getView().findViewById(R.id.fragmentPlantDetail_fFunctionAndApplicationTextView);
            TextView fUpdateTextView = getView().findViewById(R.id.fragmentPlantDetail_fUpdateTextView);

            Log.e("PlantDetailFragment", "layoutPlantDetail getfPic01URL:" + entity.getfPic01URL());
            if (!TextUtils.isEmpty(entity.getfPic01URL())) {
                Log.e("PlantDetailFragment", "layoutPlantDetail Glide:");
                Glide.with(picImageView.getContext())
                        .load(entity.getfPic01URL())
                        .listener(this)
                        .into(picImageView);
                picImageView.setBackgroundColor(Color.BLACK);
            } else {
                Log.e("PlantDetailFragment", "layoutPlantDetail download_fail:");
                picImageView.setImageResource(R.mipmap.download_fail);
            }

            ((BaseActivity) getActivity()).setToolBarTitle(entity.getfNameCh());
            fNameChTextView.setText(entity.getfNameCh());
            fNameEnTextView.setText(entity.getfNameEn());
            fAlsoKnownTextView.setText(entity.getfAlsoKnown());
            fBriefTextView.setText(entity.getfBrief());
            fFeatureTextView.setText(entity.getfFeature());
            fFunctionAndApplicationTextView.setText(entity.getfFunctionAndApplication());
            fUpdateTextView.setText(entity.getfUpdate());
        }
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        Log.e("PlantDetailFragment", "onLoadFailed onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
        ((ImageView) getView().findViewById(R.id.fragmentPlantDetail_picImageView)).setImageResource(R.mipmap.download_fail);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        return false;
    }
}
