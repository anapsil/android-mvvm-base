package net.anapsil.androidmvvmbase.ui.details;

import android.app.Activity;

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
public abstract class DetailsActivityModule {

    @PerActivity
    @Provides
    static AppRouter provideAppRouter(Activity activity) {
        return new Router(activity);
    }

    @Binds
    abstract Activity bindActivity(DetailsActivity detailsActivity);
}
