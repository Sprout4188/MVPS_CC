package com.songcw.login.mvp;

import com.songcw.basecore.mvp.IController;
import com.songcw.model.UserInfoEntity;

/**
 * Created by Sprout on 2018/8/31
 */
public interface LoginView extends IController.IView{

    void onLoginSucc(UserInfoEntity userInfoEntity);

    void onLoginFail(String error);
}
