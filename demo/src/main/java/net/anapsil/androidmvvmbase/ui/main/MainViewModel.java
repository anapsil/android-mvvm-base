package net.anapsil.androidmvvmbase.ui.main;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import net.anapsil.androidmvvmbase.domain.interactors.LoadAllCharactersUseCase;
import net.anapsil.androidmvvmbase.domain.interactors.UseCase;
import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.mvvmbase.di.scopes.PerActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;
import net.anapsil.mvvmbase.ui.viewmodels.RxBaseViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Scheduler;

/**
 * Created by ana.silva on 19/01/18.
 */
@PerActivity
public class MainViewModel extends RxBaseViewModel {
    private UseCase useCase;
    private MutableLiveData<List<Character>> characters = new MutableLiveData<>();

    @Inject
    public MainViewModel(Resources resources, AppRouter router, @Named("io") Scheduler processScheduler, @Named("android") Scheduler androidScheduler, LoadAllCharactersUseCase useCase) {
        super(resources, router, processScheduler, androidScheduler);
        this.useCase = useCase;
    }

    public MutableLiveData<List<Character>> getCharacters() {
        return characters;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        loadAll();
    }

    public void loadAll() {
        disposable.add(((LoadAllCharactersUseCase) useCase).execute(getScreenDensity(), getScreenOrientation())
                .toList()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .doOnSubscribe(__ -> status.set(Status.LOADING))
                .doOnSuccess(__ -> status.set(Status.SUCCESS))
                .doOnError(throwable -> status.set(Status.ERROR))
                .subscribe(characters -> MainViewModel.this.characters.postValue(characters), Throwable::printStackTrace));
    }

    private int getScreenDensity() {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) metrics.scaledDensity;
    }

    private int getScreenOrientation() {
        return resources.getConfiguration().orientation;
    }
}
