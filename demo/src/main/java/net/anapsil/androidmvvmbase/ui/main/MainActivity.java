package net.anapsil.androidmvvmbase.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.anapsil.androidmvvmbase.BR;
import net.anapsil.androidmvvmbase.R;
import net.anapsil.androidmvvmbase.databinding.ActivityMainBinding;
import net.anapsil.mvvmbase.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ana.silva on 19/01/18.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewDataBinding().charactersList.setHasFixedSize(true);
        getViewDataBinding().charactersList.setAdapter(getViewModel().getAdapter());
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getVariableId() {
        return BR.vm;
    }
}
