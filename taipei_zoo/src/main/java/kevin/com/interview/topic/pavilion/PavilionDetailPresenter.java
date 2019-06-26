package kevin.com.interview.topic.pavilion;

import android.text.TextUtils;
import android.view.View;

import kevin.com.interview.topic.constant.ApiConstant;
import kevin.com.interview.topic.entity.PavilionAreaEntity;
import kevin.com.interview.topic.entity.PlantResponseEntity;
import kevin.com.interview.topic.network.NetWorkCallBack;
import kevin.com.interview.topic.network.NetWorker;

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
public class PavilionDetailPresenter implements PavilionDetailContract.Presenter {

    private PavilionDetailContract.View mView;
    private PavilionAreaEntity mPavilionAreaEntity;
    private View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestDate();
        }
    };

    public PavilionDetailPresenter(PavilionDetailContract.View view, PavilionAreaEntity pavilionAreaEntity) {
        mView = view;
        mPavilionAreaEntity = pavilionAreaEntity;
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
        mView.showEmpty(false, "", null);
        mView.showLoading(true);

        String apiUrl = ApiConstant.API_PLANT_URL;
        if (!TextUtils.isEmpty(mPavilionAreaEntity.geteName())) {
            apiUrl = apiUrl + "&q=" + mPavilionAreaEntity.geteName();
        }
        NetWorker.getInstance().get(apiUrl
                , new NetWorkCallBack<PlantResponseEntity>() {
                    @Override
                    public void onSuccess(PlantResponseEntity result) {
                        if (mView == null) {
                            return;
                        }
                        mView.showLoading(false);
                        mView.showEmpty(false, "", null);
                        mView.showRecyclerView(result.getResultEntity().getResults());

                    }

                    @Override
                    public void onFail(String message) {
                        if (mView == null) {
                            return;
                        }
                        mView.showLoading(false);
                    }
                }, this);
    }
}
