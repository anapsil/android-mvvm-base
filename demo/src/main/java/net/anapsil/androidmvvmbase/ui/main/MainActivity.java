package net.anapsil.androidmvvmbase.ui.main;

import net.anapsil.androidmvvmbase.BR;
import net.anapsil.androidmvvmbase.R;
import net.anapsil.androidmvvmbase.databinding.ActivityMainBinding;
import net.anapsil.mvvmbase.ui.BaseActivity;

/**
 * Created by ana.silva on 19/01/18.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    MainViewModel mainViewModel;

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
