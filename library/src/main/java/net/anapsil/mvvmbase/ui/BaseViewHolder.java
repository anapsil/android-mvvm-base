package net.anapsil.mvvmbase.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.anapsil.mvvmbase.ui.viewmodels.BaseItemViewModel;

public abstract class BaseViewHolder<B extends ViewDataBinding, VM extends BaseItemViewModel> extends RecyclerView.ViewHolder {

    protected B binding;
    VM viewModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected final void bindContent(@NonNull View view, int variableId) {
        binding = DataBindingUtil.bind(view);
        viewModel = getViewModel();
        binding.setVariable(variableId, viewModel);
    }

    public final void executePendingBindings() {
        if (binding != null) {
            binding.executePendingBindings();
        }
    }

    public abstract VM getViewModel();
}
