package kevin.com.interview.topic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.List;

import kevin.com.interview.topic.base.BaseActivity;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.ui.recyclerview.CommonAdapter;
import kevin.com.interview.topic.ui.recyclerview.CommonDividerItemDecoration;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-6-23
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/

public class MainActivity extends BaseActivity implements MainContract.View {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainContract.Presenter mPresenter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initTooBar();
        mPresenter.requestDate();
    }

    private void initTooBar() {
        Toolbar toolbar = generateToolbar();
        mDrawerLayout = findViewById(R.id.activityMain_mainDrawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.pavilion_intro, R.string.pavilion_intro);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setToolBarTitle(getString(R.string.pavilion_intro));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showLoading(boolean isShow) {
        super.showLoadingView(isShow);
    }

    @Override
    public void showEmpty(boolean isShow, String error, View.OnClickListener listener) {
        super.showEmptyView(isShow, error, listener);
    }

    @Override
    public void showRecyclerView(List<PavilionAreaEntity> list) {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new CommonAdapter(list);
        recyclerView = findViewById(R.id.activityMain_recycleView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CommonDividerItemDecoration(this, CommonDividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MainActivity.this).resumeRequests();//恢复Glide加载图片
                } else {
                    Glide.with(MainActivity.this).pauseRequests();//禁止Glide加载图片
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
