package kevin.com.interview.topic.event;

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

public class CommonEvent {

    private int mEventID;

    public static final int EVENT_START_PLANT_DETAIL = 91701;

    public Object mObject;

    public CommonEvent(int eventID) {
        this.mEventID = eventID;
    }

    public CommonEvent(int eventID, Object object) {
        this.mEventID = eventID;
        this.mObject = object;
    }

    public int getEventID() {
        return mEventID;
    }

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }
}
