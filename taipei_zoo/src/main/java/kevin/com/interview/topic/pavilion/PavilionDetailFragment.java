package kevin.com.interview.topic.pavilion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.base.BaseActivity;
import kevin.com.interview.topic.base.BaseFragment;
import kevin.com.interview.topic.constant.AppConstant;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.entity.PlantEntity;
import kevin.com.interview.topic.ui.recyclerview.CommonAdapter;
import kevin.com.interview.topic.ui.recyclerview.CommonDividerItemDecoration;

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

public class PavilionDetailFragment extends BaseFragment implements PavilionDetailContract.View {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private PavilionDetailContract.Presenter mPresenter;
    private PavilionAreaEntity mPavilionAreaEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pavilion_detail, null);
        mPavilionAreaEntity = (PavilionAreaEntity) getArguments().getSerializable(AppConstant.BUNDLE_PAVILION_AREA_ENTITY);
        mPresenter = new PavilionDetailPresenter(this, mPavilionAreaEntity);
        mPresenter.onCreate();
        mPresenter.requestDate();
        ((BaseActivity) getActivity()).setToolBarTitle(mPavilionAreaEntity.geteName());
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showLoading(boolean isShow) {
        ((BaseActivity) getActivity()).showLoadingView(isShow);
    }

    @Override
    public void showEmpty(boolean isShow, String error, View.OnClickListener listener) {
        ((BaseActivity) getActivity()).showEmptyView(isShow, error, listener);
    }

    @Override
    public void showRecyclerView(List<PlantEntity> list) {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CommonAdapter(list);
        ((CommonAdapter) adapter).setHeaderModel(mPavilionAreaEntity);
        recyclerView = getView().findViewById(R.id.fragmentPavilionDetail_plantRecycleView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CommonDividerItemDecoration(getContext(), CommonDividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (getActivity() == null) {
                    return;
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                } else {
                    Glide.with(getActivity()).pauseRequests();
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
