package net.anapsil.mvvmbase.ui.viewmodels;

import android.content.res.Resources;

import net.anapsil.mvvmbase.navigation.AppRouter;

import androidx.databinding.ObservableField;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class RxBaseViewModel extends BaseViewModel {
    public ObservableField<Status> status = new ObservableField<>();
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

    public enum Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
