package kevin.com.interview.topic.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kevin.com.interview.topic.R;
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

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppActivityManager.getInstance().finishActivity(this);
    }

    public Toolbar generateToolbar() {
        Toolbar toolbar = findViewById(R.id.layoutCommonAppBar_toolbar);
        if (toolbar == null) {
            return null;
        }
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            //actionBar.setHomeAsUpIndicator(homeAsUpIndicatorDrawable);
        }
        return toolbar;
    }

    public void setToolBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(title);
            //actionBar.setHomeAsUpIndicator(homeAsUpIndicatorDrawable);
        }
    }

    public void showEmptyView(boolean isShow, String error, View.OnClickListener listener) {
        if (findViewById(R.id.layoutEmptyView_layout) == null) {
            return;
        }
        if (isShow) {
            findViewById(R.id.layoutEmptyView_layout).setVisibility(View.VISIBLE);
            TextView textView = findViewById(R.id.layoutEmptyView_errorMessage);
            if (!TextUtils.isEmpty(error)) {
                textView.setText(error);
            }
            Button button = findViewById(R.id.layoutEmptyView_retryButton);
            button.setOnClickListener(listener);
        } else {
            findViewById(R.id.layoutEmptyView_layout).setVisibility(View.GONE);
        }

    }

    public void showLoadingView(boolean isShow) {
        if (findViewById(R.id.layoutLoadingProgressBar_loadingLayout) == null) {
            return;
        }
        if (isShow) {
            findViewById(R.id.layoutLoadingProgressBar_loadingLayout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.layoutLoadingProgressBar_loadingLayout).setVisibility(View.GONE);
        }
    }

    public void addFragment(Fragment fragment, Bundle args, int contentLayoutId) {
        if (fragment == null) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (args != null) {
            fragment.setArguments(args);
        }
        Log.e("BaseActivity", "addFragment tag:" + fragment.getClass().getSimpleName());

        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out);
        fragmentTransaction.add(contentLayoutId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.show(fragment);
        hideOtherFragments(fragment.getClass().getSimpleName());

        fragmentTransaction.commitAllowingStateLoss();
    }

    public void removeFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        Log.e("BaseActivity", "removeFragment:" + fragment.getClass().getSimpleName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void hideOtherFragments(String tag) {
        Log.e("BaseActivity", "hideOtherFragments tag:" + tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (tag != fragment.getTag()) {
                fragmentTransaction.hide(fragment);
                Log.e("BaseActivity", "hideOtherFragments hide fragment Tag:" + fragment.getTag());
            }
        }
    }

    public String getCurrentFragmentTag() {
        Log.e("BaseActivity", "getCurrentFragmentTag");
        String fragmentName = "";
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Log.e("BaseActivity", "getCurrentFragmentTag size" + fragments.size());
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            Log.e("BaseActivity", "getCurrentFragmentTag fragment :" + fragmentName);
            if (fragment != null && fragment.isVisible()) {
                fragmentName = fragment.getTag();
                Log.e("BaseActivity", "getCurrentFragmentTag fragmentName:" + fragmentName);
            }
        }
        return fragmentName;
    }
}
