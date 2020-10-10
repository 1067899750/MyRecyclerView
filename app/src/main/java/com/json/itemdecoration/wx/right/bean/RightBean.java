package com.json.itemdecoration.wx.right.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Description
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2018-12-11 10:27
 */


public class RightBean implements Parcelable {
    private String name;
    private String titleName;
    private String tag;
    private boolean isTitle;

    public RightBean(String name) {
        this.name = name;
    }

    protected RightBean(Parcel in) {
        name = in.readString();
        titleName = in.readString();
        tag = in.readString();
        isTitle = in.readByte() != 0;
    }

    public static final Creator<RightBean> CREATOR = new Creator<RightBean>() {
        @Override
        public RightBean createFromParcel(Parcel in) {
            return new RightBean(in);
        }

        @Override
        public RightBean[] newArray(int size) {
            return new RightBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(titleName);
        dest.writeString(tag);
        dest.writeByte((byte) (isTitle ? 1 : 0));
    }
}
