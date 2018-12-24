package net.anapsil.androidmvvmbase;

import com.squareup.picasso.Picasso;

import net.anapsil.androidmvvmbase.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by ana.silva on 17/01/18.
 */

public class DemoApplication extends DaggerApplication {
    @Inject
    Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(picasso);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
