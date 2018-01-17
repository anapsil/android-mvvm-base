package net.anapsil.mvvmbase.ui.viewmodels;

import android.content.res.Resources;
import android.databinding.BaseObservable;

import net.anapsil.mvvmbase.navigation.AppRouter;

public class BaseViewModel extends BaseObservable {
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
