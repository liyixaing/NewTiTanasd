package com.lxh.baselibray.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    /**
     * 正式上线
     */
    public static final String BASE_URL = "http://app.titans.world";//正式服  端口  80
//    public static final String BASE_URL = "http://120.79.169.58";//阿里测试服  端口  8888
//    public static final String BASE_URL = "http://192.168.0.112";//本地测试服 端口 9999

    /**
     * 本地
     */

    public static <T> T createService(Class<T> serviceClass) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        //输出okhttp一些log
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL + ":80" +
                "")
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL + ":9999")
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)//8080
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        return retrofit.create(serviceClass);
    }

}