package net.anapsil.androidmvvmbase.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.anapsil.androidmvvmbase.BR;
import net.anapsil.androidmvvmbase.R;
import net.anapsil.androidmvvmbase.databinding.ActivityDetailsBinding;
import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.navigation.Router;
import net.anapsil.mvvmbase.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ana.silva on 25/01/18.
 */

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> {

    @Inject
    DetailsViewModel detailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        setSupportActionBar(getViewDataBinding().toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void handleIntent() {
        Character character = (Character) getIntent().getParcelableArrayExtra(Router.EXTRA_ARGS)[0];
        getViewDataBinding().collapsingToolbar.setTitle(character.getName());
        getViewModel().setCharacter(character);
    }

    @Override
    public DetailsViewModel getViewModel() {
        return detailsViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public int getVariableId() {
        return BR.vm;
    }
}
