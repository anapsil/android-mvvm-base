package net.anapsil.androidmvvmbase.ui.main;

import android.content.res.Resources;

import net.anapsil.androidmvvmbase.domain.interactors.LoadAllCharactersUseCase;
import net.anapsil.androidmvvmbase.domain.interactors.UseCase;
import net.anapsil.mvvmbase.navigation.AppRouter;
import net.anapsil.mvvmbase.ui.viewmodels.RxBaseViewModel;

import io.reactivex.Scheduler;

/**
 * Created by ana.silva on 19/01/18.
 */

public class MainViewModel extends RxBaseViewModel {
    private UseCase useCase;
    private CharactersAdapter adapter;

    public MainViewModel(Resources resources, AppRouter router, Scheduler processScheduler, Scheduler androidScheduler, LoadAllCharactersUseCase useCase, CharactersAdapter adapter) {
        super(resources, router, processScheduler, androidScheduler);
        this.useCase = useCase;
        this.adapter = adapter;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        loadAll();
    }

    public void loadAll() {
        disposable.add(((LoadAllCharactersUseCase)useCase).execute()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .doOnSubscribe(__ -> status.set(Status.LOADING))
                .doOnComplete(() -> status.set(Status.SUCCESS))
                .doOnError(throwable -> status.set(Status.ERROR))
                .subscribe(character -> adapter.addCharacter(character)));
    }
}
