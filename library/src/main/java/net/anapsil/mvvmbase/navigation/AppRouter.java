package net.anapsil.mvvmbase.navigation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.annotation.IdRes;

/**
 * Created by ana.silva on 17/01/18.
 */

public abstract class AppRouter<E extends Enum> {

    private FragmentManager fragmentManager;

    public AppRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public abstract void navigate(E route, Parcelable... args);

    public abstract void navigate(E route, boolean addToBackStack, Parcelable... args);

    public abstract @IdRes
    int getContainerViewId();

    private void addFragment(Fragment fragment, String tag, boolean addToStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(getContainerViewId(), fragment, fragment.getClass().getSimpleName());
        if (addToStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    private void popBackStack(String name) {
        fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
