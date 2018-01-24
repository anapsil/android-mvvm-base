package net.anapsil.androidmvvmbase.ui.main;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import net.anapsil.androidmvvmbase.domain.interactors.LoadAllCharactersUseCase;
import net.anapsil.androidmvvmbase.domain.interactors.UseCase;
import net.anapsil.mvvmbase.di.scopes.PerActivity;
import net.anapsil.mvvmbase.navigation.AppRouter;
import net.anapsil.mvvmbase.ui.viewmodels.RxBaseViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

/**
 * Created by ana.silva on 19/01/18.
 */
@PerActivity
public class MainViewModel extends RxBaseViewModel {
    private UseCase useCase;
    private CharactersAdapter adapter;

    @Inject
    public MainViewModel(Resources resources, AppRouter router, @Named("io") Scheduler processScheduler, @Named("android") Scheduler androidScheduler, LoadAllCharactersUseCase useCase, CharactersAdapter adapter) {
        super(resources, router, processScheduler, androidScheduler);
        this.useCase = useCase;
        this.adapter = adapter;
    }

    public CharactersAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        loadAll();
    }

    public void loadAll() {
        disposable.add(((LoadAllCharactersUseCase) useCase).execute(getScreenDensity(), getScreenOrientation())
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .doOnSubscribe(__ -> status.set(Status.LOADING))
                .doOnComplete(() -> status.set(Status.SUCCESS))
                .doOnError(throwable -> status.set(Status.ERROR))
                .subscribe(character -> adapter.addCharacter(character)));
    }

    private float getScreenDensity() {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.density;
    }

    private int getScreenOrientation() {
        return resources.getConfiguration().orientation;
    }
}
