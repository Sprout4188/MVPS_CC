package com.songcw.login.mvp;

import com.songcw.basecore.http.ICallBack;
import com.songcw.basecore.http.RetrofitUtil;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.login.api.User;
import com.songcw.model.UserInfoEntity;

import java.util.Map;

/**
 * Created by Sprout on 2018/8/31
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(Map<String, Object> map) {
        addDisposable(RetrofitUtil.create(User.class).captchaLogin(map), new ICallBack<UserInfoEntity>() {
            @Override
            public void onSuccess(UserInfoEntity entity) {
                if (isViewAttach()) mView.onLoginSucc(entity);
            }

            @Override
            public void onFail(String errorMsg) {
                if (isViewAttach()) mView.onLoginFail(errorMsg);
            }
        });
    }
}
