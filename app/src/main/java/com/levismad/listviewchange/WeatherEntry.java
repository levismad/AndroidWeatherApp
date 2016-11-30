package com.levismad.listviewchange;

import java.util.Date;

/**
 * Created by root on 30/11/2016.
 */

public class WeatherEntry {

    private String mIcon = "";
    private String mUmidity = "";
    private String mValue = "";
    private String mValueMin = "";
    private String mRain = "";


    private String mTimestamp = "";
    private Date mDate;

    public String getIcon() {
        return mIcon;
    }
    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }
    public String getUmidity() {
        return mUmidity;
    }
    public void setUmidity(String mUmidity) {
        this.mUmidity = mUmidity;
    }
    public String getRain() {
        return mRain;
    }
    public void setRain(String mRain) {
        this.mRain = mRain;
    }
    public void setValue(String value){
        mValue = value;
    }
    public String getValue(){
        return mValue.toString();


    }
    public String getValueMin() {
        return mValueMin;
    }
    public void setValueMin(String mValueMin) {
        this.mValueMin = mValueMin;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date mDate) {
        this.mDate = mDate;
    }
    public String getTimestamp() {
        return mTimestamp;
    }
    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }
}
