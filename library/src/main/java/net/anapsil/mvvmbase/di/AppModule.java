package net.anapsil.mvvmbase.di;

import android.app.Application;
import android.content.res.Resources;

import net.anapsil.mvvmbase.App;
import net.anapsil.mvvmbase.BuildConfig;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ana.silva on 17/01/18.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Application bindApplication(App application);

    @Singleton
    @Provides
    static Resources provideResources(Application application) {
        return application.getResources();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideLoggingInterceptor() {
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
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    @Named("io")
    static Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("computation")
    static Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("android")
    static Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
