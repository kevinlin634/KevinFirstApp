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

public interface ICallBack {

    void onSuccess(String result);

    void onFail();
}
