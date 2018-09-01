package com.songcw.base.home.mvp.section;

import android.view.View;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.songcw.base.R;
import com.songcw.basecore.grobal.Constant;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;
import com.songcw.model.UserInfoEntity;

/**
 * Created by Sprout on 2018/8/30
 */
public class HomeDispatchSection extends BaseSection implements View.OnClickListener {

    private View btNet;

    public HomeDispatchSection(Object source) {
        super(source);
    }

    @Override
    public IController.IPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        btNet = findView(R.id.btTestNet);
    }

    @Override
    protected void initEvents() {
        btNet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btTestNet:            //请求网络示例
                CC.obtainBuilder("ComponentLogin")
                        .setContext(getContext())
                        .setActionName(Constant.action.ALogin)
                        .build()
                        .callAsync(new IComponentCallback() {
                            @Override
                            public void onResult(CC cc, CCResult result) {
                                if (result.getCode() == CCResult.CODE_SUCCESS) {
                                    UserInfoEntity loginResult = result.getDataItem("loginSucc");
                                } else {

                                    String errorMsg = result.getDataItem("loginFail");
                                }
                            }
                        });
                break;
            case R.id.bt_im:                //融云IM示例
//                go(getActivity(), ImSimpleActivity.class);
                break;
            case R.id.btCapture:            //拍照示例
//                go(getActivity(), CaptureSimpleActivity.class);
                break;
            case R.id.btSelect:             //选择照片示例
//                go(getActivity(), CaptureSimpleActivity.class);
                break;
            case R.id.bt_meglive:           //Face++活体检测示例
//                go(getActivity(), MegLiveLoadingActivity.class);
                break;
            case R.id.bt_megocr:           //Face++身份证OCR检测示例
//                go(getActivity(), MegOcrLoadingActivity.class);
                break;
        }
    }
}
