package kevin.com.interview.topic.plant;

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
public class PlantDetailContract {

    interface Presenter {

        void onCreate();

        void onResume();

        void onDestroy();

    }

    interface View {

        void layoutPlantDetail(PlantEntity entity);
    }
}
