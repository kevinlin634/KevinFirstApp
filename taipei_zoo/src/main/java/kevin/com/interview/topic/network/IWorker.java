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

public interface IWorker {

    /**
     * post網路請求接口 （依照不同第三方去實作該接口）
     *
     * @param url
     * @param callBack
     * @param requestTag
     */
    void post(String url, ICallBack callBack, Object requestTag);


    /**
     * get網路請求接口 （依照不同第三方去實作該接口）
     *
     * @param url
     * @param callBack
     * @param requestTag
     */
    void get(String url, ICallBack callBack, Object requestTag);


    /**
     * 取消請求
     *
     * @param tag
     */
    void cancel(Object tag);

}
