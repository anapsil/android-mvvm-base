package net.anapsil.androidmvvmbase.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.anapsil.androidmvvmbase.domain.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ana.silva on 19/01/18.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private List<Character> characters;

    public CharactersAdapter() {
        characters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
        notifyDataSetChanged();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
