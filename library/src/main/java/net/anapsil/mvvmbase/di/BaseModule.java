package net.anapsil.mvvmbase.di;

import android.app.Application;
import android.content.res.Resources;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.anapsil.mvvmbase.App;
import net.anapsil.mvvmbase.BuildConfig;

import java.util.Collections;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ana.silva on 17/01/18.
 */
@Module
public abstract class BaseModule {

    @Singleton
    @Provides
    protected static Resources provideResources(Application application) {
        return application.getResources();
    }

    @Singleton
    @Provides
    protected static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    protected static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    protected static OkHttp3Downloader provideOkHttp3Downloader() {
        return new OkHttp3Downloader(new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build());
    }

    @Singleton
    @Provides
    protected static Picasso providePicasso(Application application, OkHttp3Downloader downloader) {
        return new Picasso.Builder(application.getApplicationContext())
                .downloader(downloader)
                .build();
    }

    @Singleton
    @Provides
    @Named("io")
    protected static Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("computation")
    protected static Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("android")
    protected static Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Binds
    abstract Application bindApplication(App application);
}
