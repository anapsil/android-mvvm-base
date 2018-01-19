package net.anapsil.androidmvvmbase.domain.interactors;

import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.repo.CharactersRepository;

import io.reactivex.Observable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class LoadAllCharactersUseCase extends MarvelUseCase<Character, CharactersRepository> {

    public LoadAllCharactersUseCase(CharactersRepository repository) {
        super(repository);
    }

    @Override
    public Observable<Character> execute() {
        return repository.getCharacters(timestamp, generateHash(timestamp));
    }
}
