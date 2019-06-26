package kevin.com.interview.topic.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
public class PlantResponseEntity implements Serializable {

    @SerializedName("result")
    ResultEntity<PlantEntity> mResultEntity;

    public ResultEntity getResultEntity() {
        return mResultEntity;
    }

    public void setResultEntity(ResultEntity resultEntity) {
        mResultEntity = resultEntity;
    }
}
