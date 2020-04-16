package com.manishkpr.exercise.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.manishkpr.exercise.BuildConfig;
import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.util.MyGsonTypeAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BackendService {

    @GET("article")
    Observable<List<Article>> getArticles();


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static BackendService newBackendService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(getOkttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(BackendService.class);
        }

        public static OkHttpClient getOkttpClient() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS);

            if(BuildConfig.DEBUG){
                client.addInterceptor(interceptor);
            }

            return client.build();
        }
    }


}
