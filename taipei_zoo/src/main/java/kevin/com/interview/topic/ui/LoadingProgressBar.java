
/*****************************************************************
 * Copyright (C) Newegg Corporation. All rights reserved.
 *
 * Author: Kevin Lin (Kevin.k.lin@newegg.com)
 * Create Date: 2015/3/17
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 *****************************************************************/

package kevin.com.interview.topic.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class LoadingProgressBar extends ProgressBar {

    public LoadingProgressBar(Context context) {
        super(context);
        setIndeterminateDrawable(new LoadingProgressDrawable(getContext()));
    }


    public LoadingProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIndeterminateDrawable(new LoadingProgressDrawable(getContext()));
    }
}

