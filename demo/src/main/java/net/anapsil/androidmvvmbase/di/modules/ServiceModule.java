package net.anapsil.androidmvvmbase.di.modules;

import net.anapsil.androidmvvmbase.domain.MarvelApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ana.silva on 19/01/18.
 */
@Module
public class ServiceModule {
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
}
