package net.anapsil.androidmvvmbase.ui.main;

import android.view.View;

import net.anapsil.mvvmbase.ui.BaseViewHolder;
import net.anapsil.mvvmbase.ui.viewmodels.BaseItemViewModel;

/**
 * Created by ana.silva on 19/01/18.
 */

public class CharacterViewHolder extends BaseViewHolder {
    public CharacterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public BaseItemViewModel getViewModel() {
        return null;
    }
}
