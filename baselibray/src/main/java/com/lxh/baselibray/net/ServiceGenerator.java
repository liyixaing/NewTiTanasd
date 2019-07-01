package com.lxh.baselibray.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    /**
     * 本地
     */

//     public static final String BASE_URL = "http://192.168.1.130"; //38.21.243.86   http://114.55.165.42:7116


    /**
     * 正式上线
     */
   //public static final String BASE_URL = "http://38.21.243.86";
//     public static final String BASE_URL = "http://114.55.165.42";//公司测试服务器
//    public static final String BASE_URL = "http://app.titans.world";
    public static final String BASE_URL = "http://120.79.169.58";

    public static <T> T createService(Class<T> serviceClass) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL + ":8888")//8080
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL + ":80")//8080
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)//8080
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        return retrofit.create(serviceClass);
    }

}
