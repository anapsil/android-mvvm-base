package net.anapsil.androidmvvmbase.di.modules;

import android.app.Application;

import net.anapsil.androidmvvmbase.DemoApplication;
import net.anapsil.androidmvvmbase.domain.MarvelApi;
import net.anapsil.mvvmbase.di.BaseModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ana.silva on 17/01/18.
 */
@Module
public abstract class AppModule extends BaseModule {
    private static final String BASE_URL = "https://gateway.marvel.com/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static MarvelApi provideApi(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }

    @Binds
    abstract Application bindApplication(DemoApplication application);
}
