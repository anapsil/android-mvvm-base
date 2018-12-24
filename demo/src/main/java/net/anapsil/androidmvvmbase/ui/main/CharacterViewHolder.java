package net.anapsil.androidmvvmbase.ui.main;

import android.view.View;

import net.anapsil.androidmvvmbase.BR;
import net.anapsil.mvvmbase.ui.BaseViewHolder;
import net.anapsil.mvvmbase.ui.viewmodels.BaseItemViewModel;

/**
 * Created by ana.silva on 19/01/18.
 */

public class CharacterViewHolder extends BaseViewHolder {

    CharacterItemViewModel characterItemViewModel;

    public CharacterViewHolder(View itemView, CharacterItemViewModel characterItemViewModel) {
        super(itemView);
        this.characterItemViewModel = characterItemViewModel;
        bindContent(itemView, BR.vm);
    }

    @Override
    public BaseItemViewModel getViewModel() {
        return characterItemViewModel;
    }
}
