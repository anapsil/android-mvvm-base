package net.anapsil.androidmvvmbase.ui.main;

import android.app.Activity;
import android.app.FragmentManager;

import net.anapsil.androidmvvmbase.navigation.Router;
import net.anapsil.mvvmbase.di.scopes.PerActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ana.silva on 10/01/18.
 */
@Module
public abstract class MainActivityModule {

    @PerActivity
    @Provides
    static FragmentManager provideFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

    @PerActivity
    @Provides
    static AppRouter provideAppRouter(FragmentManager fragmentManager) {
        return new Router(fragmentManager);
    }

    @Binds
    abstract Activity bindActivity(MainActivity mainActivity);
}
