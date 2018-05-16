package net.anapsil.mvvmbase.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.IdRes;

/**
 * Created by ana.silva on 17/01/18.
 */

public abstract class AppRouter<E extends Enum> {
    public static final String EXTRA_ARGS = "extra_args";
    private Activity activity;

    public AppRouter(Activity activity) {
        this.activity = activity;
    }

    public abstract void navigate(E route, Parcelable... args);

    public abstract void navigate(E route, boolean addToBackStack, Parcelable... args);

    public abstract @IdRes
    int getContainerViewId();

    protected void startActivity(Class clazz, Parcelable... args) {
        Intent intent = new Intent(activity, clazz);
        if (args != null) {
            intent.putExtra(EXTRA_ARGS, args);
        }
        activity.startActivity(intent);
    }

    protected void addFragment(Fragment fragment, String tag, boolean addToStack) {
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.add(getContainerViewId(), fragment, fragment.getClass().getSimpleName());
        if (addToStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    protected void popBackStack(String name) {
        activity.getFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
