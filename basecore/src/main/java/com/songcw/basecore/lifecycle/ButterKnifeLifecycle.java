package com.songcw.basecore.lifecycle;

import android.view.View;

import com.songcw.basecore.mvp.BaseSection;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Sprout at 2017/8/15
 */
public class ButterKnifeLifecycle implements ILifecycle {
    private Unbinder unbinder;

    public ButterKnifeLifecycle(BaseSection section) {
        this(section, section.getDecorView());
    }

    public ButterKnifeLifecycle(Object obj, BaseSection section) {
        this(obj, section.getDecorView());
    }

    public ButterKnifeLifecycle(Object obj, View source) {
        unbinder = ButterKnife.bind(obj, source);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onHide() {
    }

    @Override
    public void onDestory() {
        if (unbinder != null) unbinder.unbind();
    }
}
