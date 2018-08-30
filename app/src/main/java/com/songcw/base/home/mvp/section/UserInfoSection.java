package com.songcw.base.home.mvp.section;

import com.orhanobut.logger.Logger;
import com.songcw.base.R;
import com.songcw.base.home.mvp.presenter.UserInfoPresenter;
import com.songcw.base.home.mvp.view.UserInfoView;
import com.songcw.basecore.mvp.BaseSection;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by Sprout on 2018/8/27
 */
public class UserInfoSection extends BaseSection<UserInfoPresenter> implements UserInfoView {

    public UserInfoSection(Object source) {
        super(source);
    }

    @Override
    public UserInfoPresenter onCreatePresenter() {
        return new UserInfoPresenter(this);
    }

    @OnClick(R.id.bt_request)
    public void click() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", "2131312");
        mPresenter.getUserInfo(map);
    }

    @Override
    public String getUserInfoSucc(String name) {
        hideLoading();
        Logger.d("UserInfoPresenter回调成功");
        return null;
    }

    @Override
    public void getUserInfoFail(String errorMsg) {
        hideLoading();
        Logger.d("UserInfoPresenter回调失败: " + errorMsg);
    }
}
