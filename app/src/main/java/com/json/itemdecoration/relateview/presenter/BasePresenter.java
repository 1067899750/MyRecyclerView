package com.json.itemdecoration.relateview.presenter;

import com.json.itemdecoration.relateview.listener.ViewCallBack;

public abstract class BasePresenter {

    protected ViewCallBack mViewCallBack;

    public void add(ViewCallBack viewCallBack) {
        this.mViewCallBack = viewCallBack;
    }

    public void remove() {
        this.mViewCallBack = null;
    }

    protected abstract void getData();
}
