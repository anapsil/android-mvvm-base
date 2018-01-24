package net.anapsil.androidmvvmbase.di;

import net.anapsil.androidmvvmbase.DemoApplication;
import net.anapsil.androidmvvmbase.di.modules.ActivityBuilder;
import net.anapsil.androidmvvmbase.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ana.silva on 17/01/18.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DemoApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DemoApplication> {
    }
}
