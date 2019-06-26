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
public class PavilionAreaEntity implements Serializable {

    @SerializedName("E_Pic_URL")
    private String ePicURL;

    @SerializedName("E_Geo")
    private String eGeo;

    @SerializedName("E_Info")
    private String eInfo;

    @SerializedName("E_no")
    private String eNo;

    @SerializedName("E_Category")
    private String e_Category;

    @SerializedName("E_Name")
    private String eName;

    @SerializedName("E_Memo")
    private String eMemo;

    @SerializedName("_id")
    private int id;

    @SerializedName("E_URL")
    private String eURL;

    public String getePicURL() {
        return ePicURL;
    }

    public void setePicURL(String ePicURL) {
        this.ePicURL = ePicURL;
    }

    public String geteGeo() {
        return eGeo;
    }

    public void seteGeo(String eGeo) {
        this.eGeo = eGeo;
    }

    public String geteInfo() {
        return eInfo;
    }

    public void seteInfo(String eInfo) {
        this.eInfo = eInfo;
    }

    public String geteNo() {
        return eNo;
    }

    public void seteNo(String eNo) {
        this.eNo = eNo;
    }

    public String getE_Category() {
        return e_Category;
    }

    public void setE_Category(String e_Category) {
        this.e_Category = e_Category;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteMemo() {
        return eMemo;
    }

    public void seteMemo(String eMemo) {
        this.eMemo = eMemo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteURL() {
        return eURL;
    }

    public void seteURL(String eURL) {
        this.eURL = eURL;
    }
}
