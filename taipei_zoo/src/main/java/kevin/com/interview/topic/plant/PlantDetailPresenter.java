package kevin.com.interview.topic.plant;

import android.util.Log;

import kevin.com.interview.topic.entity.PlantEntity;

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

public class PlantDetailPresenter implements PlantDetailContract.Presenter {

    private PlantDetailContract.View mView;
    private PlantEntity mPlantEntity;

    public PlantDetailPresenter(PlantDetailContract.View view, PlantEntity plantEntity) {
        Log.e("PlantDetailPresenter", "PlantDetailPresenter:" + plantEntity);
        mView = view;
        mPlantEntity = plantEntity;
    }

    @Override
    public void onCreate() {
        Log.e("PlantDetailPresenter", "onCreate");
    }

    @Override
    public void onResume() {
        Log.e("PlantDetailPresenter", "onResume");
        mView.layoutPlantDetail(mPlantEntity);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mPlantEntity = null;
    }

}
