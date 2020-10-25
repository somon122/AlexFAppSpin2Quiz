package com.world_tech_point.visiting_earnapp;

public class SliderClass {

    private int mImage;
    private String mTitle;
    private String mBtnTitle;

    public SliderClass() {
    }

    public SliderClass(int mImage, String mTitle, String mBtnTitle) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mBtnTitle = mBtnTitle;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmBtnTitle() {
        return mBtnTitle;
    }
}
