package net.anapsil.androidmvvmbase.di.modules;

import net.anapsil.androidmvvmbase.ui.main.MainActivity;
import net.anapsil.mvvmbase.di.scopes.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ana.silva on 19/01/18.
 */
@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();
}
