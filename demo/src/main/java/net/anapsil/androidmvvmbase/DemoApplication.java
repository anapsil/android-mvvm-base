package net.anapsil.androidmvvmbase;

import net.anapsil.androidmvvmbase.di.DaggerAppComponent;
import net.anapsil.mvvmbase.App;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by ana.silva on 17/01/18.
 */

public class DemoApplication extends App {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
