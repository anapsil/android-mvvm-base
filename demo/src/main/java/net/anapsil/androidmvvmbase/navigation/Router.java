package net.anapsil.androidmvvmbase.navigation;

import android.app.FragmentManager;
import android.os.Parcelable;

import net.anapsil.mvvmbase.navigation.AppRouter;

/**
 * Created by ana.silva on 22/01/18.
 */

public class Router extends AppRouter<Router.Routes> {

    public Router(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public void navigate(Routes route, Parcelable... args) {

    }

    @Override
    public void navigate(Routes route, boolean addToBackStack, Parcelable... args) {

    }

    @Override
    public int getContainerViewId() {
        return 0;
    }

    public enum Routes {
        MAIN,
        DETAILS
    }
}
