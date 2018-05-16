package net.anapsil.androidmvvmbase.ui.main;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.anapsil.androidmvvmbase.R;
import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.navigation.Router;
import net.anapsil.mvvmbase.navigation.AppRouter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ana.silva on 19/01/18.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private List<Character> characters;
    private Resources resources;
    private Router router;

    @Inject
    public CharactersAdapter(Resources resources, AppRouter router) {
        characters = new ArrayList<>();
        this.resources = resources;
        this.router = (Router) router;
    }

    public void addCharacters(List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view, new CharacterItemViewModel(resources, router));
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.getViewModel().update(characters.get(position));
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
