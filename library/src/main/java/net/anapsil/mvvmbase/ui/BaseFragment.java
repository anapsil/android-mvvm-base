package net.anapsil.mvvmbase.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.anapsil.mvvmbase.ui.viewmodels.BaseViewModel;

import dagger.android.DaggerFragment;

public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends DaggerFragment {

    private B binding;
    private VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        binding.setVariable(getVariableId(), viewModel);
        binding.executePendingBindings();
        viewModel.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        viewModel.onDestroyView();
        super.onDestroyView();
    }

    protected B getViewBinding() {
        return binding;
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();

    public abstract int getVariableId();
}
