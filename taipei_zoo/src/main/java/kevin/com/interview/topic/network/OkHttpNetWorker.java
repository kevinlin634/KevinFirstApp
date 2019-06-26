package kevin.com.interview.topic.network;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

public class OkHttpNetWorker implements IWorker {

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpNetWorker() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void post(String url, final ICallBack callBack, Object requestTag) {
        Log.e("OkHttpNetWorker", "is OkHttp post");

        RequestBody requestBody = new FormBody.Builder().build();
        Request request = new Request.Builder().url(url).tag(requestTag).post(requestBody).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("OkHttpNetWorker", "onFailure");
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + Log.getStackTraceString(e));

                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getCause:" + e.getCause());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getClass:" + e.getClass());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getMessage:" + e.getMessage());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getLocalizedMessage:" + e.getLocalizedMessage());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getStackTrace:" + e.getStackTrace());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "isCanceled:" + call.isCanceled());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("OkHttpNetWorker", "onFail");
                        callBack.onFail();
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                Log.e("OkHttpNetWorker", "onResponse");
                if (response.isSuccessful()) {
                    final String str = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("OkHttpNetWorker", "onSuccess:" + str);
                            callBack.onSuccess(str);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("OkHttpNetWorker", "onFail");
                            callBack.onFail();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void get(String url, final ICallBack callBack, Object requestTag) {
        Request request = new Request.Builder().url(url).tag(requestTag).get().build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("OkHttpNetWorker", "onFailure");
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + Log.getStackTraceString(e));

                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getCause:" + e.getCause());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getClass:" + e.getClass());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getMessage:" + e.getMessage());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getLocalizedMessage:" + e.getLocalizedMessage());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "getStackTrace:" + e.getStackTrace());
                Log.e("OkHttpNetWorker", "<onFailure Throwable>: " + "isCanceled:" + call.isCanceled());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("OkHttpNetWorker", "onFail");
                        callBack.onFail();
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                Log.e("OkHttpNetWorker", "onResponse");
                if (response.isSuccessful()) {
                    final String str = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("OkHttpNetWorker", "onSuccess:" + str);
                            callBack.onSuccess(str);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("OkHttpNetWorker", "onFail");
                            callBack.onFail();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void cancel(Object tag) {
        if (tag == null) {
            mOkHttpClient.dispatcher().cancelAll();
        } else {
            Dispatcher dispatcher = mOkHttpClient.dispatcher();
            synchronized (dispatcher) {
                for (Call call : dispatcher.queuedCalls()) {
                    if (tag.equals(call.request().tag())) {
                        call.cancel();
                    }
                }
                for (Call call : dispatcher.runningCalls()) {
                    if (tag.equals(call.request().tag())) {
                        call.cancel();
                    }
                }
            }
        }
    }

}
