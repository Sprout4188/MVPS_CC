package com.songcw.basecore.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.songcw.basecore.lifecycle.ButterKnifeLifecycle;
import com.songcw.basecore.lifecycle.ILifecycle;
import com.songcw.basecore.lifecycle.LifecycleManager;
import com.songcw.basecore.lifecycle.RxBusLifecycle;

import io.reactivex.disposables.Disposable;

/**
 * Created by Sprout on 2018/8/27
 */
public abstract class BaseSection<P extends IController.IPresenter> implements IController.IView {

    private Object source;
    private View decorView;
    protected P mPresenter;
    private LifecycleManager manager = new LifecycleManager();

    private static final String MSG_SOURCE = "source must be activity or fragment";
    private final String TAG = this.getClass().getSimpleName();

    public BaseSection(Object source) {
        if (null == source) {
            throw new IllegalArgumentException("source cannot be null");
        }
        if (!(source instanceof Activity) && !(source instanceof Fragment) && !(source instanceof android.app.Fragment)) {
            throw new IllegalArgumentException(MSG_SOURCE);
        }
        this.source = source;
        mPresenter = onCreatePresenter();
        if (source instanceof Activity) {
            this.decorView = ((Activity) source).getWindow().getDecorView();
        } else if (source instanceof Fragment) {
            this.decorView = ((Fragment) source).getView();
        } else if (source instanceof android.app.Fragment) {
            this.decorView = ((android.app.Fragment) source).getView();
        } else {
            throw new IllegalArgumentException(MSG_SOURCE);
        }
    }

    public Context getContext() {
        if (source instanceof Activity) {
            return (Context) source;
        } else if (source instanceof Fragment) {
            return ((Fragment) source).getActivity();
        } else if (source instanceof android.app.Fragment) {
            return ((android.app.Fragment) source).getActivity();
        } else {
            throw new IllegalStateException("source must be Activity or Fragment");
        }
    }

    public View getDecorView() {
        return decorView;
    }

    public abstract P onCreatePresenter();

    /**
     * 添加生命周期管理
     */
    public void addLifecycle(ILifecycle lifecycle) {
        manager.addLifecycle(lifecycle);
    }

    /**
     * 添加Rxjava生命周期管理(注册与反注册)
     */
    public void addDisposable(Disposable disposable) {
        manager.addDisposable(disposable);
    }

    public void showLoading() {
        ((BaseActivity) getContext()).showLoading();
    }

    public void hideLoading() {
        ((BaseActivity) getContext()).hideLoading();
    }

    /**
     * 默认添加ButterKnife和RxBus生命周期
     */
    public void addDefaultLifecycle() {
        addLifecycle(new ButterKnifeLifecycle(this));
        addLifecycle(new RxBusLifecycle(this));
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        manager.onCreate();
    }

    public void onViewCreated(View view, Bundle state) {

    }

    public void onRestart() {
        manager.onShow();
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
        manager.onHide();
    }

    public void onDestroy() {
        manager.onDestory();
    }

    public void onNewIntent(Intent intent) {
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void startActivity(Intent intent) {
        if (source instanceof Activity) {
            ((Activity) source).startActivity(intent);
        } else if (source instanceof Fragment) {
            ((Fragment) source).startActivity(intent);
        } else if (source instanceof android.app.Fragment) {
            ((android.app.Fragment) source).startActivity(intent);
        } else {
            throw new IllegalStateException(MSG_SOURCE);
        }
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (source instanceof Activity) {
            ((Activity) source).startActivityForResult(intent, requestCode);
        } else if (source instanceof Fragment) {
            ((Fragment) source).startActivityForResult(intent, requestCode);
        } else if (source instanceof android.app.Fragment) {
            ((android.app.Fragment) source).startActivityForResult(intent, requestCode);
        } else {
            throw new IllegalStateException(MSG_SOURCE);
        }
    }

    public void finish() {
        if (source instanceof Activity) {
            ((Activity) source).finish();
        } else if (source instanceof Fragment) {
            if (null != ((Fragment) source).getActivity()) {
                ((Fragment) source).getActivity().finish();
            }
        } else if (source instanceof android.app.Fragment) {
            if (null != ((android.app.Fragment) source).getActivity()) {
                ((android.app.Fragment) source).getActivity().finish();
            }
        } else {
            throw new IllegalStateException(MSG_SOURCE);
        }
    }

    public void setResult(int resultCode) {
        setResult(resultCode, null);
    }

    public void setResult(int resultCode, Intent data) {
        if (source instanceof Activity) {
            ((Activity) source).setResult(resultCode, data);
        } else if (source instanceof Fragment) {
            if (null != ((Fragment) source).getActivity()) {
                ((Fragment) source).getActivity().setResult(resultCode, data);
            }
        } else if (source instanceof android.app.Fragment) {
            if (null != ((android.app.Fragment) source).getActivity()) {
                ((android.app.Fragment) source).getActivity().setResult(resultCode, data);
            }
        } else {
            throw new IllegalStateException(MSG_SOURCE);
        }
    }
}
