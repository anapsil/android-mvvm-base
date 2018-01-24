package net.anapsil.mvvmbase.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.anapsil.mvvmbase.R;
import net.anapsil.mvvmbase.utils.TextWatcherWrapper;

public class FormCustomBindings {

    @BindingAdapter("binding")
    public static void bindText(TextView view, ObservableString text) {
        Pair<ObservableString, TextWatcher> pair = (Pair) view.getTag(R.id.bound);
        if (pair == null || pair.first != text) {
            if (pair != null) {
                view.removeTextChangedListener(pair.second);
            }

            TextWatcherWrapper watcher = new TextWatcherWrapper() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    text.set(charSequence.toString());
                }
            };

            view.setTag(R.id.bound, new Pair<>(text, watcher));
            view.addTextChangedListener(watcher);
        }

        String newValue = text.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    @BindingAdapter("cb_text")
    public static void bindCheckBoxText(CheckBox cb, ObservableString text) {
        cb.setText(text.get());
    }

    @BindingAdapter("onTouch")
    public static void handleTouch(EditText view, View.OnTouchListener touchListener) {
        view.setOnTouchListener(touchListener);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, ObservableString imageUrl) {
        Picasso.with(view.getContext()).load(imageUrl.get())
                .into(view);
    }

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void loadImage(ImageView view, ObservableString imageUrl, Drawable placeholder) {
        try {
            Picasso.with(view.getContext()).load(imageUrl.get())
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(view);
        } catch (IllegalArgumentException ex) {
            view.setImageDrawable(placeholder);
        }
    }
}
