package com.songcw.base.home.activity;

import android.os.Bundle;

import com.songcw.base.R;
import com.songcw.base.home.mvp.section.UserInfoSection;
import com.songcw.basecore.mvp.BaseActivity;

/**
 * 网络请求示例
 */
public class NetSimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("网络请求示例");
    }

    @Override
    protected void addSections() {
        addSection(new UserInfoSection(this));
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_net_simple;
    }
}
