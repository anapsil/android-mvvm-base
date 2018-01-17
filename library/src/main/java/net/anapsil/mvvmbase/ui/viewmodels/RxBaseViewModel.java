package net.anapsil.mvvmbase.ui.viewmodels;

import android.content.res.Resources;
import android.databinding.ObservableBoolean;

import net.anapsil.mvvmbase.navigation.AppRouter;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class RxBaseViewModel extends BaseViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableBoolean isError = new ObservableBoolean(false);
    protected CompositeDisposable disposable;
    protected Scheduler processScheduler;
    protected Scheduler androidScheduler;

    public RxBaseViewModel(Resources resources, AppRouter router, Scheduler processScheduler, Scheduler androidScheduler) {
        super(resources, router);
        this.processScheduler = processScheduler;
        this.androidScheduler = androidScheduler;
    }

    @Override
    public void onViewCreated() {
        disposable = new CompositeDisposable();
    }

    @Override
    public void onDestroyView() {
        disposable.dispose();
    }
}
