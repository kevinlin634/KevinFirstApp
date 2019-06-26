package kevin.com.interview.topic.network;

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

public class NetWorker implements IWorker {

    private static class NetWorkerHolder {
        private static final NetWorker INSTANCE = new NetWorker();
    }

    private NetWorker() {
    }

    public static final NetWorker getInstance() {
        return NetWorkerHolder.INSTANCE;
    }

    /**
     * 需要一個網路訪問對象，相當於某個第三方網路框架的worker
     */
    private static IWorker mIWorker;

    /**
     * 初始化實作IWorker接口的第三方網路框架
     *
     * @param worker
     */
    public static void init(IWorker worker) {
        mIWorker = worker;
    }

    @Override
    public void post(String url, ICallBack callBack, Object requestTag) {
        mIWorker.post(url, callBack, requestTag);
    }

    @Override
    public void get(String url, ICallBack callBack, Object requestTag) {
        mIWorker.get(url, callBack, requestTag);
    }

    @Override
    public void cancel(Object tag) {
        mIWorker.cancel(tag);
    }

}
