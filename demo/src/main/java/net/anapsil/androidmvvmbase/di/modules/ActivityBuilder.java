package net.anapsil.androidmvvmbase.di.modules;

import net.anapsil.androidmvvmbase.ui.details.DetailsActivity;
import net.anapsil.androidmvvmbase.ui.details.DetailsActivityModule;
import net.anapsil.androidmvvmbase.ui.main.MainActivity;
import net.anapsil.androidmvvmbase.ui.main.MainActivityModule;
import net.anapsil.mvvmbase.di.scopes.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ana.silva on 19/01/18.
 */
@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {DetailsActivityModule.class})
    abstract DetailsActivity detailsActivity();
}
