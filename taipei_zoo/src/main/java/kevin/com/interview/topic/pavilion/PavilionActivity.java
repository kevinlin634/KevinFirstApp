package kevin.com.interview.topic.pavilion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.base.BaseActivity;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.entity.PlantEntity;
import kevin.com.interview.topic.event.CommonEvent;
import kevin.com.interview.topic.plant.PlantDetailFragment;

import static kevin.com.interview.topic.constant.AppConstant.BUNDLE_PAVILION_AREA_ENTITY;
import static kevin.com.interview.topic.constant.AppConstant.BUNDLE_PLANT_ENTITY;
import static kevin.com.interview.topic.constant.AppConstant.INTENT_PAVILION_AREA_ENTITY;
import static kevin.com.interview.topic.event.CommonEvent.EVENT_START_PLANT_DETAIL;

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

public class PavilionActivity extends BaseActivity {

    private PavilionAreaEntity mEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pavilion_detail);
        addFragment(new PavilionDetailFragment(), getModel(), R.id.activityPavilionDetail_contentFrameLayout);
        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventProcess(CommonEvent event) {
        switch (event.getEventID()) {
            case EVENT_START_PLANT_DETAIL:
                Bundle bundle = new Bundle();
                bundle.putSerializable(BUNDLE_PLANT_ENTITY, (PlantEntity) event.getObject());
                addFragment(new PlantDetailFragment(), bundle, R.id.activityPavilionDetail_contentFrameLayout);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void initToolBar() {
        Toolbar toolbar = generateToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PavilionActivity", "initToolBar getCurrentFragmentTag():" + getCurrentFragmentTag());
                backLogic();
            }
        });
    }

    private void backLogic() {
        switch (getCurrentFragmentTag()) {
            case "PavilionDetailFragment":
                finish();
                break;
            case "PlantDetailFragment":
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(PlantDetailFragment.class.getSimpleName());
                removeFragment(fragment);
                setToolBarTitle(mEntity.geteName());
                break;
            default:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backLogic();
    }

    private Bundle getModel() {
        if (getIntent() != null) {
            mEntity = (PavilionAreaEntity) getIntent().getSerializableExtra(INTENT_PAVILION_AREA_ENTITY);
            Bundle bundle = new Bundle();
            bundle.putSerializable(BUNDLE_PAVILION_AREA_ENTITY, mEntity);
            return bundle;
        } else {
            return null;
        }
    }

}
