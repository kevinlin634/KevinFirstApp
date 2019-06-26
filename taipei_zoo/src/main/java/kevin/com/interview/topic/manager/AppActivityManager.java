package kevin.com.interview.topic.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

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

public class AppActivityManager {

    private static Stack<FragmentActivity> activityStack;
    private FragmentActivity mLastActivity;

    private static class AppActivityManagerHolder {
        private static final AppActivityManager INSTANCE = new AppActivityManager();
    }

    public static final AppActivityManager getInstance() {
        return AppActivityManagerHolder.INSTANCE;
    }

    private AppActivityManager() {
    }

    /**
     * 將Activity加入stack
     *
     * @param activity
     */
    public void addActivity(FragmentActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<FragmentActivity>();
        }
        activityStack.add(activity);
        mLastActivity = activity;
    }

    /**
     * 獲取當前的Activity
     *
     * @return
     */
    public FragmentActivity getCurrentActivity() {
        try {
            return activityStack.lastElement();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 獲取當前的Activity
     *
     * @return
     */
    public FragmentActivity currentActivity() {
        if (activityStack == null) {
            return mLastActivity;
        } else if (activityStack.isEmpty()) {
            return mLastActivity;
        } else {
            return activityStack.lastElement();
        }
    }

    public Context getCurrentContext() {
        return getCurrentActivity();
    }

    /**
     * 獲取指定Activity
     *
     * @param cls
     */
    public FragmentActivity getActivity(Class<?> cls) {
        if (activityStack == null) {
            return null;
        }
        for (FragmentActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public List<WeakReference<FragmentActivity>> getActivityList(Class<?> cls) {
        List<WeakReference<FragmentActivity>> list = new ArrayList<>();
        for (FragmentActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                list.add(new WeakReference<FragmentActivity>(activity));
            }
        }
        return list;
    }

    /**
     * 結束當前Activity,釋放Activity所佔空間
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 結束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!isActivityFinishing(activity)) {
                activity.finish();
            }
        }
    }

    /**
     * 結束指定的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        Iterator<FragmentActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (activity != null && cls.getCanonicalName().equals(activity.getClass()
                    .getCanonicalName())) {
                iterator.remove();

                if (!isActivityFinishing(activity)) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 結束所有的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 所有的Activity重建
     */
    public void allActivityReCreate() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).recreate();
            }
        }
    }

    /**
     * 結束所有的Activity
     */
    public void finishAllActivityKeepCurrent() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if (i < size - 1) {//除了当前的activity，均finish
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出App, 結束所有的Activity並且退出
     *
     * @param context
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context
                    .ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());

            System.exit(0);
        } catch (Exception e) {
        }
    }

    public Stack<FragmentActivity> getActivitySatck() {
        return activityStack;
    }

    public boolean isActivityFinishing(Activity activity) {
        if (activity == null) {
            return true;
        }
        return activity.isFinishing() || (Build.VERSION.SDK_INT > Build.VERSION_CODES
                .JELLY_BEAN && activity.isDestroyed());
    }
}
