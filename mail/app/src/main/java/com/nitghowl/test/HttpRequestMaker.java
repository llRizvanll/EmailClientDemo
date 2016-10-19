package com.nitghowl.test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequestMaker {
    //change this to your own domain.
        public final static String domain = "http://192.168.0.4";
        public final static String path = ":8088/";
        public static OkHttpClient okClient = new OkHttpClient.Builder()

                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();

                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                System.out.println("GET CALLS : "+chain.request().url().toString());
                                return chain.proceed(request);
                            }
                        })
                .build();

        public static Retrofit builder = new Retrofit.Builder().
                baseUrl(domain).addConverterFactory(GsonConverterFactory.create()).client(okClient).build();
        public static ApiMethodGen createService(){
            return builder.create(ApiMethodGen.class);
        }
}
