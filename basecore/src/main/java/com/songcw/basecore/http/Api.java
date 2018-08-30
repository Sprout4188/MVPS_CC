package com.songcw.basecore.http;

import com.songcw.basecore.entity.UserInfoEntity;

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
     * 验证码登录
     */
    @POST("customer/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserInfoEntity>> captchaLogin(@FieldMap Map<String, Object> map);
}
