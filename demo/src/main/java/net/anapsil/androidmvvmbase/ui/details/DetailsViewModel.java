package net.anapsil.androidmvvmbase.ui.details;

import android.content.res.Resources;
import android.text.TextUtils;

import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.mvvmbase.databinding.ObservableString;
import net.anapsil.mvvmbase.di.scopes.PerActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;
import net.anapsil.mvvmbase.ui.viewmodels.RxBaseViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

/**
 * Created by ana.silva on 25/01/18.
 */
@PerActivity
public class DetailsViewModel extends RxBaseViewModel {
    public ObservableString image = new ObservableString();
    public ObservableString description = new ObservableString();

    private Character character;

    @Inject
    public DetailsViewModel(Resources resources, AppRouter router, @Named("io") Scheduler processScheduler, @Named("android") Scheduler androidScheduler) {
        super(resources, router, processScheduler, androidScheduler);
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        image.set(character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension());
        description.set(TextUtils.isEmpty(character.getDescription()) ? "No description provided" : character.getDescription());
    }
}
