package kevin.com.interview.topic.pavilion;

import java.util.List;

import kevin.com.interview.topic.entity.PlantEntity;

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

public class PavilionDetailContract {

    interface Presenter {

        void onCreate();

        void onResume();

        void onDestroy();

        void requestDate();
    }

    interface View {

        void showLoading(boolean isShow);

        void showEmpty(boolean isShow, String error, android.view.View.OnClickListener listener);

        void showRecyclerView(List<PlantEntity> list);

    }
}
