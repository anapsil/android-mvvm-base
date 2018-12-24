package net.anapsil.mvvmbase.ui.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;

import net.anapsil.mvvmbase.navigation.AppRouter;

public class BaseViewModel extends ViewModel {
    protected Resources resources;
    protected AppRouter router;

    public BaseViewModel(Resources resources, AppRouter router) {
        this.resources = resources;
        this.router = router;
    }

    public void onViewCreated() {
    }

    public void onDestroyView() {
    }
}
