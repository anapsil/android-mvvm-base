package net.anapsil.mvvmbase.ui;

import android.view.View;

import net.anapsil.mvvmbase.ui.viewmodels.BaseItemViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

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
