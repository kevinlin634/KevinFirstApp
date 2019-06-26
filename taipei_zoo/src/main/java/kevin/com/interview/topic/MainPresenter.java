package kevin.com.interview.topic;

import android.util.Log;
import android.view.View;

import kevin.com.interview.topic.constant.ApiConstant;
import kevin.com.interview.topic.entity.PavilionResponseEntity;
import kevin.com.interview.topic.network.NetWorkCallBack;
import kevin.com.interview.topic.network.NetWorker;

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

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestDate();
        }
    };

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        mView = null;
        mRetryClickListener = null;
        NetWorker.getInstance().cancel(this);
    }

    @Override
    public void requestDate() {
        if (mView == null) {
            return;

        }
        mView.showEmpty(false, "", null);
        mView.showLoading(true);
        NetWorker.getInstance().get(ApiConstant.API_PAVILION_AREA_URL
                , new NetWorkCallBack<PavilionResponseEntity>() {
                    @Override
                    public void onSuccess(PavilionResponseEntity result) {
                        mView.showLoading(false);

                        if (result == null) {
                            mView.showEmpty(true, "", mRetryClickListener);
                            return;
                        }
                        Log.e("TAG", "onSuccess");
                        Log.e("TAG", "onSuccess result:" + result.getResultEntity().getResults().size());

                        if (result.getResultEntity().getResults().size() > 0) {
                            mView.showEmpty(false, "", null);
                            mView.showRecyclerView(result.getResultEntity().getResults());
                        } else {
                            mView.showEmpty(true, "", mRetryClickListener);
                        }

                    }

                    @Override
                    public void onFail(String message) {
                        Log.e("TAG", "onFail");
                        mView.showLoading(false);
                        mView.showEmpty(true, message, mRetryClickListener);
                    }
                }, this);
    }
}
