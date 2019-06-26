package kevin.com.interview.topic.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kevin.com.interview.topic.R;
import kevin.com.interview.topic.entity.PavilionAreaEntity;


/****************************************************************
 * Copyright (C) Kevin Corporation. All rights reserved.
 *
 * Author: Kevin Lin
 * Create Date: 2019-06-23
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 **************************************************************/

public class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private final int ITEM_VIEW_TYPE_PAVILION = 2424;
    private final int ITEM_VIEW_TYPE_PAVILION_HEADER = 3939;
    private final int ITEM_VIEW_TYPE_PLANT = 8787;
    private List<T> mList;
    private Object mHeaderModel;
    private int offset = 0;

    public CommonAdapter(List<T> list) {
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderModel != null && isPositionHeader(position)) {
            return ITEM_VIEW_TYPE_PAVILION_HEADER;
        }

        if (mList.get(position - offset) instanceof PavilionAreaEntity) {
            return ITEM_VIEW_TYPE_PAVILION;
        } else {
            return ITEM_VIEW_TYPE_PLANT;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_PAVILION:
                View pavilionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pavilion_recyclerview_viewholder, parent, false);
                return new PavilionViewHolder(pavilionView);
            case ITEM_VIEW_TYPE_PLANT:
                View plantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_recyclerview_viewholder, parent, false);
                return new PlantViewHolder(plantView);
            case ITEM_VIEW_TYPE_PAVILION_HEADER:
                View pavilionHeaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pavilion_header_recyclerview_viewholder, parent, false);
                return new PavilionHeaderViewHolder(pavilionHeaderView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        if (holder instanceof PavilionHeaderViewHolder) {
            holder.onBindViewHolder(mHeaderModel);
        } else {
            holder.onBindViewHolder(mList.get(position - offset));
        }
    }

    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        if (holder == null) {
            return;
        }
        holder.onViewRecycled();
        super.onViewRecycled(holder);
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        if (mHeaderModel != null) {
            return mList == null ? 0 : mList.size() + offset;
        } else {
            return mList == null ? 0 : mList.size();
        }
    }

    public Object getHeaderModel() {
        return mHeaderModel;
    }

    public void setHeaderModel(Object headerModel) {
        mHeaderModel = headerModel;
        offset = 1;
    }
}
