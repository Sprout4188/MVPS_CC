package com.songcw.base.home.mvp.presenter;

import com.songcw.base.home.mvp.view.UserInfoView;
import com.songcw.basecore.entity.UserInfoEntity;
import com.songcw.basecore.http.ICallBack;
import com.songcw.basecore.mvp.BasePresenter;

import java.util.Map;

/**
 * Created by Sprout on 2018/8/27
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView> {
    public UserInfoPresenter(UserInfoView view) {
        super(view);
    }

    public void getUserInfo(Map<String, Object> map) {
        addDisposable(getApi().captchaLogin(map), new ICallBack<UserInfoEntity>(){
            @Override
            public void onSuccess(UserInfoEntity entity) {
                if (isViewAttach()) mView.getUserInfoSucc("");
            }

            @Override
            public void onFail(String errorMsg) {
                if (isViewAttach()) mView.getUserInfoFail(errorMsg);
            }
        });
    }
}
