package com.songcw.login.activity;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.login.R;
import com.songcw.login.mvp.LoginSection;

/**
 * Created by Sprout on 2018/8/31
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void addSections() {
        addSection(new LoginSection(this));
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_login;
    }
}
