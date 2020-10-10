package com.json.itemdecoration.wx.right.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * @author puyantao
 * @describe
 * @create 2020/10/10 10:24
 */
public class CategoryOneArrayBean implements Parcelable {
    private String name;
    private List<CategoryOneArrayBean.CategoryTwoArrayBean> categoryTwoArray;

    public CategoryOneArrayBean() {
    }

    protected CategoryOneArrayBean(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoryOneArrayBean> CREATOR = new Creator<CategoryOneArrayBean>() {
        @Override
        public CategoryOneArrayBean createFromParcel(Parcel in) {
            return new CategoryOneArrayBean(in);
        }

        @Override
        public CategoryOneArrayBean[] newArray(int size) {
            return new CategoryOneArrayBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<CategoryOneArrayBean.CategoryTwoArrayBean> getCategoryTwoArray() {
        return categoryTwoArray;
    }

    public void setCategoryTwoArray(List<CategoryOneArrayBean.CategoryTwoArrayBean> categoryTwoArray) {
        this.categoryTwoArray = categoryTwoArray;
    }

    public static class CategoryTwoArrayBean implements Parcelable {
        private String name;

        public CategoryTwoArrayBean() {
        }

        protected CategoryTwoArrayBean(Parcel in) {
            name = in.readString();
        }

        public static final Creator<CategoryOneArrayBean.CategoryTwoArrayBean> CREATOR = new Creator<CategoryOneArrayBean.CategoryTwoArrayBean>() {
            @Override
            public CategoryOneArrayBean.CategoryTwoArrayBean createFromParcel(Parcel in) {
                return new CategoryOneArrayBean.CategoryTwoArrayBean(in);
            }

            @Override
            public CategoryOneArrayBean.CategoryTwoArrayBean[] newArray(int size) {
                return new CategoryOneArrayBean.CategoryTwoArrayBean[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
        }
    }
}
