package com.songcw.base.home.mvp.view;

import com.songcw.basecore.mvp.IController;

/**
 * Created by Sprout on 2018/8/27
 */
public interface UserInfoView extends IController.IView {
    String getUserInfoSucc(String name);

    void getUserInfoFail(String errorMsg);
}
