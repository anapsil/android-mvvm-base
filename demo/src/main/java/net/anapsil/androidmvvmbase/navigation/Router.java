package net.anapsil.androidmvvmbase.navigation;

import android.app.Activity;
import android.os.Parcelable;

import net.anapsil.androidmvvmbase.ui.details.DetailsActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;

/**
 * Created by ana.silva on 22/01/18.
 */

public class Router extends AppRouter<Router.Routes> {

    public Router(Activity activity) {
        super(activity);
    }

    @Override
    public void navigate(Routes route, Parcelable... args) {
        navigate(route, false, args);
    }

    @Override
    public void navigate(Routes route, boolean addToBackStack, Parcelable... args) {
        switch (route) {
            case MAIN:
                break;
            case DETAILS:
                startActivity(DetailsActivity.class, args);
                break;
        }
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
