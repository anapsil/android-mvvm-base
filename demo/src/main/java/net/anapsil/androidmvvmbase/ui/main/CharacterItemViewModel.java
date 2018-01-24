package net.anapsil.androidmvvmbase.ui.main;

import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.view.View;

import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.mvvmbase.databinding.ObservableString;
import net.anapsil.mvvmbase.di.scopes.PerActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;
import net.anapsil.mvvmbase.ui.viewmodels.BaseItemViewModel;
import net.anapsil.mvvmbase.ui.viewmodels.BaseViewModel;

/**
 * Created by ana.silva on 22/01/18.
 */
@PerActivity
public class CharacterItemViewModel extends BaseViewModel implements BaseItemViewModel<Character> {
    public ObservableString name = new ObservableString();
    public ObservableString thumb = new ObservableString();

    public ObservableBoolean favorite = new ObservableBoolean();

    public CharacterItemViewModel(Resources resources, AppRouter router) {
        super(resources, router);
    }

    @Override
    public void onItemClick(View v) {

    }

    @Override
    public void update(Character item) {
        name.set(item.getName());
        thumb.set(item.getThumbnail().getPath());
        notifyChange();
    }
}
