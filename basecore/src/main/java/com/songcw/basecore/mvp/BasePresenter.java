package com.songcw.basecore.mvp;

import android.content.Context;

import com.songcw.basecore.http.ICallBack;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sprout on 2018/8/27
 */
public abstract class BasePresenter<V extends IController.IView> implements IController.IPresenter {
    private final String MSG_ERROR = "view isn't instance of BaseSection and can't be null";
    protected V mView;

    public BasePresenter(V view) {
        if (view instanceof BaseSection) {
            mView = view;
        } else {
            throw new IllegalArgumentException(MSG_ERROR);
        }
    }

    public Context getContext() {
        if (mView != null) return ((BaseSection) mView).getContext();
        else throw new IllegalArgumentException(MSG_ERROR);
    }

    public boolean isViewAttach() {
        return mView != null;
    }

    /**
     * 默认当Section onDestroy时是取消订阅
     */
    public void addDisposable(Observable observable, ICallBack callBack) {
        this.addDisposable(true, observable, callBack);
    }

    /**
     * @param isCancelSubsribe 当Section onDestroy时是否取消订阅(true = 取消, false = 不取消)
     */
    public void addDisposable(boolean isCancelSubsribe, Observable observable, ICallBack callBack) {
        Disposable disposable = (Disposable) observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callBack);
        if (isCancelSubsribe) {
            ((BaseSection) mView).addDisposable(disposable);
        }
    }
}
