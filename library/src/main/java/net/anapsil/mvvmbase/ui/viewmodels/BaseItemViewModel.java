package net.anapsil.mvvmbase.ui.viewmodels;

import android.view.View;

public interface BaseItemViewModel<I extends Object> {
    void onItemClick(View v);

    void update(I item);
}
