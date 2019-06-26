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
public class PlantEntity implements Serializable {

    @SerializedName("F_Name_Latin")
    private String fNameLatin;

    //"F_pdf02_ALT":null,
    @SerializedName("F_Location")
    private String fLocation;

    @SerializedName("F_Summary")
    private String fSummary;

    @SerializedName("F_Keywords")
    private String fKeywords;
    //"F_pdf01_ALT":null,
    @SerializedName("F_Pic01_URL")
    private String fPic01URL;
    //"F_pdf02_URL":null,
    //"F_Pic02_URL":null,

    //"F_Code":null,

    @SerializedName("F_Geo")
    private String fGeo;

    //"F_Pic03_URL":null,
    //"F_Voice01_ALT":null,

    @SerializedName("F_AlsoKnown")
    private String fAlsoKnown;

    //"F_Voice02_ALT":null,

    @SerializedName("F_Name_Ch")
    private String fNameCh;

    //"F_Pic04_ALT":null,
    @SerializedName("F_Name_En")
    private String fNameEn;

    @SerializedName("F_Brief")
    private String fBrief;

    //"F_Pic04_URL":null,
    //"F_Voice01_URL":null,
    @SerializedName("F_Feature")
    private String fFeature;

    @SerializedName("F_Family")
    private String fFamily;
    //"F_Pic02_ALT":null,

    //"F_Voice03_ALT":null,
    //"F_Voice02_URL":null,
    //"F_Pic03_ALT":null,
    //"F_Pic01_ALT":null,
    //"F_CID":null,
    //"F_pdf01_URL":null,
    //"F_Vedio_URL":null,
    @SerializedName("F_Genus")
    private String fGenus;

    @SerializedName("F_Function＆Application")
    private String fFunctionAndApplication;

    //"F_Voice03_URL":null,

    @SerializedName("F_Update")
    private String fUpdate;

    @SerializedName("_id")
    private int id;

    public String getfNameLatin() {
        return fNameLatin;
    }

    public void setfNameLatin(String fNameLatin) {
        this.fNameLatin = fNameLatin;
    }

    public String getfLocation() {
        return fLocation;
    }

    public void setfLocation(String fLocation) {
        this.fLocation = fLocation;
    }

    public String getfSummary() {
        return fSummary;
    }

    public void setfSummary(String fSummary) {
        this.fSummary = fSummary;
    }

    public String getfKeywords() {
        return fKeywords;
    }

    public void setfKeywords(String fKeywords) {
        this.fKeywords = fKeywords;
    }

    public String getfGeo() {
        return fGeo;
    }

    public void setfGeo(String fGeo) {
        this.fGeo = fGeo;
    }

    public String getfAlsoKnown() {
        return fAlsoKnown;
    }

    public void setfAlsoKnown(String fAlsoKnown) {
        this.fAlsoKnown = fAlsoKnown;
    }

    public String getfNameCh() {
        return fNameCh;
    }

    public void setfNameCh(String fNameCh) {
        this.fNameCh = fNameCh;
    }

    public String getfNameEn() {
        return fNameEn;
    }

    public void setfNameEn(String fNameEn) {
        this.fNameEn = fNameEn;
    }

    public String getfBrief() {
        return fBrief;
    }

    public void setfBrief(String fBrief) {
        this.fBrief = fBrief;
    }

    public String getfFeature() {
        return fFeature;
    }

    public void setfFeature(String fFeature) {
        this.fFeature = fFeature;
    }

    public String getfFamily() {
        return fFamily;
    }

    public void setfFamily(String fFamily) {
        this.fFamily = fFamily;
    }

    public String getfGenus() {
        return fGenus;
    }

    public void setfGenus(String fGenus) {
        this.fGenus = fGenus;
    }

    public String getfFunctionAndApplication() {
        return fFunctionAndApplication;
    }

    public void setfFunctionAndApplication(String fFunctionAndApplication) {
        this.fFunctionAndApplication = fFunctionAndApplication;
    }

    public String getfUpdate() {
        return fUpdate;
    }

    public void setfUpdate(String fUpdate) {
        this.fUpdate = fUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfPic01URL() {
        return fPic01URL;
    }

    public void setfPic01URL(String fPic01URL) {
        this.fPic01URL = fPic01URL;
    }
}
