package kevin.com.interview.topic.base;

import android.app.Application;

import kevin.com.interview.topic.network.NetWorker;
import kevin.com.interview.topic.network.OkHttpNetWorker;

/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-24
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/

public class BaseApplication extends Application {

    private static BaseApplication instance = null;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = BaseApplication.this;
        NetWorker.init(new OkHttpNetWorker());
    }
}
