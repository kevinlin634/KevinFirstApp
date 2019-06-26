package kevin.com.interview.topic.utils;

import android.support.design.widget.AppBarLayout;

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

public abstract class AppBarStateChangeEvent implements AppBarLayout.OnOffsetChangedListener {
    protected enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset);

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED, verticalOffset);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, verticalOffset);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE, verticalOffset);
            }
            mCurrentState = State.IDLE;
        }
    }
}
