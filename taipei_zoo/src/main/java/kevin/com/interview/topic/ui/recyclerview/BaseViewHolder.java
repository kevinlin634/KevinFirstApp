package kevin.com.interview.topic.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Object viewInfo);

    public abstract void onViewRecycled();
}
