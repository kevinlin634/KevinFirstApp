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

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class NetWorkCallBack<Result> implements ICallBack {

    @Override
    public void onSuccess(String result) {
        // 將result string轉換為JSON
        Gson gson = new Gson();
        Class<?> clz = generateClassInfo(this);
        Result objResult = (Result) gson.fromJson(result, clz);
        onSuccess(objResult);
    }

    /**
     * 獲取泛型資料物件類型
     *
     * @param object
     * @return
     */
    private Class<?> generateClassInfo(Object object) {
        //獲取帶有泛型的父親類別
        Type genType = object.getClass().getGenericSuperclass();
        //獲取泛型數組
        Type[] actualTypeArguments = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }

    public abstract void onSuccess(Result result);

    public abstract void onFail(String message);

    @Override
    public void onFail() {
        Log.e("NetWorkCallBack", "onFail:");
        onFail("onFail");
    }

}
