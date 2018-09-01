package com.songcw.login.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.login.R;
import com.songcw.model.UserInfoEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sprout on 2018/8/31
 */
public class LoginSection extends BaseSection<LoginPresenter> implements LoginView {

    private String ccCallId;
    private View btNet;

    public LoginSection(Object source) {
        super(source);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = ((BaseActivity) getContext()).getIntent();
        ccCallId = intent.getStringExtra("ccCallId");
    }

    @Override
    protected void initViews() {
        btNet = findView(R.id.bt_test);
    }

    @Override
    protected void initEvents() {
        btNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                Map<String, Object> map = new HashMap<>();
                map.put("memberNo", "2131312");
                mPresenter.login(map);
            }
        });
    }

    @Override
    public void onLoginSucc(UserInfoEntity userInfoEntity) {
        hideLoading();
        //发送组件调用的结果（返回信息）
//        CC.sendCCResult(ccCallId, CCResult.success("loginSucc", userInfoEntity));
    }

    @Override
    public void onLoginFail(String error) {
        hideLoading();
//        CC.sendCCResult(ccCallId, CCResult.error("loginFail", error));
    }
}
