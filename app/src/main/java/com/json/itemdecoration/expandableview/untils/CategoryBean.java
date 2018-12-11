package com.json.itemdecoration.expandableview.untils;

import java.io.Serializable;

/**
 * Description  实体类
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2018-11-22 17:07
 */


public class CategoryBean implements Serializable {
    private static final long serialVersionUID = 5136218664701666396L;

    private String tag;//所属的分类
    private String categoryName;

    public CategoryBean(String tag, String categoryName) {
        this.tag = tag;
        this.categoryName = categoryName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
