package net.anapsil.mvvmbase.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import net.anapsil.mvvmbase.ui.viewmodels.BaseViewModel;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends DaggerAppCompatActivity {
    private B viewDataBinding;
    private VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.onViewCreated();
    }

    @Override
    protected void onDestroy() {
        viewModel.onDestroyView();
        super.onDestroy();
    }

    private void performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = getViewModel();
        viewDataBinding.setVariable(getVariableId(), viewModel);
        viewDataBinding.executePendingBindings();
    }

    public B getViewDataBinding() {
        return viewDataBinding;
    }

    public abstract VM getViewModel();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract int getVariableId();
}
