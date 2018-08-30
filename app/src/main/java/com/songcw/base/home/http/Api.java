package com.songcw.base.home.http;

import com.songcw.base.home.http.entity.UserInfoEntity;
import com.songcw.basecore.http.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Sprout on 2018/8/28
 */
public interface Api {
    /**
     * 公共类接口
     */
    interface Common {

    }

    /**
     * 用户相关接口
     */
    interface User {
        /**
         * 验证码登录
         */
        @POST("customer/login")
        @FormUrlEncoded
        Observable<BaseResponse<UserInfoEntity>> captchaLogin(@FieldMap Map<String, Object> map);

        /**
         * 密码登录
         */
        @POST("customer/login")
        @FormUrlEncoded
        Observable<BaseResponse<UserInfoEntity>> psdLogin(@FieldMap Map<String, Object> map);
    }

    /**
     * 订单相关接口
     */
    interface Order {

    }
}
