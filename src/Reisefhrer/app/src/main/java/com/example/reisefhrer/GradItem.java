package com.example.reisefhrer;

public class GradItem {
    private int mImageResource;
    private String mText1;


    public GradItem(int imageResource, String text1){
        mImageResource = imageResource;
        mText1 = text1;
    }

    public int getmImageResource(){
        return mImageResource;
    }

    public String getmText1(){
        return mText1;
    }

}
