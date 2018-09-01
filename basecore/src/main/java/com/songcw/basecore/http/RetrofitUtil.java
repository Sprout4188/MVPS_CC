package com.songcw.basecore.http;

import com.songcw.basecore.grobal.Config;
import com.songcw.basecore.sp.PermanentInfoSP;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sprout on 2018/8/28
 */
public class RetrofitUtil {
    private volatile static Retrofit retrofit;

    public static <T> T create(Class<T> clazz) {
        return getInstance().create(clazz);
    }

    private static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        }
        return RetrofitUtil.retrofit.newBuilder().baseUrl(PermanentInfoSP.baseUrl.getValue()).build();
    }

    private static Retrofit getRetrofit() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
        }
        OkHttpClient okHttpClient = builder
                .addInterceptor(new AddHeaderInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Config.Http.ConnectTimeout, TimeUnit.SECONDS)
                .readTimeout(Config.Http.ReadTimeout, TimeUnit.SECONDS)
                .writeTimeout(Config.Http.WriteTimeout, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PermanentInfoSP.baseUrl.getValue())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    static class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            Map<String, String> header = Config.Http.buildHeader();   //添加公共Header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
            Request request = builder.method(original.method(), original.body()).build();

            Response response = chain.proceed(request);
            return response;
        }
    }
}
