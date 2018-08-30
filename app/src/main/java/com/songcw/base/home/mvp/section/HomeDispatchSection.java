package com.songcw.base.home.mvp.section;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.songcw.base.R;
import com.songcw.base.home.activity.NetSimpleActivity;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;

import butterknife.OnClick;

/**
 * Created by Sprout on 2018/8/30
 */
public class HomeDispatchSection extends BaseSection {
    public HomeDispatchSection(Object source) {
        super(source);
    }

    @Override
    public IController.IPresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.btTestNet, R.id.bt_im, R.id.btCapture, R.id.btSelect, R.id.bt_meglive, R.id.bt_megocr})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btTestNet:            //请求网络示例
                go(getContext(), NetSimpleActivity.class);
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

    public void go(Context from, Class to) {
        Intent intent = new Intent();
        intent.setClass(from, to);
        startActivity(intent);
    }
}
