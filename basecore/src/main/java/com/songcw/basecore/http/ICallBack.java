package com.songcw.basecore.http;

import com.songcw.basecore.grobal.Grobal;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Sprout on 2018/8/28
 */
public abstract class ICallBack<T> extends DisposableObserver<BaseResponse<T>> {
    public abstract void onSuccess(T entity);

    public abstract void onFail(String errorMsg);


    @Override
    public void onNext(BaseResponse<T> response) {
        if (Grobal.Http.Code_Succ.equals(response.getCode())) {
            onSuccess(response.getResult());
        } else {
            onFail(response.getMsg());
        }
        if (!isDisposed()) dispose();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UnknownHostException) {
            onFail("无效Host");
        } else if (e instanceof SocketTimeoutException) {
            onFail("请求超时");
        } else if (e instanceof ConnectException) {
            onFail("连接失败");
        } else if (e instanceof HttpException) {
            onFail(((HttpException) e).message());
        } else {
            onFail(e.getMessage());
        }
        e.printStackTrace();
        if (!isDisposed()) dispose();
    }

    @Override
    public void onComplete() {
        if (!isDisposed()) dispose();
    }
}
